package cdm.observable.asset.calculatedrate.functions;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.functions.GetAllBusinessCenters;
import cdm.observable.asset.calculatedrate.CalculatedRateObservationDatesAndWeights;
import cdm.observable.asset.calculatedrate.CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.calculatedrate.ObservationPeriodDatesEnum;
import cdm.observable.asset.calculatedrate.ObservationShiftCalculation;
import cdm.observable.asset.calculatedrate.OffsetCalculation;
import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.ResetDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(GenerateObservationDatesAndWeights.GenerateObservationDatesAndWeightsDefault.class)
public abstract class GenerateObservationDatesAndWeights implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected ComputeCalculationPeriod computeCalculationPeriod;
	@Inject protected DetermineObservationPeriod determineObservationPeriod;
	@Inject protected GenerateObservationDates generateObservationDates;
	@Inject protected GenerateWeightings generateWeightings;
	@Inject protected GetAllBusinessCenters getAllBusinessCenters;

	/**
	* @param calculationParams Floating rate definition for the calculated rate.
	* @param resetDates Reset structure (needed only for fallback rates, otherwise will be empty.
	* @param calculationPeriod Calculation period for which we want to determine the rate.
	* @param priorCalculationPeriod The prior calculation period (needed only for set in advance observation shift rate.
	* @return results observation dates and corresponding weight.
	*/
	public CalculatedRateObservationDatesAndWeights evaluate(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod) {
		CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder resultsBuilder = doEvaluate(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod);
		
		final CalculatedRateObservationDatesAndWeights results;
		if (resultsBuilder == null) {
			results = null;
		} else {
			results = resultsBuilder.build();
			objectValidator.validate(CalculatedRateObservationDatesAndWeights.class, results);
		}
		
		return results;
	}

	protected abstract CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder doEvaluate(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod);

	protected abstract MapperS<? extends ObservationShiftCalculation> obsShift(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod);

	protected abstract MapperS<? extends OffsetCalculation> lockout(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod);

	protected abstract MapperS<Integer> specifiedLockout(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod);

	protected abstract MapperS<Integer> lockoutDays(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod);

	protected abstract MapperC<BusinessCenterEnum> businessDays(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod);

	protected abstract MapperS<ObservationPeriodDatesEnum> calculateRelative(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod);

	protected abstract MapperS<? extends CalculationPeriodBase> adjustedCalculationPeriod(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod);

	protected abstract MapperS<? extends CalculationPeriodBase> observationPeriod(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod);

	protected abstract MapperC<Date> observationDates(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod);

	public static class GenerateObservationDatesAndWeightsDefault extends GenerateObservationDatesAndWeights {
		@Override
		protected CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder doEvaluate(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod) {
			CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder results = CalculatedRateObservationDatesAndWeights.builder();
			return assignOutput(results, calculationParams, resetDates, calculationPeriod, priorCalculationPeriod);
		}
		
		protected CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder assignOutput(CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder results, FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod) {
			results
				.addObservationDates(observationDates(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod).getMulti());
			
			results
				.addWeights(generateWeightings.evaluate(calculationParams, MapperS.of(results).<Date>mapC("getObservationDates", calculatedRateObservationDatesAndWeights -> calculatedRateObservationDatesAndWeights.getObservationDates()).getMulti(), observationPeriod(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod).get(), adjustedCalculationPeriod(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod).get(), lockoutDays(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod).get()));
			
			return Optional.ofNullable(results)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends ObservationShiftCalculation> obsShift(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod) {
			return MapperS.of(calculationParams).<ObservationShiftCalculation>map("getObservationShiftCalculation", floatingRateCalculationParameters -> floatingRateCalculationParameters.getObservationShiftCalculation());
		}
		
		@Override
		protected MapperS<? extends OffsetCalculation> lockout(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod) {
			return MapperS.of(calculationParams).<OffsetCalculation>map("getLockoutCalculation", floatingRateCalculationParameters -> floatingRateCalculationParameters.getLockoutCalculation());
		}
		
		@Override
		protected MapperS<Integer> specifiedLockout(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod) {
			if (exists(lockout(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod).<Integer>map("getOffsetDays", offsetCalculation -> offsetCalculation.getOffsetDays())).getOrDefault(false)) {
				return lockout(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod).<Integer>map("getOffsetDays", offsetCalculation -> offsetCalculation.getOffsetDays());
			}
			return MapperS.of(5);
		}
		
		@Override
		protected MapperS<Integer> lockoutDays(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod) {
			if (exists(lockout(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod)).getOrDefault(false)) {
				return specifiedLockout(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod);
			}
			return MapperS.of(0);
		}
		
		@Override
		protected MapperC<BusinessCenterEnum> businessDays(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod) {
			return MapperC.<BusinessCenterEnum>of(getAllBusinessCenters.evaluate(MapperS.of(calculationParams).<BusinessCenters>map("getApplicableBusinessDays", floatingRateCalculationParameters -> floatingRateCalculationParameters.getApplicableBusinessDays()).get()));
		}
		
		@Override
		protected MapperS<ObservationPeriodDatesEnum> calculateRelative(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod) {
			if (exists(obsShift(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod).<ObservationPeriodDatesEnum>map("getCalculationBase", observationShiftCalculation -> observationShiftCalculation.getCalculationBase())).getOrDefault(false)) {
				return obsShift(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod).<ObservationPeriodDatesEnum>map("getCalculationBase", observationShiftCalculation -> observationShiftCalculation.getCalculationBase());
			}
			return MapperS.of(ObservationPeriodDatesEnum.STANDARD);
		}
		
		@Override
		protected MapperS<? extends CalculationPeriodBase> adjustedCalculationPeriod(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod) {
			return MapperS.of(computeCalculationPeriod.evaluate(calculationPeriod, priorCalculationPeriod, calculateRelative(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod).get(), resetDates));
		}
		
		@Override
		protected MapperS<? extends CalculationPeriodBase> observationPeriod(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod) {
			return MapperS.of(determineObservationPeriod.evaluate(adjustedCalculationPeriod(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod).get(), calculationParams));
		}
		
		@Override
		protected MapperC<Date> observationDates(FloatingRateCalculationParameters calculationParams, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod) {
			return MapperC.<Date>of(generateObservationDates.evaluate(observationPeriod(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod).get(), businessDays(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod).getMulti(), lockoutDays(calculationParams, resetDates, calculationPeriod, priorCalculationPeriod).get()));
		}
	}
}
