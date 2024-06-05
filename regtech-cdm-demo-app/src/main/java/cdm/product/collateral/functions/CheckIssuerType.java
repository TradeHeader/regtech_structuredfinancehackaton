package cdm.product.collateral.functions;

import cdm.base.staticdata.asset.common.CollateralIssuerType;
import cdm.product.collateral.EligibilityQuery;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(CheckIssuerType.CheckIssuerTypeDefault.class)
public abstract class CheckIssuerType implements RosettaFunction {

	/**
	* @param issuerType 
	* @param query 
	* @return isEqual 
	*/
	public Boolean evaluate(List<? extends CollateralIssuerType> issuerType, EligibilityQuery query) {
		Boolean isEqual = doEvaluate(issuerType, query);
		
		return isEqual;
	}

	protected abstract Boolean doEvaluate(List<? extends CollateralIssuerType> issuerType, EligibilityQuery query);

	public static class CheckIssuerTypeDefault extends CheckIssuerType {
		@Override
		protected Boolean doEvaluate(List<? extends CollateralIssuerType> issuerType, EligibilityQuery query) {
			if (issuerType == null) {
				issuerType = Collections.emptyList();
			}
			Boolean isEqual = null;
			return assignOutput(isEqual, issuerType, query);
		}
		
		protected Boolean assignOutput(Boolean isEqual, List<? extends CollateralIssuerType> issuerType, EligibilityQuery query) {
			isEqual = notExists(MapperC.<CollateralIssuerType>of(issuerType)).or(contains(MapperC.<CollateralIssuerType>of(issuerType), MapperS.of(query).<CollateralIssuerType>map("getIssuerType", eligibilityQuery -> eligibilityQuery.getIssuerType()))).get();
			
			return isEqual;
		}
	}
}
