package cdm.product.collateral.functions;

import cdm.base.staticdata.party.LegalEntity;
import cdm.product.collateral.EligibilityQuery;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(CheckIssuerName.CheckIssuerNameDefault.class)
public abstract class CheckIssuerName implements RosettaFunction {

	/**
	* @param issuerName 
	* @param query 
	* @return isEqual 
	*/
	public Boolean evaluate(List<? extends LegalEntity> issuerName, EligibilityQuery query) {
		Boolean isEqual = doEvaluate(issuerName, query);
		
		return isEqual;
	}

	protected abstract Boolean doEvaluate(List<? extends LegalEntity> issuerName, EligibilityQuery query);

	public static class CheckIssuerNameDefault extends CheckIssuerName {
		@Override
		protected Boolean doEvaluate(List<? extends LegalEntity> issuerName, EligibilityQuery query) {
			if (issuerName == null) {
				issuerName = Collections.emptyList();
			}
			Boolean isEqual = null;
			return assignOutput(isEqual, issuerName, query);
		}
		
		protected Boolean assignOutput(Boolean isEqual, List<? extends LegalEntity> issuerName, EligibilityQuery query) {
			isEqual = notExists(MapperC.<LegalEntity>of(issuerName)).or(contains(MapperC.<LegalEntity>of(issuerName), MapperS.of(query).<LegalEntity>map("getIssuerName", eligibilityQuery -> eligibilityQuery.getIssuerName()))).get();
			
			return isEqual;
		}
	}
}
