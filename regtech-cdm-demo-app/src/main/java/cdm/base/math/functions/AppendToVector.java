package cdm.base.math.functions;

import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@ImplementedBy(AppendToVector.AppendToVectorDefault.class)
public abstract class AppendToVector implements RosettaFunction {

	/**
	* @param vector Input vector.
	* @param value Value to add to the vector.
	* @return resultVector Resulting vector.
	*/
	public List<BigDecimal> evaluate(List<BigDecimal> vector, BigDecimal value) {
		List<BigDecimal> resultVector = doEvaluate(vector, value);
		
		return resultVector;
	}

	protected abstract List<BigDecimal> doEvaluate(List<BigDecimal> vector, BigDecimal value);

	public static class AppendToVectorDefault extends AppendToVector {
		@Override
		protected List<BigDecimal> doEvaluate(List<BigDecimal> vector, BigDecimal value) {
			if (vector == null) {
				vector = Collections.emptyList();
			}
			List<BigDecimal> resultVector = new ArrayList<>();
			return assignOutput(resultVector, vector, value);
		}
		
		protected List<BigDecimal> assignOutput(List<BigDecimal> resultVector, List<BigDecimal> vector, BigDecimal value) {
			resultVector.addAll(vector);
			
			if (value == null) {
				resultVector.addAll(Collections.<BigDecimal>emptyList());
			} else {
				resultVector.addAll(Collections.singletonList(value));
			}
			
			return resultVector;
		}
	}
}
