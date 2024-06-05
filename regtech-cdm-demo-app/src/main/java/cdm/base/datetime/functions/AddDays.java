package cdm.base.datetime.functions;

import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;


@ImplementedBy(AddDays.AddDaysDefault.class)
public abstract class AddDays implements RosettaFunction {

	/**
	* @param inputDate The base date for the calculation.
	* @param numDays The number of days to add.
	* @return resultDate The date shifted by the specified number of days.
	*/
	public Date evaluate(Date inputDate, Integer numDays) {
		Date resultDate = doEvaluate(inputDate, numDays);
		
		return resultDate;
	}

	protected abstract Date doEvaluate(Date inputDate, Integer numDays);

	public static class AddDaysDefault extends AddDays {
		@Override
		protected Date doEvaluate(Date inputDate, Integer numDays) {
			Date resultDate = null;
			return assignOutput(resultDate, inputDate, numDays);
		}
		
		protected Date assignOutput(Date resultDate, Date inputDate, Integer numDays) {
			return resultDate;
		}
	}
}
