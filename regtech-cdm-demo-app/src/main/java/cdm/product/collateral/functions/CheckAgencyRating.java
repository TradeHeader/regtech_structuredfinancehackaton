package cdm.product.collateral.functions;

import cdm.base.math.QuantifierEnum;
import cdm.observable.asset.CreditNotation;
import cdm.observable.asset.CreditRatingAgencyEnum;
import cdm.product.collateral.AgencyRatingCriteria;
import cdm.product.collateral.EligibilityQuery;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.Collections;
import java.util.List;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(CheckAgencyRating.CheckAgencyRatingDefault.class)
public abstract class CheckAgencyRating implements RosettaFunction {

	/**
	* @param agencyRatings 
	* @param query 
	* @return isEqual 
	*/
	public Boolean evaluate(List<? extends AgencyRatingCriteria> agencyRatings, EligibilityQuery query) {
		Boolean isEqual = doEvaluate(agencyRatings, query);
		
		return isEqual;
	}

	protected abstract Boolean doEvaluate(List<? extends AgencyRatingCriteria> agencyRatings, EligibilityQuery query);

	public static class CheckAgencyRatingDefault extends CheckAgencyRating {
		@Override
		protected Boolean doEvaluate(List<? extends AgencyRatingCriteria> agencyRatings, EligibilityQuery query) {
			if (agencyRatings == null) {
				agencyRatings = Collections.emptyList();
			}
			Boolean isEqual = null;
			return assignOutput(isEqual, agencyRatings, query);
		}
		
		protected Boolean assignOutput(Boolean isEqual, List<? extends AgencyRatingCriteria> agencyRatings, EligibilityQuery query) {
			final MapperC<AgencyRatingCriteria> thenResult0 = MapperC.<AgencyRatingCriteria>of(agencyRatings);
			final MapperC<AgencyRatingCriteria> thenResult1 = thenResult0
				.filterItemNullSafe(item -> contains(item.<CreditNotation>mapC("getCreditNotation", agencyRatingCriteria -> agencyRatingCriteria.getCreditNotation()).<FieldWithMetaString>map("getNotation", creditNotation -> creditNotation.getNotation()).<String>map("getValue", _f->_f.getValue()), MapperS.of(query).<AgencyRatingCriteria>map("getAgencyRating", eligibilityQuery -> eligibilityQuery.getAgencyRating()).<CreditNotation>mapC("getCreditNotation", agencyRatingCriteria -> agencyRatingCriteria.getCreditNotation()).<FieldWithMetaString>map("getNotation", creditNotation -> creditNotation.getNotation()).<String>map("getValue", _f->_f.getValue())).get());
			final MapperC<AgencyRatingCriteria> thenResult2 = thenResult1
				.filterItemNullSafe(item -> contains(item.<QuantifierEnum>map("getQualifier", agencyRatingCriteria -> agencyRatingCriteria.getQualifier()), MapperS.of(query).<AgencyRatingCriteria>map("getAgencyRating", eligibilityQuery -> eligibilityQuery.getAgencyRating()).<QuantifierEnum>map("getQualifier", agencyRatingCriteria -> agencyRatingCriteria.getQualifier())).get());
			final MapperC<AgencyRatingCriteria> thenResult3 = thenResult2
				.filterItemNullSafe(item -> notExists(item.<CreditRatingAgencyEnum>map("getReferenceAgency", agencyRatingCriteria -> agencyRatingCriteria.getReferenceAgency())).or(contains(item.<CreditRatingAgencyEnum>map("getReferenceAgency", agencyRatingCriteria -> agencyRatingCriteria.getReferenceAgency()), MapperS.of(query).<AgencyRatingCriteria>map("getAgencyRating", eligibilityQuery -> eligibilityQuery.getAgencyRating()).<CreditRatingAgencyEnum>map("getReferenceAgency", agencyRatingCriteria -> agencyRatingCriteria.getReferenceAgency()))).get());
			isEqual = notExists(MapperC.<AgencyRatingCriteria>of(agencyRatings)).or(ComparisonResult.of(exists(thenResult3).asMapper())).get();
			
			return isEqual;
		}
	}
}
