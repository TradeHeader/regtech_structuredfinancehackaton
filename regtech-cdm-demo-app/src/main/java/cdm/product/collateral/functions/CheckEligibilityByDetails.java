package cdm.product.collateral.functions;

import cdm.base.datetime.PeriodRange;
import cdm.base.staticdata.asset.common.AssetType;
import cdm.base.staticdata.asset.common.CollateralIssuerType;
import cdm.base.staticdata.asset.common.CurrencyCodeEnum;
import cdm.base.staticdata.asset.common.ISOCountryCodeEnum;
import cdm.base.staticdata.party.LegalEntity;
import cdm.product.collateral.AgencyRatingCriteria;
import cdm.product.collateral.AssetCriteria;
import cdm.product.collateral.CheckEligibilityResult;
import cdm.product.collateral.CheckEligibilityResult.CheckEligibilityResultBuilder;
import cdm.product.collateral.CollateralTreatment;
import cdm.product.collateral.EligibilityQuery;
import cdm.product.collateral.EligibleCollateralCriteria;
import cdm.product.collateral.EligibleCollateralSpecification;
import cdm.product.collateral.IssuerCriteria;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(CheckEligibilityByDetails.CheckEligibilityByDetailsDefault.class)
public abstract class CheckEligibilityByDetails implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected CheckAgencyRating checkAgencyRating;
	@Inject protected CheckAssetCountryOfOrigin checkAssetCountryOfOrigin;
	@Inject protected CheckAssetType checkAssetType;
	@Inject protected CheckDenominatedCurrency checkDenominatedCurrency;
	@Inject protected CheckIssuerName checkIssuerName;
	@Inject protected CheckIssuerType checkIssuerType;
	@Inject protected CheckMaturity checkMaturity;

	/**
	* @param specification Specifications that determine which collateral meets the eligibility and can be used/posted for delivery. For ICMA usecase - this is the basket(s). For ISDA usecase these are the Elegibility Schedule Lists.
	* @param query The eligibility query defines the criteria to filter the specifications that will be used to find the eligibility result. For ICMA usecase - The asset infomation related to potential collateral available in your inventory you can use for the Repo trade. For ISDA - the questions related to the asset infomation you can post as collateral. For ICMA usecase - The issuer infomation related to the potential collateral available in your inventory you can use for the Repo trade. For ISDA - the questions related to the issuer infomation you can post as collateral.
	* @return eligibilityResult 
	*/
	public CheckEligibilityResult evaluate(EligibleCollateralSpecification specification, EligibilityQuery query) {
		CheckEligibilityResult.CheckEligibilityResultBuilder eligibilityResultBuilder = doEvaluate(specification, query);
		
		final CheckEligibilityResult eligibilityResult;
		if (eligibilityResultBuilder == null) {
			eligibilityResult = null;
		} else {
			eligibilityResult = eligibilityResultBuilder.build();
			objectValidator.validate(CheckEligibilityResult.class, eligibilityResult);
		}
		
		return eligibilityResult;
	}

	protected abstract CheckEligibilityResult.CheckEligibilityResultBuilder doEvaluate(EligibleCollateralSpecification specification, EligibilityQuery query);

	protected abstract MapperC<? extends EligibleCollateralCriteria> matchingEligibleCriteria(EligibleCollateralSpecification specification, EligibilityQuery query);

	public static class CheckEligibilityByDetailsDefault extends CheckEligibilityByDetails {
		@Override
		protected CheckEligibilityResult.CheckEligibilityResultBuilder doEvaluate(EligibleCollateralSpecification specification, EligibilityQuery query) {
			CheckEligibilityResult.CheckEligibilityResultBuilder eligibilityResult = CheckEligibilityResult.builder();
			return assignOutput(eligibilityResult, specification, query);
		}
		
		protected CheckEligibilityResult.CheckEligibilityResultBuilder assignOutput(CheckEligibilityResult.CheckEligibilityResultBuilder eligibilityResult, EligibleCollateralSpecification specification, EligibilityQuery query) {
			eligibilityResult
				.setIsEligible(greaterThan(MapperS.of(matchingEligibleCriteria(specification, query).resultCount()), MapperS.of(0), CardinalityOperator.All).get());
			
			eligibilityResult
				.setEligibilityQuery(query);
			
			eligibilityResult
				.setSpecification(specification);
			
			eligibilityResult
				.addMatchingEligibleCriteria(matchingEligibleCriteria(specification, query).getMulti());
			
			return Optional.ofNullable(eligibilityResult)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperC<? extends EligibleCollateralCriteria> matchingEligibleCriteria(EligibleCollateralSpecification specification, EligibilityQuery query) {
			final MapperC<EligibleCollateralCriteria> thenResult0 = MapperS.of(specification).<EligibleCollateralCriteria>mapC("getCriteria", eligibleCollateralSpecification -> eligibleCollateralSpecification.getCriteria())
				.filterItemNullSafe(item -> item.<CollateralTreatment>map("getTreatment", eligibleCollateralCriteria -> eligibleCollateralCriteria.getTreatment()).<Boolean>map("getIsIncluded", collateralTreatment -> collateralTreatment.getIsIncluded()).get());
			final MapperC<EligibleCollateralCriteria> thenResult1 = thenResult0
				.filterItemNullSafe(item -> {
					final MapperC<AssetCriteria> _thenResult0 = item.<AssetCriteria>mapC("getAsset", collateralCriteriaBase -> collateralCriteriaBase.getAsset());
					final MapperC<AssetCriteria> _thenResult1 = _thenResult0
						.filterItemNullSafe(_item -> checkAssetCountryOfOrigin.evaluate(_item.<ISOCountryCodeEnum>mapC("getAssetCountryOfOrigin", assetCriteria -> assetCriteria.getAssetCountryOfOrigin()).getMulti(), query));
					final MapperC<AssetCriteria> thenResult2 = _thenResult1
						.filterItemNullSafe(_item -> checkAgencyRating.evaluate(_item.<AgencyRatingCriteria>mapC("getAgencyRating", assetCriteria -> assetCriteria.getAgencyRating()).getMulti(), query));
					final MapperC<AssetCriteria> thenResult3 = thenResult2
						.filterItemNullSafe(_item -> checkAssetType.evaluate(_item.<AssetType>mapC("getCollateralAssetType", assetCriteria -> assetCriteria.getCollateralAssetType()).getMulti(), query));
					final MapperC<AssetCriteria> thenResult4 = thenResult3
						.filterItemNullSafe(_item -> checkDenominatedCurrency.evaluate(_item.<CurrencyCodeEnum>mapC("getDenominatedCurrency", assetCriteria -> assetCriteria.getDenominatedCurrency()).getMulti(), query));
					final MapperC<AssetCriteria> thenResult5 = thenResult4
						.filterItemNullSafe(_item -> checkMaturity.evaluate(_item.<PeriodRange>map("getMaturityRange", assetCriteria -> assetCriteria.getMaturityRange()).get(), query));
					return exists(thenResult5).asMapper().get();
				});
			return thenResult1
				.filterItemNullSafe(item -> {
					final MapperC<IssuerCriteria> _thenResult0 = item.<IssuerCriteria>mapC("getIssuer", collateralCriteriaBase -> collateralCriteriaBase.getIssuer());
					final MapperC<IssuerCriteria> _thenResult1 = _thenResult0
						.filterItemNullSafe(_item -> checkIssuerName.evaluate(_item.<LegalEntity>mapC("getIssuerName", issuerCriteria -> issuerCriteria.getIssuerName()).getMulti(), query));
					final MapperC<IssuerCriteria> thenResult2 = _thenResult1
						.filterItemNullSafe(_item -> checkIssuerType.evaluate(_item.<CollateralIssuerType>mapC("getIssuerType", issuerCriteria -> issuerCriteria.getIssuerType()).getMulti(), query));
					return exists(thenResult2).asMapper().get();
				});
		}
	}
}
