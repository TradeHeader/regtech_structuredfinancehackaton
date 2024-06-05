package cdm.base.datetime.functions;

import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@ImplementedBy(AppendDateToList.AppendDateToListDefault.class)
public abstract class AppendDateToList implements RosettaFunction {

	/**
	* @param origDates List of dates.
	* @param newDate Date to add to the list.
	* @return newList The newly increased list.
	*/
	public List<Date> evaluate(List<Date> origDates, Date newDate) {
		List<Date> newList = doEvaluate(origDates, newDate);
		
		return newList;
	}

	protected abstract List<Date> doEvaluate(List<Date> origDates, Date newDate);

	public static class AppendDateToListDefault extends AppendDateToList {
		@Override
		protected List<Date> doEvaluate(List<Date> origDates, Date newDate) {
			if (origDates == null) {
				origDates = Collections.emptyList();
			}
			List<Date> newList = new ArrayList<>();
			return assignOutput(newList, origDates, newDate);
		}
		
		protected List<Date> assignOutput(List<Date> newList, List<Date> origDates, Date newDate) {
			newList = new ArrayList<>(origDates);
			
			if (newDate == null) {
				newList.addAll(Collections.<Date>emptyList());
			} else {
				newList.addAll(Collections.singletonList(newDate));
			}
			
			return newList;
		}
	}
}
