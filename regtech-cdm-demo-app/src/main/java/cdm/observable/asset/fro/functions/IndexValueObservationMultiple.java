package cdm.observable.asset.fro.functions;

import cdm.observable.asset.FloatingRateOption;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;


@ImplementedBy(IndexValueObservationMultiple.IndexValueObservationMultipleDefault.class)
public abstract class IndexValueObservationMultiple implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected IndexValueObservation indexValueObservation;

	/**
	* @param observationDate 
	* @param floatingRateOption 
	* @return observedValues 
	*/
	public List<BigDecimal> evaluate(List<Date> observationDate, FloatingRateOption floatingRateOption) {
		List<BigDecimal> observedValues = doEvaluate(observationDate, floatingRateOption);
		
		return observedValues;
	}

	protected abstract List<BigDecimal> doEvaluate(List<Date> observationDate, FloatingRateOption floatingRateOption);

	public static class IndexValueObservationMultipleDefault extends IndexValueObservationMultiple {
		@Override
		protected List<BigDecimal> doEvaluate(List<Date> observationDate, FloatingRateOption floatingRateOption) {
			if (observationDate == null) {
				observationDate = Collections.emptyList();
			}
			List<BigDecimal> observedValues = new ArrayList<>();
			return assignOutput(observedValues, observationDate, floatingRateOption);
		}
		
		protected List<BigDecimal> assignOutput(List<BigDecimal> observedValues, List<Date> observationDate, FloatingRateOption floatingRateOption) {
			observedValues.addAll(MapperC.<Date>of(observationDate)
				.mapItem(item -> MapperS.of(indexValueObservation.evaluate(item.get(), floatingRateOption))).getMulti());
			
			return observedValues;
		}
	}
}
