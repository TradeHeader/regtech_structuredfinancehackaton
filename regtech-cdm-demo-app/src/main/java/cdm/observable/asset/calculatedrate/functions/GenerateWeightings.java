package cdm.observable.asset.calculatedrate.functions;

import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.product.common.schedule.CalculationPeriodBase;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;


@ImplementedBy(GenerateWeightings.GenerateWeightingsDefault.class)
public abstract class GenerateWeightings implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected DetermineWeightingDates determineWeightingDates;
	@Inject protected GenerateWeights generateWeights;

	/**
	* @param calculationParams Floating rate definition for the calculated rate.
	* @param observationDates 
	* @param observationPeriod The resulting observation period.
	* @param adjustedCalculationPeriod The calculation period for which the rate is being computed, after any adjustment.
	* @param lockoutDays The number of lockout day.
	* @return weights A vector of weights, typically numbers between 1 and 3.
	*/
	public List<BigDecimal> evaluate(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays) {
		List<BigDecimal> weights = doEvaluate(calculationParams, observationDates, observationPeriod, adjustedCalculationPeriod, lockoutDays);
		
		return weights;
	}

	protected abstract List<BigDecimal> doEvaluate(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays);

	protected abstract MapperC<Date> weightingDates(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays);

	public static class GenerateWeightingsDefault extends GenerateWeightings {
		@Override
		protected List<BigDecimal> doEvaluate(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays) {
			if (observationDates == null) {
				observationDates = Collections.emptyList();
			}
			List<BigDecimal> weights = new ArrayList<>();
			return assignOutput(weights, calculationParams, observationDates, observationPeriod, adjustedCalculationPeriod, lockoutDays);
		}
		
		protected List<BigDecimal> assignOutput(List<BigDecimal> weights, FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays) {
			weights = generateWeights.evaluate(weightingDates(calculationParams, observationDates, observationPeriod, adjustedCalculationPeriod, lockoutDays).getMulti());
			
			return weights;
		}
		
		@Override
		protected MapperC<Date> weightingDates(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays) {
			return MapperC.<Date>of(determineWeightingDates.evaluate(calculationParams, observationDates, observationPeriod, adjustedCalculationPeriod, lockoutDays));
		}
	}
}
