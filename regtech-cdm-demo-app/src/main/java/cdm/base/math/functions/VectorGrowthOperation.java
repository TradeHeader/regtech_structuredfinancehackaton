package cdm.base.math.functions;

import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@ImplementedBy(VectorGrowthOperation.VectorGrowthOperationDefault.class)
public abstract class VectorGrowthOperation implements RosettaFunction {

	/**
	* @param baseValue Original value, typically 1.0.
	* @param factor Vector of growth factors, which are all typically slightly greater than 1.0.
	* @return result Result vector, showing all of the interim growth values
	*/
	public List<BigDecimal> evaluate(BigDecimal baseValue, List<BigDecimal> factor) {
		List<BigDecimal> result = doEvaluate(baseValue, factor);
		
		return result;
	}

	protected abstract List<BigDecimal> doEvaluate(BigDecimal baseValue, List<BigDecimal> factor);

	public static class VectorGrowthOperationDefault extends VectorGrowthOperation {
		@Override
		protected List<BigDecimal> doEvaluate(BigDecimal baseValue, List<BigDecimal> factor) {
			if (factor == null) {
				factor = Collections.emptyList();
			}
			List<BigDecimal> result = new ArrayList<>();
			return assignOutput(result, baseValue, factor);
		}
		
		protected List<BigDecimal> assignOutput(List<BigDecimal> result, BigDecimal baseValue, List<BigDecimal> factor) {
			return result;
		}
	}
}
