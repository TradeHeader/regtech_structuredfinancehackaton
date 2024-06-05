package cdm.base.datetime.functions;

import cdm.base.datetime.BusinessCenterEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;
import java.util.ArrayList;
import java.util.List;


@ImplementedBy(BusinessCenterHolidays.BusinessCenterHolidaysDefault.class)
public abstract class BusinessCenterHolidays implements RosettaFunction {

	/**
	* @param businessCenter 
	* @return holidayDates 
	*/
	public List<Date> evaluate(BusinessCenterEnum businessCenter) {
		List<Date> holidayDates = doEvaluate(businessCenter);
		
		return holidayDates;
	}

	protected abstract List<Date> doEvaluate(BusinessCenterEnum businessCenter);

	public static class BusinessCenterHolidaysDefault extends BusinessCenterHolidays {
		@Override
		protected List<Date> doEvaluate(BusinessCenterEnum businessCenter) {
			List<Date> holidayDates = new ArrayList<>();
			return assignOutput(holidayDates, businessCenter);
		}
		
		protected List<Date> assignOutput(List<Date> holidayDates, BusinessCenterEnum businessCenter) {
			return holidayDates;
		}
	}
}
