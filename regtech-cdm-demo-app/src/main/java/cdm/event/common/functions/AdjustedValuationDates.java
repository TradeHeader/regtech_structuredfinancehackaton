package cdm.event.common.functions;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.base.datetime.functions.ResolveAdjustableDate;
import cdm.base.datetime.functions.ResolveAdjustableDates;
import cdm.observable.asset.PerformanceValuationDates;
import cdm.observable.asset.ValuationDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;


@ImplementedBy(AdjustedValuationDates.AdjustedValuationDatesDefault.class)
public abstract class AdjustedValuationDates implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected ResolveAdjustableDate resolveAdjustableDate;
	@Inject protected ResolveAdjustableDates resolveAdjustableDates;

	/**
	* @param valuationDates 
	* @return adjustedValuationDates 
	*/
	public List<Date> evaluate(ValuationDates valuationDates) {
		List<Date> adjustedValuationDates = doEvaluate(valuationDates);
		
		return adjustedValuationDates;
	}

	protected abstract List<Date> doEvaluate(ValuationDates valuationDates);

	public static class AdjustedValuationDatesDefault extends AdjustedValuationDates {
		@Override
		protected List<Date> doEvaluate(ValuationDates valuationDates) {
			List<Date> adjustedValuationDates = new ArrayList<>();
			return assignOutput(adjustedValuationDates, valuationDates);
		}
		
		protected List<Date> assignOutput(List<Date> adjustedValuationDates, ValuationDates valuationDates) {
			adjustedValuationDates = MapperC.<Date>of(MapperC.<Date>of(resolveAdjustableDates.evaluate(MapperS.of(valuationDates).<PerformanceValuationDates>map("getValuationDatesInterim", _valuationDates -> _valuationDates.getValuationDatesInterim()).<AdjustableRelativeOrPeriodicDates>map("getValuationDates", performanceValuationDates -> performanceValuationDates.getValuationDates()).get())), MapperS.of(resolveAdjustableDate.evaluate(MapperS.of(valuationDates).<PerformanceValuationDates>map("getValuationDatesFinal", _valuationDates -> _valuationDates.getValuationDatesFinal()).<AdjustableOrRelativeDate>map("getValuationDate", performanceValuationDates -> performanceValuationDates.getValuationDate()).get())))
				.sort().getMulti();
			
			return adjustedValuationDates;
		}
	}
}
