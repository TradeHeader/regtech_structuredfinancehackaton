package cdm.observable.asset.calculatedrate.functions;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.functions.GetAllBusinessCenters;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.calculatedrate.ObservationShiftCalculation;
import cdm.observable.asset.calculatedrate.OffsetCalculation;
import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.CalculationPeriodBase.CalculationPeriodBaseBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperListOfLists;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(DetermineObservationPeriod.DetermineObservationPeriodDefault.class)
public abstract class DetermineObservationPeriod implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected GenerateObservationPeriod generateObservationPeriod;
	@Inject protected GetAllBusinessCenters getAllBusinessCenters;

	/**
	* @param adjustedCalculationPeriod The calculation period for which the rate is being computed, after any adjustment.
	* @param calculationParams Floating rate definition for the calculated rate.
	* @return observationPeriod The resulting observation period.
	*/
	public CalculationPeriodBase evaluate(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams) {
		CalculationPeriodBase.CalculationPeriodBaseBuilder observationPeriodBuilder = doEvaluate(adjustedCalculationPeriod, calculationParams);
		
		final CalculationPeriodBase observationPeriod;
		if (observationPeriodBuilder == null) {
			observationPeriod = null;
		} else {
			observationPeriod = observationPeriodBuilder.build();
			objectValidator.validate(CalculationPeriodBase.class, observationPeriod);
		}
		
		return observationPeriod;
	}

	protected abstract CalculationPeriodBase.CalculationPeriodBaseBuilder doEvaluate(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams);

	protected abstract MapperS<? extends ObservationShiftCalculation> obsShift(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams);

	protected abstract MapperS<? extends OffsetCalculation> lookback(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams);

	protected abstract MapperS<? extends BusinessCenters> businessDays(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams);

	protected abstract MapperS<? extends BusinessCenters> additionalBusinessDays(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams);

	protected abstract MapperC<BusinessCenterEnum> allBusinessDays(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams);

	protected abstract MapperS<Integer> shift(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams);

	protected abstract MapperS<Integer> shiftDefaulted(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams);

	public static class DetermineObservationPeriodDefault extends DetermineObservationPeriod {
		@Override
		protected CalculationPeriodBase.CalculationPeriodBaseBuilder doEvaluate(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams) {
			CalculationPeriodBase.CalculationPeriodBaseBuilder observationPeriod = CalculationPeriodBase.builder();
			return assignOutput(observationPeriod, adjustedCalculationPeriod, calculationParams);
		}
		
		protected CalculationPeriodBase.CalculationPeriodBaseBuilder assignOutput(CalculationPeriodBase.CalculationPeriodBaseBuilder observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams) {
			observationPeriod = toBuilder(generateObservationPeriod.evaluate(adjustedCalculationPeriod, allBusinessDays(adjustedCalculationPeriod, calculationParams).getMulti(), shiftDefaulted(adjustedCalculationPeriod, calculationParams).get()));
			
			return Optional.ofNullable(observationPeriod)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends ObservationShiftCalculation> obsShift(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams) {
			return MapperS.of(calculationParams).<ObservationShiftCalculation>map("getObservationShiftCalculation", floatingRateCalculationParameters -> floatingRateCalculationParameters.getObservationShiftCalculation());
		}
		
		@Override
		protected MapperS<? extends OffsetCalculation> lookback(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams) {
			return MapperS.of(calculationParams).<OffsetCalculation>map("getLookbackCalculation", floatingRateCalculationParameters -> floatingRateCalculationParameters.getLookbackCalculation());
		}
		
		@Override
		protected MapperS<? extends BusinessCenters> businessDays(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams) {
			return MapperS.of(calculationParams).<BusinessCenters>map("getApplicableBusinessDays", floatingRateCalculationParameters -> floatingRateCalculationParameters.getApplicableBusinessDays());
		}
		
		@Override
		protected MapperS<? extends BusinessCenters> additionalBusinessDays(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams) {
			return obsShift(adjustedCalculationPeriod, calculationParams).<BusinessCenters>map("getAdditionalBusinessDays", observationShiftCalculation -> observationShiftCalculation.getAdditionalBusinessDays());
		}
		
		@Override
		protected MapperC<BusinessCenterEnum> allBusinessDays(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams) {
			final MapperListOfLists<BusinessCenterEnum> thenResult = MapperC.<BusinessCenters>of(businessDays(adjustedCalculationPeriod, calculationParams), additionalBusinessDays(adjustedCalculationPeriod, calculationParams))
				.mapItemToList(item -> MapperC.<BusinessCenterEnum>of(getAllBusinessCenters.evaluate(item.get())));
			return thenResult
				.flattenList();
		}
		
		@Override
		protected MapperS<Integer> shift(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams) {
			if (exists(obsShift(adjustedCalculationPeriod, calculationParams)).getOrDefault(false)) {
				return obsShift(adjustedCalculationPeriod, calculationParams).<Integer>map("getOffsetDays", observationShiftCalculation -> observationShiftCalculation.getOffsetDays());
			}
			if (exists(lookback(adjustedCalculationPeriod, calculationParams)).getOrDefault(false)) {
				return lookback(adjustedCalculationPeriod, calculationParams).<Integer>map("getOffsetDays", offsetCalculation -> offsetCalculation.getOffsetDays());
			}
			return MapperS.of(0);
		}
		
		@Override
		protected MapperS<Integer> shiftDefaulted(CalculationPeriodBase adjustedCalculationPeriod, FloatingRateCalculationParameters calculationParams) {
			if (exists(shift(adjustedCalculationPeriod, calculationParams)).getOrDefault(false)) {
				return shift(adjustedCalculationPeriod, calculationParams);
			}
			return MapperS.of(5);
		}
	}
}
