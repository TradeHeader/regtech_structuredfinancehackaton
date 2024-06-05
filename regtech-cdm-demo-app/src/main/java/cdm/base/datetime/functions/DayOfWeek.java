package cdm.base.datetime.functions;

import cdm.base.datetime.DayOfWeekEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;


@ImplementedBy(DayOfWeek.DayOfWeekDefault.class)
public abstract class DayOfWeek implements RosettaFunction {

	/**
	* @param date The date for which the weekday is needed.
	* @return dayOfWeek The day of the week as an enumerated value.
	*/
	public DayOfWeekEnum evaluate(Date date) {
		DayOfWeekEnum dayOfWeek = doEvaluate(date);
		
		return dayOfWeek;
	}

	protected abstract DayOfWeekEnum doEvaluate(Date date);

	public static class DayOfWeekDefault extends DayOfWeek {
		@Override
		protected DayOfWeekEnum doEvaluate(Date date) {
			DayOfWeekEnum dayOfWeek = null;
			return assignOutput(dayOfWeek, date);
		}
		
		protected DayOfWeekEnum assignOutput(DayOfWeekEnum dayOfWeek, Date date) {
			return dayOfWeek;
		}
	}
}
