package cdm.base.datetime.functions;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.DayOfWeekEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(IsWeekend.IsWeekendDefault.class)
public abstract class IsWeekend implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected DayOfWeek dayOfWeek0;

	/**
	* @param date The date for which the weekday is needed
	* @param businessCenters Not needed for the current implementation so ignored, but kept for future extensibility
	* @return isWeekend 
	*/
	public Boolean evaluate(Date date, List<BusinessCenterEnum> businessCenters) {
		Boolean isWeekend = doEvaluate(date, businessCenters);
		
		return isWeekend;
	}

	protected abstract Boolean doEvaluate(Date date, List<BusinessCenterEnum> businessCenters);

	protected abstract MapperS<DayOfWeekEnum> dayOfWeek1(Date date, List<BusinessCenterEnum> businessCenters);

	public static class IsWeekendDefault extends IsWeekend {
		@Override
		protected Boolean doEvaluate(Date date, List<BusinessCenterEnum> businessCenters) {
			if (businessCenters == null) {
				businessCenters = Collections.emptyList();
			}
			Boolean isWeekend = null;
			return assignOutput(isWeekend, date, businessCenters);
		}
		
		protected Boolean assignOutput(Boolean isWeekend, Date date, List<BusinessCenterEnum> businessCenters) {
			isWeekend = areEqual(dayOfWeek1(date, businessCenters), MapperS.of(DayOfWeekEnum.SAT), CardinalityOperator.All).or(areEqual(dayOfWeek1(date, businessCenters), MapperS.of(DayOfWeekEnum.SUN), CardinalityOperator.All)).get();
			
			return isWeekend;
		}
		
		@Override
		protected MapperS<DayOfWeekEnum> dayOfWeek1(Date date, List<BusinessCenterEnum> businessCenters) {
			return MapperS.of(dayOfWeek0.evaluate(date));
		}
	}
}
