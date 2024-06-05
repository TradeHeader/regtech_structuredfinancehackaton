package cdm.base.math.functions;

import cdm.base.math.ArithmeticOperationEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ArithmeticOperation.ArithmeticOperationDefault.class)
public abstract class ArithmeticOperation implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected Max max;
	@Inject protected Min min;

	/**
	* @param n1 
	* @param op 
	* @param n2 
	* @return result 
	*/
	public BigDecimal evaluate(BigDecimal n1, ArithmeticOperationEnum op, BigDecimal n2) {
		BigDecimal result = doEvaluate(n1, op, n2);
		
		return result;
	}

	protected abstract BigDecimal doEvaluate(BigDecimal n1, ArithmeticOperationEnum op, BigDecimal n2);

	public static class ArithmeticOperationDefault extends ArithmeticOperation {
		@Override
		protected BigDecimal doEvaluate(BigDecimal n1, ArithmeticOperationEnum op, BigDecimal n2) {
			BigDecimal result = null;
			return assignOutput(result, n1, op, n2);
		}
		
		protected BigDecimal assignOutput(BigDecimal result, BigDecimal n1, ArithmeticOperationEnum op, BigDecimal n2) {
			if (areEqual(MapperS.of(op), MapperS.of(ArithmeticOperationEnum.ADD), CardinalityOperator.All).getOrDefault(false)) {
				result = MapperMaths.<BigDecimal, BigDecimal, BigDecimal>add(MapperS.of(n1), MapperS.of(n2)).get();
			} else if (areEqual(MapperS.of(op), MapperS.of(ArithmeticOperationEnum.SUBTRACT), CardinalityOperator.All).getOrDefault(false)) {
				result = MapperMaths.<BigDecimal, BigDecimal, BigDecimal>subtract(MapperS.of(n1), MapperS.of(n2)).get();
			} else if (areEqual(MapperS.of(op), MapperS.of(ArithmeticOperationEnum.MULTIPLY), CardinalityOperator.All).getOrDefault(false)) {
				result = MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(MapperS.of(n1), MapperS.of(n2)).get();
			} else if (areEqual(MapperS.of(op), MapperS.of(ArithmeticOperationEnum.DIVIDE), CardinalityOperator.All).getOrDefault(false)) {
				result = MapperMaths.<BigDecimal, BigDecimal, BigDecimal>divide(MapperS.of(n1), MapperS.of(n2)).get();
			} else if (areEqual(MapperS.of(op), MapperS.of(ArithmeticOperationEnum.MAX), CardinalityOperator.All).getOrDefault(false)) {
				result = max.evaluate(n1, n2);
			} else if (areEqual(MapperS.of(op), MapperS.of(ArithmeticOperationEnum.MIN), CardinalityOperator.All).getOrDefault(false)) {
				result = min.evaluate(n1, n2);
			} else {
				result = null;
			}
			
			return result;
		}
	}
}
