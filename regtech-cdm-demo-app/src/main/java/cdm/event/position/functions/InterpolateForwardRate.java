package cdm.event.position.functions;

import cdm.product.template.ForwardPayout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.math.BigDecimal;


@ImplementedBy(InterpolateForwardRate.InterpolateForwardRateDefault.class)
public abstract class InterpolateForwardRate implements RosettaFunction {

	/**
	* @param forward 
	* @return result 
	*/
	public BigDecimal evaluate(ForwardPayout forward) {
		BigDecimal result = doEvaluate(forward);
		
		return result;
	}

	protected abstract BigDecimal doEvaluate(ForwardPayout forward);

	public static class InterpolateForwardRateDefault extends InterpolateForwardRate {
		@Override
		protected BigDecimal doEvaluate(ForwardPayout forward) {
			BigDecimal result = null;
			return assignOutput(result, forward);
		}
		
		protected BigDecimal assignOutput(BigDecimal result, ForwardPayout forward) {
			return result;
		}
	}
}
