package cdm.base.datetime.functions;

import cdm.base.datetime.AdjustableOrRelativeDate;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;


@ImplementedBy(ResolveAdjustableDate.ResolveAdjustableDateDefault.class)
public abstract class ResolveAdjustableDate implements RosettaFunction {

	/**
	* @param adjustableOrRelativeDate 
	* @return adjustedDate 
	*/
	public Date evaluate(AdjustableOrRelativeDate adjustableOrRelativeDate) {
		Date adjustedDate = doEvaluate(adjustableOrRelativeDate);
		
		return adjustedDate;
	}

	protected abstract Date doEvaluate(AdjustableOrRelativeDate adjustableOrRelativeDate);

	public static class ResolveAdjustableDateDefault extends ResolveAdjustableDate {
		@Override
		protected Date doEvaluate(AdjustableOrRelativeDate adjustableOrRelativeDate) {
			Date adjustedDate = null;
			return assignOutput(adjustedDate, adjustableOrRelativeDate);
		}
		
		protected Date assignOutput(Date adjustedDate, AdjustableOrRelativeDate adjustableOrRelativeDate) {
			return adjustedDate;
		}
	}
}
