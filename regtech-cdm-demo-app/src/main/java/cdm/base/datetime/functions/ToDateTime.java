package cdm.base.datetime.functions;

import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.inject.Inject;


@ImplementedBy(ToDateTime.ToDateTimeDefault.class)
public abstract class ToDateTime implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected ToTime toTime;

	/**
	* @param date 
	* @return zonedDateTime 
	*/
	public ZonedDateTime evaluate(Date date) {
		ZonedDateTime zonedDateTime = doEvaluate(date);
		
		return zonedDateTime;
	}

	protected abstract ZonedDateTime doEvaluate(Date date);

	public static class ToDateTimeDefault extends ToDateTime {
		@Override
		protected ZonedDateTime doEvaluate(Date date) {
			ZonedDateTime zonedDateTime = null;
			return assignOutput(zonedDateTime, date);
		}
		
		protected ZonedDateTime assignOutput(ZonedDateTime zonedDateTime, Date date) {
			final LocalTime time = toTime.evaluate(0, 0, 0);
			final String timezone = "Z";
			if (date != null && time != null && timezone != null) {
				zonedDateTime = ZonedDateTime.of(date.toLocalDate(), time, ZoneId.of(timezone));
			} else {
				zonedDateTime = null;
			}
			
			return zonedDateTime;
		}
	}
}
