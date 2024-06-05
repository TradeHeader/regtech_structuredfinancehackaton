package cdm.base.datetime.functions;

import cdm.base.datetime.BusinessCenterEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(AddBusinessDays.AddBusinessDaysDefault.class)
public abstract class AddBusinessDays implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected cdm.base.datetime.functions.AddBusinessDays addBusinessDays;
	@Inject protected AddDays addDays;
	@Inject protected IsBusinessDay isBusinessDay;

	/**
	* @param originalDate date to be shifted. If not a good business day, a supplied shift of 0 will shift it to the next business day
	* @param offsetBusinessDays number of business days to shift the original date
	* @param businessCenters business centers to use in the shifting
	* @return shiftedDate 
	*/
	public Date evaluate(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters) {
		Date shiftedDate = doEvaluate(originalDate, offsetBusinessDays, businessCenters);
		
		return shiftedDate;
	}

	protected abstract Date doEvaluate(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperS<Boolean> isGoodBusinessDay(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperS<Integer> shift(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperS<Date> shiftedByOne(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperS<Boolean> isShiftedGood(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperS<Integer> newShift(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperS<Integer> newOffset(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperS<Boolean> done(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperS<Date> newDate(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters);

	public static class AddBusinessDaysDefault extends AddBusinessDays {
		@Override
		protected Date doEvaluate(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters) {
			if (businessCenters == null) {
				businessCenters = Collections.emptyList();
			}
			Date shiftedDate = null;
			return assignOutput(shiftedDate, originalDate, offsetBusinessDays, businessCenters);
		}
		
		protected Date assignOutput(Date shiftedDate, Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters) {
			shiftedDate = newDate(originalDate, offsetBusinessDays, businessCenters).get();
			
			return shiftedDate;
		}
		
		@Override
		protected MapperS<Boolean> isGoodBusinessDay(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters) {
			return MapperS.of(isBusinessDay.evaluate(originalDate, businessCenters));
		}
		
		@Override
		protected MapperS<Integer> shift(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters) {
			if (lessThan(MapperS.of(offsetBusinessDays), MapperS.of(0), CardinalityOperator.All).getOrDefault(false)) {
				return MapperS.of(-1);
			}
			return MapperS.of(1);
		}
		
		@Override
		protected MapperS<Date> shiftedByOne(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters) {
			return MapperS.of(addDays.evaluate(originalDate, shift(originalDate, offsetBusinessDays, businessCenters).get()));
		}
		
		@Override
		protected MapperS<Boolean> isShiftedGood(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters) {
			return MapperS.of(isBusinessDay.evaluate(shiftedByOne(originalDate, offsetBusinessDays, businessCenters).get(), businessCenters));
		}
		
		@Override
		protected MapperS<Integer> newShift(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters) {
			if (isShiftedGood(originalDate, offsetBusinessDays, businessCenters).getOrDefault(false)) {
				return shift(originalDate, offsetBusinessDays, businessCenters);
			}
			return MapperS.of(0);
		}
		
		@Override
		protected MapperS<Integer> newOffset(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters) {
			if (areEqual(MapperS.of(offsetBusinessDays), MapperS.of(0), CardinalityOperator.All).getOrDefault(false)) {
				return MapperS.of(0);
			}
			return MapperMaths.<Integer, Integer, Integer>subtract(MapperS.of(offsetBusinessDays), newShift(originalDate, offsetBusinessDays, businessCenters));
		}
		
		@Override
		protected MapperS<Boolean> done(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters) {
			return areEqual(MapperS.of(offsetBusinessDays), MapperS.of(0), CardinalityOperator.All).and(areEqual(isGoodBusinessDay(originalDate, offsetBusinessDays, businessCenters), MapperS.of(true), CardinalityOperator.All)).asMapper();
		}
		
		@Override
		protected MapperS<Date> newDate(Date originalDate, Integer offsetBusinessDays, List<BusinessCenterEnum> businessCenters) {
			if (done(originalDate, offsetBusinessDays, businessCenters).getOrDefault(false)) {
				return MapperS.of(originalDate);
			}
			return MapperS.of(addBusinessDays.evaluate(shiftedByOne(originalDate, offsetBusinessDays, businessCenters).get(), newOffset(originalDate, offsetBusinessDays, businessCenters).get(), businessCenters));
		}
	}
}
