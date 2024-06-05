package cdm.observable.asset.calculatedrate.functions;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.functions.AddBusinessDays;
import cdm.base.datetime.functions.GenerateDateList;
import cdm.product.common.schedule.CalculationPeriodBase;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(GenerateObservationDates.GenerateObservationDatesDefault.class)
public abstract class GenerateObservationDates implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected AddBusinessDays addBusinessDays;
	@Inject protected GenerateDateList generateDateList;

	/**
	* @param observationPeriod The given observation period.
	* @param businessCenters The observation date.
	* @param lockoutDays The number of lockout date.
	* @return observationDates The resulting list of observation date.
	*/
	public List<Date> evaluate(CalculationPeriodBase observationPeriod, List<BusinessCenterEnum> businessCenters, Integer lockoutDays) {
		List<Date> observationDates = doEvaluate(observationPeriod, businessCenters, lockoutDays);
		
		return observationDates;
	}

	protected abstract List<Date> doEvaluate(CalculationPeriodBase observationPeriod, List<BusinessCenterEnum> businessCenters, Integer lockoutDays);

	protected abstract MapperS<Integer> days(CalculationPeriodBase observationPeriod, List<BusinessCenterEnum> businessCenters, Integer lockoutDays);

	protected abstract MapperS<Date> endDate(CalculationPeriodBase observationPeriod, List<BusinessCenterEnum> businessCenters, Integer lockoutDays);

	public static class GenerateObservationDatesDefault extends GenerateObservationDates {
		@Override
		protected List<Date> doEvaluate(CalculationPeriodBase observationPeriod, List<BusinessCenterEnum> businessCenters, Integer lockoutDays) {
			if (businessCenters == null) {
				businessCenters = Collections.emptyList();
			}
			List<Date> observationDates = new ArrayList<>();
			return assignOutput(observationDates, observationPeriod, businessCenters, lockoutDays);
		}
		
		protected List<Date> assignOutput(List<Date> observationDates, CalculationPeriodBase observationPeriod, List<BusinessCenterEnum> businessCenters, Integer lockoutDays) {
			observationDates.addAll(generateDateList.evaluate(MapperS.of(observationPeriod).<Date>map("getAdjustedStartDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedStartDate()).get(), endDate(observationPeriod, businessCenters, lockoutDays).get(), businessCenters));
			
			return observationDates;
		}
		
		@Override
		protected MapperS<Integer> days(CalculationPeriodBase observationPeriod, List<BusinessCenterEnum> businessCenters, Integer lockoutDays) {
			final MapperS<Integer> ifThenElseResult;
			if (exists(MapperS.of(lockoutDays)).getOrDefault(false)) {
				ifThenElseResult = MapperS.of(lockoutDays);
			} else {
				ifThenElseResult = MapperS.of(0);
			}
			return MapperMaths.<Integer, Integer, Integer>add(MapperS.of(1), ifThenElseResult);
		}
		
		@Override
		protected MapperS<Date> endDate(CalculationPeriodBase observationPeriod, List<BusinessCenterEnum> businessCenters, Integer lockoutDays) {
			return MapperS.of(addBusinessDays.evaluate(MapperS.of(observationPeriod).<Date>map("getAdjustedEndDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedEndDate()).get(), MapperMaths.<Integer, Integer, Integer>multiply(MapperS.of(-1), days(observationPeriod, businessCenters, lockoutDays)).get(), businessCenters));
		}
	}
}
