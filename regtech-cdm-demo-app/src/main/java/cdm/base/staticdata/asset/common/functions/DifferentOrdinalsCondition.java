package cdm.base.staticdata.asset.common.functions;

import cdm.base.staticdata.asset.common.Taxonomy;
import cdm.base.staticdata.asset.common.TaxonomyClassification;
import cdm.base.staticdata.asset.common.TaxonomyValue;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(DifferentOrdinalsCondition.DifferentOrdinalsConditionDefault.class)
public abstract class DifferentOrdinalsCondition implements RosettaFunction {

	/**
	* @param taxonomy 
	* @return result 
	*/
	public Boolean evaluate(Taxonomy taxonomy) {
		Boolean result = doEvaluate(taxonomy);
		
		return result;
	}

	protected abstract Boolean doEvaluate(Taxonomy taxonomy);

	public static class DifferentOrdinalsConditionDefault extends DifferentOrdinalsCondition {
		@Override
		protected Boolean doEvaluate(Taxonomy taxonomy) {
			Boolean result = null;
			return assignOutput(result, taxonomy);
		}
		
		protected Boolean assignOutput(Boolean result, Taxonomy taxonomy) {
			result = areEqual(MapperS.of(taxonomy).<TaxonomyValue>map("getValue", _taxonomy -> _taxonomy.getValue())
				.mapSingleToItem(item -> areEqual(item.<TaxonomyClassification>mapC("getClassification", taxonomyValue -> taxonomyValue.getClassification()).<Integer>map("getOrdinal", taxonomyClassification -> taxonomyClassification.getOrdinal()), distinct(item.<TaxonomyClassification>mapC("getClassification", taxonomyValue -> taxonomyValue.getClassification()).<Integer>map("getOrdinal", taxonomyClassification -> taxonomyClassification.getOrdinal())), CardinalityOperator.All).asMapper()), MapperS.of(true), CardinalityOperator.All).get();
			
			return result;
		}
	}
}
