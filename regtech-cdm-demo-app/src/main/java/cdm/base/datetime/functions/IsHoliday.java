package cdm.base.datetime.functions;

import cdm.base.datetime.BusinessCenterEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(IsHoliday.IsHolidayDefault.class)
public abstract class IsHoliday implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected BusinessCenterHolidaysMultiple businessCenterHolidaysMultiple;

	/**
	* @param checkDate The date being tested
	* @param businessCenters The business centers for which the test is required
	* @return isHoliday true if the supplied date is a holiday
	*/
	public Boolean evaluate(Date checkDate, List<BusinessCenterEnum> businessCenters) {
		Boolean isHoliday = doEvaluate(checkDate, businessCenters);
		
		return isHoliday;
	}

	protected abstract Boolean doEvaluate(Date checkDate, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperC<Date> holidays(Date checkDate, List<BusinessCenterEnum> businessCenters);

	public static class IsHolidayDefault extends IsHoliday {
		@Override
		protected Boolean doEvaluate(Date checkDate, List<BusinessCenterEnum> businessCenters) {
			if (businessCenters == null) {
				businessCenters = Collections.emptyList();
			}
			Boolean isHoliday = null;
			return assignOutput(isHoliday, checkDate, businessCenters);
		}
		
		protected Boolean assignOutput(Boolean isHoliday, Date checkDate, List<BusinessCenterEnum> businessCenters) {
			isHoliday = contains(holidays(checkDate, businessCenters), MapperS.of(checkDate)).get();
			
			return isHoliday;
		}
		
		@Override
		protected MapperC<Date> holidays(Date checkDate, List<BusinessCenterEnum> businessCenters) {
			return MapperC.<Date>of(businessCenterHolidaysMultiple.evaluate(businessCenters));
		}
	}
}
