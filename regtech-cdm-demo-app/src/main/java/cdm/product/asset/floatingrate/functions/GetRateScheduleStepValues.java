package cdm.product.asset.floatingrate.functions;

import cdm.base.math.DatedValue;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.product.common.schedule.RateSchedule;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(GetRateScheduleStepValues.GetRateScheduleStepValuesDefault.class)
public abstract class GetRateScheduleStepValues implements RosettaFunction {

	/**
	* @param schedule The rate schedule.
	* @param periodStartDate The start date for which you want the rate.
	* @return stepValues 
	*/
	public List<BigDecimal> evaluate(RateSchedule schedule, Date periodStartDate) {
		List<BigDecimal> stepValues = doEvaluate(schedule, periodStartDate);
		
		return stepValues;
	}

	protected abstract List<BigDecimal> doEvaluate(RateSchedule schedule, Date periodStartDate);

	public static class GetRateScheduleStepValuesDefault extends GetRateScheduleStepValues {
		@Override
		protected List<BigDecimal> doEvaluate(RateSchedule schedule, Date periodStartDate) {
			List<BigDecimal> stepValues = new ArrayList<>();
			return assignOutput(stepValues, schedule, periodStartDate);
		}
		
		protected List<BigDecimal> assignOutput(List<BigDecimal> stepValues, RateSchedule schedule, Date periodStartDate) {
			stepValues.addAll(MapperS.of(schedule).<ReferenceWithMetaPriceSchedule>map("getPrice", rateSchedule -> rateSchedule.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).getMulti());
			
			final MapperC<DatedValue> thenResult = MapperS.of(schedule).<ReferenceWithMetaPriceSchedule>map("getPrice", rateSchedule -> rateSchedule.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).<DatedValue>mapC("getDatedValue", measureSchedule -> measureSchedule.getDatedValue())
				.filterItemNullSafe(item -> lessThanEquals(item.<Date>map("getDate", datedValue -> datedValue.getDate()), MapperS.of(periodStartDate), CardinalityOperator.All).get());
			stepValues.addAll(thenResult
				.mapItem(item -> item.<BigDecimal>map("getValue", datedValue -> datedValue.getValue())).getMulti());
			
			return stepValues;
		}
	}
}
