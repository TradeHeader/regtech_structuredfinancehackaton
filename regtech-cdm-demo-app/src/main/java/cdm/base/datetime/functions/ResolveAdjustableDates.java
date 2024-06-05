package cdm.base.datetime.functions;

import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;
import java.util.ArrayList;
import java.util.List;


@ImplementedBy(ResolveAdjustableDates.ResolveAdjustableDatesDefault.class)
public abstract class ResolveAdjustableDates implements RosettaFunction {

	/**
	* @param adjustableRelativeOrPeriodicDates 
	* @return adjustedDates 
	*/
	public List<Date> evaluate(AdjustableRelativeOrPeriodicDates adjustableRelativeOrPeriodicDates) {
		List<Date> adjustedDates = doEvaluate(adjustableRelativeOrPeriodicDates);
		
		return adjustedDates;
	}

	protected abstract List<Date> doEvaluate(AdjustableRelativeOrPeriodicDates adjustableRelativeOrPeriodicDates);

	public static class ResolveAdjustableDatesDefault extends ResolveAdjustableDates {
		@Override
		protected List<Date> doEvaluate(AdjustableRelativeOrPeriodicDates adjustableRelativeOrPeriodicDates) {
			List<Date> adjustedDates = new ArrayList<>();
			return assignOutput(adjustedDates, adjustableRelativeOrPeriodicDates);
		}
		
		protected List<Date> assignOutput(List<Date> adjustedDates, AdjustableRelativeOrPeriodicDates adjustableRelativeOrPeriodicDates) {
			return adjustedDates;
		}
	}
}
