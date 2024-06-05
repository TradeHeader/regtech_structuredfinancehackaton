package cdm.base.datetime.functions;

import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;


@ImplementedBy(LeapYearDateDifference.LeapYearDateDifferenceDefault.class)
public abstract class LeapYearDateDifference implements RosettaFunction {

	/**
	* @param firstDate The left side of the subtraction.
	* @param secondDate The right side of the subtraction.
	* @return difference The number of leap year calendar days secondDate is after firstDate. Negative means secondDate is before firstDate.
	*/
	public Integer evaluate(Date firstDate, Date secondDate) {
		Integer difference = doEvaluate(firstDate, secondDate);
		
		return difference;
	}

	protected abstract Integer doEvaluate(Date firstDate, Date secondDate);

	public static class LeapYearDateDifferenceDefault extends LeapYearDateDifference {
		@Override
		protected Integer doEvaluate(Date firstDate, Date secondDate) {
			Integer difference = null;
			return assignOutput(difference, firstDate, secondDate);
		}
		
		protected Integer assignOutput(Integer difference, Date firstDate, Date secondDate) {
			return difference;
		}
	}
}
