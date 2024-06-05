package cdm.base.datetime.functions;

import cdm.base.datetime.BusinessCenterEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperListOfLists;
import com.rosetta.model.lib.records.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(BusinessCenterHolidaysMultiple.BusinessCenterHolidaysMultipleDefault.class)
public abstract class BusinessCenterHolidaysMultiple implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected BusinessCenterHolidays businessCenterHolidays;

	/**
	* @param businessCenters The business centers for which the holiday list is required.
	* @return holidayDates The combined list of holidays in all of the supplied business centers.
	*/
	public List<Date> evaluate(List<BusinessCenterEnum> businessCenters) {
		List<Date> holidayDates = doEvaluate(businessCenters);
		
		return holidayDates;
	}

	protected abstract List<Date> doEvaluate(List<BusinessCenterEnum> businessCenters);

	public static class BusinessCenterHolidaysMultipleDefault extends BusinessCenterHolidaysMultiple {
		@Override
		protected List<Date> doEvaluate(List<BusinessCenterEnum> businessCenters) {
			if (businessCenters == null) {
				businessCenters = Collections.emptyList();
			}
			List<Date> holidayDates = new ArrayList<>();
			return assignOutput(holidayDates, businessCenters);
		}
		
		protected List<Date> assignOutput(List<Date> holidayDates, List<BusinessCenterEnum> businessCenters) {
			final MapperListOfLists<Date> thenResult0 = MapperC.<BusinessCenterEnum>of(businessCenters)
				.mapItemToList(item -> MapperC.<Date>of(businessCenterHolidays.evaluate(item.get())));
			final MapperC<Date> thenResult1 = thenResult0
				.flattenList();
			final MapperC<Date> thenResult2 = distinct(thenResult1);
			holidayDates.addAll(thenResult2
				.sort().getMulti());
			
			return holidayDates;
		}
	}
}
