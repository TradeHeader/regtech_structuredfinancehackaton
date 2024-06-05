package cdm.base.math.functions;

import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Max.MaxDefault.class)
public abstract class Max implements RosettaFunction {

	/**
	* @param a 
	* @param b 
	* @return result 
	*/
	public BigDecimal evaluate(BigDecimal a, BigDecimal b) {
		BigDecimal result = doEvaluate(a, b);
		
		return result;
	}

	protected abstract BigDecimal doEvaluate(BigDecimal a, BigDecimal b);

	public static class MaxDefault extends Max {
		@Override
		protected BigDecimal doEvaluate(BigDecimal a, BigDecimal b) {
			BigDecimal result = null;
			return assignOutput(result, a, b);
		}
		
		protected BigDecimal assignOutput(BigDecimal result, BigDecimal a, BigDecimal b) {
			if (greaterThan(MapperS.of(a), MapperS.of(b), CardinalityOperator.All).getOrDefault(false)) {
				result = a;
			} else {
				result = b;
			}
			
			return result;
		}
	}
}
