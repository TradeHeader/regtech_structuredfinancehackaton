package cdm.base.datetime.functions;

import cdm.base.datetime.BusinessCenterEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;


@ImplementedBy(IsBusinessDay.IsBusinessDayDefault.class)
public abstract class IsBusinessDay implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected IsHoliday isHoliday;
	@Inject protected IsWeekend isWeekend;

	/**
	* @param date The date for which we want to determine whether it&#39;s a good business day
	* @param businessCenters The list of business centers to use
	* @return isGoodBusinessDay True if a good business day, false if a weekend or holiday
	*/
	public Boolean evaluate(Date date, List<BusinessCenterEnum> businessCenters) {
		Boolean isGoodBusinessDay = doEvaluate(date, businessCenters);
		
		return isGoodBusinessDay;
	}

	protected abstract Boolean doEvaluate(Date date, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperS<Boolean> weekend(Date date, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperS<Boolean> holiday(Date date, List<BusinessCenterEnum> businessCenters);

	public static class IsBusinessDayDefault extends IsBusinessDay {
		@Override
		protected Boolean doEvaluate(Date date, List<BusinessCenterEnum> businessCenters) {
			if (businessCenters == null) {
				businessCenters = Collections.emptyList();
			}
			Boolean isGoodBusinessDay = null;
			return assignOutput(isGoodBusinessDay, date, businessCenters);
		}
		
		protected Boolean assignOutput(Boolean isGoodBusinessDay, Date date, List<BusinessCenterEnum> businessCenters) {
			if (weekend(date, businessCenters).getOrDefault(false)) {
				isGoodBusinessDay = false;
			} else if (holiday(date, businessCenters).getOrDefault(false)) {
				isGoodBusinessDay = false;
			} else {
				isGoodBusinessDay = true;
			}
			
			return isGoodBusinessDay;
		}
		
		@Override
		protected MapperS<Boolean> weekend(Date date, List<BusinessCenterEnum> businessCenters) {
			return MapperS.of(isWeekend.evaluate(date, businessCenters));
		}
		
		@Override
		protected MapperS<Boolean> holiday(Date date, List<BusinessCenterEnum> businessCenters) {
			return MapperS.of(isHoliday.evaluate(date, businessCenters));
		}
	}
}
