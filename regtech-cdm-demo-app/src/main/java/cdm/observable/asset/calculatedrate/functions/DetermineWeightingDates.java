package cdm.observable.asset.calculatedrate.functions;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.functions.AppendDateToList;
import cdm.base.datetime.functions.GetAllBusinessCenters;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.calculatedrate.ObservationShiftCalculation;
import cdm.observable.asset.calculatedrate.OffsetCalculation;
import cdm.product.common.schedule.CalculationPeriodBase;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(DetermineWeightingDates.DetermineWeightingDatesDefault.class)
public abstract class DetermineWeightingDates implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected AppendDateToList appendDateToList;
	@Inject protected GenerateObservationDates generateObservationDates;
	@Inject protected GetAllBusinessCenters getAllBusinessCenters;

	/**
	* @param calculationParams Floating rate definition for the calculated rate.
	* @param observationDates 
	* @param observationPeriod The resulting observation period.
	* @param adjustedCalculationPeriod The calculation period for which the rate is being computed, after any adjustment.
	* @param lockoutDays The number of lockout day.
	* @return weightingDates 
	*/
	public List<Date> evaluate(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays) {
		List<Date> weightingDates = doEvaluate(calculationParams, observationDates, observationPeriod, adjustedCalculationPeriod, lockoutDays);
		
		return weightingDates;
	}

	protected abstract List<Date> doEvaluate(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays);

	protected abstract MapperS<? extends ObservationShiftCalculation> obsShift(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays);

	protected abstract MapperS<? extends OffsetCalculation> lookback(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays);

	protected abstract MapperC<BusinessCenterEnum> businessCenters(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays);

	protected abstract MapperC<Date> baseWeightingDates(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays);

	protected abstract MapperS<? extends CalculationPeriodBase> wtPeriod(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays);

	protected abstract MapperC<Date> weightingDatesAll(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays);

	public static class DetermineWeightingDatesDefault extends DetermineWeightingDates {
		@Override
		protected List<Date> doEvaluate(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays) {
			if (observationDates == null) {
				observationDates = Collections.emptyList();
			}
			List<Date> weightingDates = new ArrayList<>();
			return assignOutput(weightingDates, calculationParams, observationDates, observationPeriod, adjustedCalculationPeriod, lockoutDays);
		}
		
		protected List<Date> assignOutput(List<Date> weightingDates, FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays) {
			weightingDates.addAll(weightingDatesAll(calculationParams, observationDates, observationPeriod, adjustedCalculationPeriod, lockoutDays).getMulti());
			
			return weightingDates;
		}
		
		@Override
		protected MapperS<? extends ObservationShiftCalculation> obsShift(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays) {
			return MapperS.of(calculationParams).<ObservationShiftCalculation>map("getObservationShiftCalculation", floatingRateCalculationParameters -> floatingRateCalculationParameters.getObservationShiftCalculation());
		}
		
		@Override
		protected MapperS<? extends OffsetCalculation> lookback(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays) {
			return MapperS.of(calculationParams).<OffsetCalculation>map("getLookbackCalculation", floatingRateCalculationParameters -> floatingRateCalculationParameters.getLookbackCalculation());
		}
		
		@Override
		protected MapperC<BusinessCenterEnum> businessCenters(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays) {
			return MapperC.<BusinessCenterEnum>of(getAllBusinessCenters.evaluate(MapperS.of(calculationParams).<BusinessCenters>map("getApplicableBusinessDays", floatingRateCalculationParameters -> floatingRateCalculationParameters.getApplicableBusinessDays()).get()));
		}
		
		@Override
		protected MapperC<Date> baseWeightingDates(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays) {
			if (exists(obsShift(calculationParams, observationDates, observationPeriod, adjustedCalculationPeriod, lockoutDays)).getOrDefault(false)) {
				return MapperC.<Date>of(observationDates);
			}
			return MapperC.<Date>of(generateObservationDates.evaluate(adjustedCalculationPeriod, businessCenters(calculationParams, observationDates, observationPeriod, adjustedCalculationPeriod, lockoutDays).getMulti(), lockoutDays));
		}
		
		@Override
		protected MapperS<? extends CalculationPeriodBase> wtPeriod(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays) {
			if (exists(lookback(calculationParams, observationDates, observationPeriod, adjustedCalculationPeriod, lockoutDays)).getOrDefault(false)) {
				return MapperS.of(adjustedCalculationPeriod);
			}
			return MapperS.of(observationPeriod);
		}
		
		@Override
		protected MapperC<Date> weightingDatesAll(FloatingRateCalculationParameters calculationParams, List<Date> observationDates, CalculationPeriodBase observationPeriod, CalculationPeriodBase adjustedCalculationPeriod, Integer lockoutDays) {
			return MapperC.<Date>of(appendDateToList.evaluate(baseWeightingDates(calculationParams, observationDates, observationPeriod, adjustedCalculationPeriod, lockoutDays).getMulti(), wtPeriod(calculationParams, observationDates, observationPeriod, adjustedCalculationPeriod, lockoutDays).<Date>map("getAdjustedEndDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedEndDate()).get()));
		}
	}
}
