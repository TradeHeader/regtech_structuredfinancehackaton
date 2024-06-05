package cdm.product.collateral.functions;

import cdm.base.staticdata.asset.common.ISOCountryCodeEnum;
import cdm.product.collateral.EligibilityQuery;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(CheckAssetCountryOfOrigin.CheckAssetCountryOfOriginDefault.class)
public abstract class CheckAssetCountryOfOrigin implements RosettaFunction {

	/**
	* @param assetCountryOfOrigin 
	* @param query 
	* @return isEqual 
	*/
	public Boolean evaluate(List<ISOCountryCodeEnum> assetCountryOfOrigin, EligibilityQuery query) {
		Boolean isEqual = doEvaluate(assetCountryOfOrigin, query);
		
		return isEqual;
	}

	protected abstract Boolean doEvaluate(List<ISOCountryCodeEnum> assetCountryOfOrigin, EligibilityQuery query);

	public static class CheckAssetCountryOfOriginDefault extends CheckAssetCountryOfOrigin {
		@Override
		protected Boolean doEvaluate(List<ISOCountryCodeEnum> assetCountryOfOrigin, EligibilityQuery query) {
			if (assetCountryOfOrigin == null) {
				assetCountryOfOrigin = Collections.emptyList();
			}
			Boolean isEqual = null;
			return assignOutput(isEqual, assetCountryOfOrigin, query);
		}
		
		protected Boolean assignOutput(Boolean isEqual, List<ISOCountryCodeEnum> assetCountryOfOrigin, EligibilityQuery query) {
			isEqual = notExists(MapperC.<ISOCountryCodeEnum>of(assetCountryOfOrigin)).or(contains(MapperC.<ISOCountryCodeEnum>of(assetCountryOfOrigin), MapperS.of(query).<ISOCountryCodeEnum>map("getAssetCountryOfOrigin", eligibilityQuery -> eligibilityQuery.getAssetCountryOfOrigin()))).get();
			
			return isEqual;
		}
	}
}
