package cdm.observable.asset.fro.functions;

import cdm.observable.asset.FloatingRateOption;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;


@ImplementedBy(IndexValueObservation.IndexValueObservationDefault.class)
public abstract class IndexValueObservation implements RosettaFunction {

	/**
	* @param observationDate 
	* @param floatingRateOption 
	* @return observedValue 
	*/
	public BigDecimal evaluate(Date observationDate, FloatingRateOption floatingRateOption) {
		BigDecimal observedValue = doEvaluate(observationDate, floatingRateOption);
		
		return observedValue;
	}

	protected abstract BigDecimal doEvaluate(Date observationDate, FloatingRateOption floatingRateOption);

	public static class IndexValueObservationDefault extends IndexValueObservation {
		@Override
		protected BigDecimal doEvaluate(Date observationDate, FloatingRateOption floatingRateOption) {
			BigDecimal observedValue = null;
			return assignOutput(observedValue, observationDate, floatingRateOption);
		}
		
		protected BigDecimal assignOutput(BigDecimal observedValue, Date observationDate, FloatingRateOption floatingRateOption) {
			return observedValue;
		}
	}
}
