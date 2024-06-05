package cdm.base.math.functions;

import cdm.base.math.ArithmeticOperationEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@ImplementedBy(VectorOperation.VectorOperationDefault.class)
public abstract class VectorOperation implements RosettaFunction {

	/**
	* @param arithmeticOp Vector operator.
	* @param left Left vector.
	* @param right Right vector.
	* @return result Result vector.
	*/
	public List<BigDecimal> evaluate(ArithmeticOperationEnum arithmeticOp, List<BigDecimal> left, List<BigDecimal> right) {
		List<BigDecimal> result = doEvaluate(arithmeticOp, left, right);
		
		return result;
	}

	protected abstract List<BigDecimal> doEvaluate(ArithmeticOperationEnum arithmeticOp, List<BigDecimal> left, List<BigDecimal> right);

	public static class VectorOperationDefault extends VectorOperation {
		@Override
		protected List<BigDecimal> doEvaluate(ArithmeticOperationEnum arithmeticOp, List<BigDecimal> left, List<BigDecimal> right) {
			if (left == null) {
				left = Collections.emptyList();
			}
			if (right == null) {
				right = Collections.emptyList();
			}
			List<BigDecimal> result = new ArrayList<>();
			return assignOutput(result, arithmeticOp, left, right);
		}
		
		protected List<BigDecimal> assignOutput(List<BigDecimal> result, ArithmeticOperationEnum arithmeticOp, List<BigDecimal> left, List<BigDecimal> right) {
			return result;
		}
	}
}
