package cdm.observable.asset.calculatedrate.functions;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.functions.AddBusinessDays;
import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.CalculationPeriodBase.CalculationPeriodBaseBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(GenerateObservationPeriod.GenerateObservationPeriodDefault.class)
public abstract class GenerateObservationPeriod implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected AddBusinessDays addBusinessDays;

	/**
	* @param calculationPeriod The calculation period for which the rate is being compute.
	* @param businessCenters The business centers to be used for shifting.
	* @param shiftDays The amount of any shift.
	* @return observationPeriod The resulting observation period.
	*/
	public CalculationPeriodBase evaluate(CalculationPeriodBase calculationPeriod, List<BusinessCenterEnum> businessCenters, Integer shiftDays) {
		CalculationPeriodBase.CalculationPeriodBaseBuilder observationPeriodBuilder = doEvaluate(calculationPeriod, businessCenters, shiftDays);
		
		final CalculationPeriodBase observationPeriod;
		if (observationPeriodBuilder == null) {
			observationPeriod = null;
		} else {
			observationPeriod = observationPeriodBuilder.build();
			objectValidator.validate(CalculationPeriodBase.class, observationPeriod);
		}
		
		return observationPeriod;
	}

	protected abstract CalculationPeriodBase.CalculationPeriodBaseBuilder doEvaluate(CalculationPeriodBase calculationPeriod, List<BusinessCenterEnum> businessCenters, Integer shiftDays);

	protected abstract MapperS<Date> calcStart(CalculationPeriodBase calculationPeriod, List<BusinessCenterEnum> businessCenters, Integer shiftDays);

	protected abstract MapperS<Date> calcEnd(CalculationPeriodBase calculationPeriod, List<BusinessCenterEnum> businessCenters, Integer shiftDays);

	protected abstract MapperS<Date> obsStart(CalculationPeriodBase calculationPeriod, List<BusinessCenterEnum> businessCenters, Integer shiftDays);

	protected abstract MapperS<Date> obsEnd(CalculationPeriodBase calculationPeriod, List<BusinessCenterEnum> businessCenters, Integer shiftDays);

	public static class GenerateObservationPeriodDefault extends GenerateObservationPeriod {
		@Override
		protected CalculationPeriodBase.CalculationPeriodBaseBuilder doEvaluate(CalculationPeriodBase calculationPeriod, List<BusinessCenterEnum> businessCenters, Integer shiftDays) {
			if (businessCenters == null) {
				businessCenters = Collections.emptyList();
			}
			CalculationPeriodBase.CalculationPeriodBaseBuilder observationPeriod = CalculationPeriodBase.builder();
			return assignOutput(observationPeriod, calculationPeriod, businessCenters, shiftDays);
		}
		
		protected CalculationPeriodBase.CalculationPeriodBaseBuilder assignOutput(CalculationPeriodBase.CalculationPeriodBaseBuilder observationPeriod, CalculationPeriodBase calculationPeriod, List<BusinessCenterEnum> businessCenters, Integer shiftDays) {
			observationPeriod
				.setAdjustedStartDate(obsStart(calculationPeriod, businessCenters, shiftDays).get());
			
			observationPeriod
				.setAdjustedEndDate(obsEnd(calculationPeriod, businessCenters, shiftDays).get());
			
			return Optional.ofNullable(observationPeriod)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<Date> calcStart(CalculationPeriodBase calculationPeriod, List<BusinessCenterEnum> businessCenters, Integer shiftDays) {
			return MapperS.of(calculationPeriod).<Date>map("getAdjustedStartDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedStartDate());
		}
		
		@Override
		protected MapperS<Date> calcEnd(CalculationPeriodBase calculationPeriod, List<BusinessCenterEnum> businessCenters, Integer shiftDays) {
			return MapperS.of(calculationPeriod).<Date>map("getAdjustedEndDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedEndDate());
		}
		
		@Override
		protected MapperS<Date> obsStart(CalculationPeriodBase calculationPeriod, List<BusinessCenterEnum> businessCenters, Integer shiftDays) {
			return MapperS.of(addBusinessDays.evaluate(calcStart(calculationPeriod, businessCenters, shiftDays).get(), MapperMaths.<Integer, Integer, Integer>multiply(MapperS.of(-1), MapperS.of(shiftDays)).get(), businessCenters));
		}
		
		@Override
		protected MapperS<Date> obsEnd(CalculationPeriodBase calculationPeriod, List<BusinessCenterEnum> businessCenters, Integer shiftDays) {
			return MapperS.of(addBusinessDays.evaluate(calcEnd(calculationPeriod, businessCenters, shiftDays).get(), MapperMaths.<Integer, Integer, Integer>multiply(MapperS.of(-1), MapperS.of(shiftDays)).get(), businessCenters));
		}
	}
}
