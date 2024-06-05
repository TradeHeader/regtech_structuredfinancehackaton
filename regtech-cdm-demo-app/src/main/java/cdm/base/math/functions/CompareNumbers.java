package cdm.base.math.functions;

import cdm.base.math.CompareOp;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(CompareNumbers.CompareNumbersDefault.class)
public abstract class CompareNumbers implements RosettaFunction {

	/**
	* @param n1 
	* @param op 
	* @param n2 
	* @return result 
	*/
	public Boolean evaluate(BigDecimal n1, CompareOp op, BigDecimal n2) {
		Boolean result = doEvaluate(n1, op, n2);
		
		return result;
	}

	protected abstract Boolean doEvaluate(BigDecimal n1, CompareOp op, BigDecimal n2);

	public static class CompareNumbersDefault extends CompareNumbers {
		@Override
		protected Boolean doEvaluate(BigDecimal n1, CompareOp op, BigDecimal n2) {
			Boolean result = null;
			return assignOutput(result, n1, op, n2);
		}
		
		protected Boolean assignOutput(Boolean result, BigDecimal n1, CompareOp op, BigDecimal n2) {
			if (areEqual(MapperS.of(op), MapperS.of(CompareOp.GREATER_THAN), CardinalityOperator.All).getOrDefault(false)) {
				result = areEqual(greaterThan(MapperS.of(n1), MapperS.of(n2), CardinalityOperator.All), MapperS.of(true), CardinalityOperator.All).get();
			} else if (areEqual(MapperS.of(op), MapperS.of(CompareOp.GREATER_THAN_OR_EQUALS), CardinalityOperator.All).getOrDefault(false)) {
				result = areEqual(greaterThanEquals(MapperS.of(n1), MapperS.of(n2), CardinalityOperator.All), MapperS.of(true), CardinalityOperator.All).get();
			} else if (areEqual(MapperS.of(op), MapperS.of(CompareOp.EQUALS), CardinalityOperator.All).getOrDefault(false)) {
				result = areEqual(areEqual(MapperS.of(n1), MapperS.of(n2), CardinalityOperator.All), MapperS.of(true), CardinalityOperator.All).get();
			} else if (areEqual(MapperS.of(op), MapperS.of(CompareOp.LESS_THAN_OR_EQUALS), CardinalityOperator.All).getOrDefault(false)) {
				result = areEqual(lessThanEquals(MapperS.of(n1), MapperS.of(n2), CardinalityOperator.All), MapperS.of(true), CardinalityOperator.All).get();
			} else if (areEqual(MapperS.of(op), MapperS.of(CompareOp.LESS_THAN), CardinalityOperator.All).getOrDefault(false)) {
				result = areEqual(lessThan(MapperS.of(n1), MapperS.of(n2), CardinalityOperator.All), MapperS.of(true), CardinalityOperator.All).get();
			} else {
				result = false;
			}
			
			return result;
		}
	}
}
