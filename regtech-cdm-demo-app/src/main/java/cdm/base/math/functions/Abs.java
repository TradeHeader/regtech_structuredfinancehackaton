package cdm.base.math.functions;

import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Abs.AbsDefault.class)
public abstract class Abs implements RosettaFunction {

	/**
	* @param arg 
	* @return result 
	*/
	public BigDecimal evaluate(BigDecimal arg) {
		BigDecimal result = doEvaluate(arg);
		
		return result;
	}

	protected abstract BigDecimal doEvaluate(BigDecimal arg);

	public static class AbsDefault extends Abs {
		@Override
		protected BigDecimal doEvaluate(BigDecimal arg) {
			BigDecimal result = null;
			return assignOutput(result, arg);
		}
		
		protected BigDecimal assignOutput(BigDecimal result, BigDecimal arg) {
			if (lessThan(MapperS.of(arg), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All).getOrDefault(false)) {
				result = MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(MapperS.of(BigDecimal.valueOf(-1)), MapperS.of(arg)).get();
			} else {
				result = arg;
			}
			
			return result;
		}
	}
}
