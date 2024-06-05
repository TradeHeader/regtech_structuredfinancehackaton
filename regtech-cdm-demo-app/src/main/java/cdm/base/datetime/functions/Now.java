package cdm.base.datetime.functions;

import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.time.ZonedDateTime;


@ImplementedBy(Now.NowDefault.class)
public abstract class Now implements RosettaFunction {

	/**
	* @return now 
	*/
	public ZonedDateTime evaluate() {
		ZonedDateTime now = doEvaluate();
		
		return now;
	}

	protected abstract ZonedDateTime doEvaluate();

	public static class NowDefault extends Now {
		@Override
		protected ZonedDateTime doEvaluate() {
			ZonedDateTime now = null;
			return assignOutput(now);
		}
		
		protected ZonedDateTime assignOutput(ZonedDateTime now) {
			return now;
		}
	}
}
