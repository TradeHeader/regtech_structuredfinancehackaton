package cdm.base.datetime.functions;

import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@ImplementedBy(PopOffDateList.PopOffDateListDefault.class)
public abstract class PopOffDateList implements RosettaFunction {

	/**
	* @param dates List of dates.
	* @return newList The newly created list, omitting the last element of the original list.
	*/
	public List<Date> evaluate(List<Date> dates) {
		List<Date> newList = doEvaluate(dates);
		
		return newList;
	}

	protected abstract List<Date> doEvaluate(List<Date> dates);

	public static class PopOffDateListDefault extends PopOffDateList {
		@Override
		protected List<Date> doEvaluate(List<Date> dates) {
			if (dates == null) {
				dates = Collections.emptyList();
			}
			List<Date> newList = new ArrayList<>();
			return assignOutput(newList, dates);
		}
		
		protected List<Date> assignOutput(List<Date> newList, List<Date> dates) {
			return newList;
		}
	}
}
