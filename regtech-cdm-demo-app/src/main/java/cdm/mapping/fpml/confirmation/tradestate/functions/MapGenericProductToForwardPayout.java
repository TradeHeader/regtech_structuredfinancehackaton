package cdm.mapping.fpml.confirmation.tradestate.functions;

import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(MapGenericProductToForwardPayout.MapGenericProductToForwardPayoutDefault.class)
public abstract class MapGenericProductToForwardPayout implements RosettaFunction {

	/**
	* @param synonymPath The xml path where the func is called from.
	* @param modelPath The cdm path that is being mapped.
	* @param productType 
	* @return result 
	*/
	public Boolean evaluate(String synonymPath, String modelPath, List<String> productType) {
		Boolean result = doEvaluate(synonymPath, modelPath, productType);
		
		return result;
	}

	protected abstract Boolean doEvaluate(String synonymPath, String modelPath, List<String> productType);

	public static class MapGenericProductToForwardPayoutDefault extends MapGenericProductToForwardPayout {
		@Override
		protected Boolean doEvaluate(String synonymPath, String modelPath, List<String> productType) {
			if (productType == null) {
				productType = Collections.emptyList();
			}
			Boolean result = null;
			return assignOutput(result, synonymPath, modelPath, productType);
		}
		
		protected Boolean assignOutput(Boolean result, String synonymPath, String modelPath, List<String> productType) {
			result = contains(MapperC.<String>of(productType), MapperS.of("InterestRate:Forward:Debt")).get();
			
			return result;
		}
	}
}
