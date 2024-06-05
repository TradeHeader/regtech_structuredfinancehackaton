package cdm.product.asset.floatingrate.functions;

import cdm.product.common.schedule.RateSchedule;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import javax.inject.Inject;


@ImplementedBy(GetRateScheduleAmount.GetRateScheduleAmountDefault.class)
public abstract class GetRateScheduleAmount implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected GetRateScheduleStepValues getRateScheduleStepValues;

	/**
	* @param schedule The rate schedule.
	* @param periodStartDate The start date for which you want the rate.
	* @return amount 
	*/
	public BigDecimal evaluate(RateSchedule schedule, Date periodStartDate) {
		BigDecimal amount = doEvaluate(schedule, periodStartDate);
		
		return amount;
	}

	protected abstract BigDecimal doEvaluate(RateSchedule schedule, Date periodStartDate);

	public static class GetRateScheduleAmountDefault extends GetRateScheduleAmount {
		@Override
		protected BigDecimal doEvaluate(RateSchedule schedule, Date periodStartDate) {
			BigDecimal amount = null;
			return assignOutput(amount, schedule, periodStartDate);
		}
		
		protected BigDecimal assignOutput(BigDecimal amount, RateSchedule schedule, Date periodStartDate) {
			amount = MapperC.<BigDecimal>of(getRateScheduleStepValues.evaluate(schedule, periodStartDate))
				.last().get();
			
			return amount;
		}
	}
}
