package cdm.base.math.functions;

import cdm.base.math.ArithmeticOperationEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(VectorScalarOperation.VectorScalarOperationDefault.class)
public abstract class VectorScalarOperation implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected ArithmeticOperation arithmeticOperation;

	/**
	* @param arithmeticOp Arithmetic operator to be applied.
	* @param left Left vector.
	* @param right Scalar number - a single value to be applied to all elements of vector.
	* @return result Result vector.
	*/
	public List<BigDecimal> evaluate(ArithmeticOperationEnum arithmeticOp, List<BigDecimal> left, BigDecimal right) {
		List<BigDecimal> result = doEvaluate(arithmeticOp, left, right);
		
		return result;
	}

	protected abstract List<BigDecimal> doEvaluate(ArithmeticOperationEnum arithmeticOp, List<BigDecimal> left, BigDecimal right);

	protected abstract MapperS<BigDecimal> rightOrDefault(ArithmeticOperationEnum arithmeticOp, List<BigDecimal> left, BigDecimal right);

	public static class VectorScalarOperationDefault extends VectorScalarOperation {
		@Override
		protected List<BigDecimal> doEvaluate(ArithmeticOperationEnum arithmeticOp, List<BigDecimal> left, BigDecimal right) {
			if (left == null) {
				left = Collections.emptyList();
			}
			List<BigDecimal> result = new ArrayList<>();
			return assignOutput(result, arithmeticOp, left, right);
		}
		
		protected List<BigDecimal> assignOutput(List<BigDecimal> result, ArithmeticOperationEnum arithmeticOp, List<BigDecimal> left, BigDecimal right) {
			result.addAll(MapperC.<BigDecimal>of(left)
				.mapItem(item -> MapperS.of(arithmeticOperation.evaluate(item.get(), arithmeticOp, rightOrDefault(arithmeticOp, left, right).get()))).getMulti());
			
			return result;
		}
		
		@Override
		protected MapperS<BigDecimal> rightOrDefault(ArithmeticOperationEnum arithmeticOp, List<BigDecimal> left, BigDecimal right) {
			if (exists(MapperS.of(right)).getOrDefault(false)) {
				return MapperS.of(right);
			}
			return MapperS.of(new BigDecimal("0.0"));
		}
	}
}
