package cdm.base.datetime.functions;

import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;


@ImplementedBy(Today.TodayDefault.class)
public abstract class Today implements RosettaFunction {

	/**
	* @return today 
	*/
	public Date evaluate() {
		Date today = doEvaluate();
		
		return today;
	}

	protected abstract Date doEvaluate();

	public static class TodayDefault extends Today {
		@Override
		protected Date doEvaluate() {
			Date today = null;
			return assignOutput(today);
		}
		
		protected Date assignOutput(Date today) {
			return today;
		}
	}
}
