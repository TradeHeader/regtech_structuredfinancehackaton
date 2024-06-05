package cdm.product.asset.functions;

import cdm.base.staticdata.asset.rates.FloatingRateIndexEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.math.BigDecimal;


@ImplementedBy(ResolveRateIndex.ResolveRateIndexDefault.class)
public abstract class ResolveRateIndex implements RosettaFunction {

	/**
	* @param index 
	* @return rate 
	*/
	public BigDecimal evaluate(FloatingRateIndexEnum index) {
		BigDecimal rate = doEvaluate(index);
		
		return rate;
	}

	protected abstract BigDecimal doEvaluate(FloatingRateIndexEnum index);

	public static class ResolveRateIndexDefault extends ResolveRateIndex {
		@Override
		protected BigDecimal doEvaluate(FloatingRateIndexEnum index) {
			BigDecimal rate = null;
			return assignOutput(rate, index);
		}
		
		protected BigDecimal assignOutput(BigDecimal rate, FloatingRateIndexEnum index) {
			return rate;
		}
	}
}
