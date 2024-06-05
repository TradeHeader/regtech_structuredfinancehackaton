package cdm.base.datetime.functions;

import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.time.LocalTime;


@ImplementedBy(ToTime.ToTimeDefault.class)
public abstract class ToTime implements RosettaFunction {

	/**
	* @param hours 
	* @param minutes 
	* @param seconds 
	* @return time 
	*/
	public LocalTime evaluate(Integer hours, Integer minutes, Integer seconds) {
		LocalTime time = doEvaluate(hours, minutes, seconds);
		
		return time;
	}

	protected abstract LocalTime doEvaluate(Integer hours, Integer minutes, Integer seconds);

	public static class ToTimeDefault extends ToTime {
		@Override
		protected LocalTime doEvaluate(Integer hours, Integer minutes, Integer seconds) {
			LocalTime time = null;
			return assignOutput(time, hours, minutes, seconds);
		}
		
		protected LocalTime assignOutput(LocalTime time, Integer hours, Integer minutes, Integer seconds) {
			return time;
		}
	}
}
