package cdm.observable.asset.calculatedrate.functions;

import cdm.base.datetime.daycount.DayCountFractionEnum;
import cdm.base.datetime.daycount.functions.YearFractionForOneDay;
import cdm.observable.asset.FloatingRateOption;
import cdm.observable.asset.calculatedrate.CalculatedRateDetails;
import cdm.observable.asset.calculatedrate.CalculatedRateObservationDatesAndWeights;
import cdm.observable.asset.calculatedrate.CalculationMethodEnum;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.fro.functions.IndexValueObservationMultiple;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder;
import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.ResetDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(EvaluateCalculatedRate.EvaluateCalculatedRateDefault.class)
public abstract class EvaluateCalculatedRate implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected ApplyAveragingFormula applyAveragingFormula;
	@Inject protected ApplyCompoundingFormula applyCompoundingFormula;
	@Inject protected GenerateObservationDatesAndWeights generateObservationDatesAndWeights;
	@Inject protected IndexValueObservationMultiple indexValueObservationMultiple;
	@Inject protected ProcessObservations processObservations;
	@Inject protected YearFractionForOneDay yearFractionForOneDay;

	/**
	* @param floatingRateOption The base floating rate inde.
	* @param calculationParameters Floating rate definition for the calculated rate.
	* @param resetDates Reset structure (needed only for fallback rates, otherwise will be empty).
	* @param calculationPeriod Calculation period for which we want to determine the rate.
	* @param priorCalculationPeriod The prior calculation period (needed only for set in advance observation shift rate.
	* @param dayCount The day count fraction in effect on the stream.
	* @return results detailed results of the floating rate calculation.
	*/
	public FloatingRateSettingDetails evaluate(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount) {
		FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder resultsBuilder = doEvaluate(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount);
		
		final FloatingRateSettingDetails results;
		if (resultsBuilder == null) {
			results = null;
		} else {
			results = resultsBuilder.build();
			objectValidator.validate(FloatingRateSettingDetails.class, results);
		}
		
		return results;
	}

	protected abstract FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount);

	protected abstract MapperS<? extends FloatingRateOption> fro(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount);

	protected abstract MapperS<? extends CalculatedRateObservationDatesAndWeights> datesAndWeights(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount);

	protected abstract MapperC<Date> observationDates(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount);

	protected abstract MapperC<BigDecimal> observations(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount);

	protected abstract MapperC<BigDecimal> processedObservations(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount);

	protected abstract MapperS<CalculationMethodEnum> calculationMethod(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount);

	protected abstract MapperS<Boolean> isCompounding(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount);

	protected abstract MapperC<BigDecimal> weights(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount);

	protected abstract MapperS<BigDecimal> yearFraction(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount);

	protected abstract MapperS<? extends CalculatedRateDetails> calculationResults(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount);

	public static class EvaluateCalculatedRateDefault extends EvaluateCalculatedRate {
		@Override
		protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount) {
			FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder results = FloatingRateSettingDetails.builder();
			return assignOutput(results, floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount);
		}
		
		protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder assignOutput(FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder results, FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount) {
			results
				.setCalculationDetails(calculationResults(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).get());
			
			results
				.getOrCreateCalculationDetails()
				.getOrCreateObservations()
				.addObservationDates(datesAndWeights(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).<Date>mapC("getObservationDates", calculatedRateObservationDatesAndWeights -> calculatedRateObservationDatesAndWeights.getObservationDates()).getMulti());
			
			results
				.getOrCreateCalculationDetails()
				.getOrCreateObservations()
				.addWeights(datesAndWeights(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).<BigDecimal>mapC("getWeights", calculatedRateObservationDatesAndWeights -> calculatedRateObservationDatesAndWeights.getWeights()).getMulti());
			
			results
				.getOrCreateCalculationDetails()
				.getOrCreateObservations()
				.addObservedRates(observations(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).getMulti());
			
			results
				.getOrCreateCalculationDetails()
				.getOrCreateObservations()
				.addProcessedRates(processedObservations(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).getMulti());
			
			results
				.setFloatingRate(calculationResults(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).<BigDecimal>map("getCalculatedRate", calculatedRateDetails -> calculatedRateDetails.getCalculatedRate()).get());
			
			return Optional.ofNullable(results)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends FloatingRateOption> fro(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount) {
			return MapperS.of(floatingRateOption);
		}
		
		@Override
		protected MapperS<? extends CalculatedRateObservationDatesAndWeights> datesAndWeights(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount) {
			return MapperS.of(generateObservationDatesAndWeights.evaluate(calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod));
		}
		
		@Override
		protected MapperC<Date> observationDates(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount) {
			return datesAndWeights(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).<Date>mapC("getObservationDates", calculatedRateObservationDatesAndWeights -> calculatedRateObservationDatesAndWeights.getObservationDates());
		}
		
		@Override
		protected MapperC<BigDecimal> observations(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount) {
			return MapperC.<BigDecimal>of(indexValueObservationMultiple.evaluate(observationDates(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).getMulti(), fro(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).get()));
		}
		
		@Override
		protected MapperC<BigDecimal> processedObservations(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount) {
			return MapperC.<BigDecimal>of(processObservations.evaluate(calculationParameters, observations(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).getMulti()));
		}
		
		@Override
		protected MapperS<CalculationMethodEnum> calculationMethod(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount) {
			return MapperS.of(calculationParameters).<CalculationMethodEnum>map("getCalculationMethod", floatingRateCalculationParameters -> floatingRateCalculationParameters.getCalculationMethod());
		}
		
		@Override
		protected MapperS<Boolean> isCompounding(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount) {
			return areEqual(calculationMethod(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount), MapperS.of(CalculationMethodEnum.COMPOUNDING), CardinalityOperator.All).asMapper();
		}
		
		@Override
		protected MapperC<BigDecimal> weights(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount) {
			return datesAndWeights(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).<BigDecimal>mapC("getWeights", calculatedRateObservationDatesAndWeights -> calculatedRateObservationDatesAndWeights.getWeights());
		}
		
		@Override
		protected MapperS<BigDecimal> yearFraction(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount) {
			return MapperS.of(yearFractionForOneDay.evaluate(dayCount));
		}
		
		@Override
		protected MapperS<? extends CalculatedRateDetails> calculationResults(FloatingRateOption floatingRateOption, FloatingRateCalculationParameters calculationParameters, ResetDates resetDates, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, DayCountFractionEnum dayCount) {
			if (isCompounding(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).getOrDefault(false)) {
				return MapperS.of(applyCompoundingFormula.evaluate(processedObservations(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).getMulti(), weights(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).getMulti(), yearFraction(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).get()));
			}
			return MapperS.of(applyAveragingFormula.evaluate(observations(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).getMulti(), weights(floatingRateOption, calculationParameters, resetDates, calculationPeriod, priorCalculationPeriod, dayCount).getMulti()));
		}
	}
}
