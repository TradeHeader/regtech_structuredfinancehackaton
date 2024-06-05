package cdm.product.collateral.validation;

import cdm.base.staticdata.asset.common.AssetType;
import cdm.base.staticdata.asset.common.CollateralIssuerType;
import cdm.base.staticdata.asset.common.CurrencyCodeEnum;
import cdm.base.staticdata.asset.common.ISOCountryCodeEnum;
import cdm.base.staticdata.party.LegalEntity;
import cdm.product.collateral.AgencyRatingCriteria;
import cdm.product.collateral.EligibilityQuery;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class EligibilityQueryValidator implements Validator<EligibilityQuery> {

	private List<ComparisonResult> getComparisonResults(EligibilityQuery o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("maturity", (BigDecimal) o.getMaturity() != null ? 1 : 0, 1, 1), 
				checkCardinality("collateralAssetType", (AssetType) o.getCollateralAssetType() != null ? 1 : 0, 1, 1), 
				checkCardinality("assetCountryOfOrigin", (ISOCountryCodeEnum) o.getAssetCountryOfOrigin() != null ? 1 : 0, 1, 1), 
				checkCardinality("denominatedCurrency", (CurrencyCodeEnum) o.getDenominatedCurrency() != null ? 1 : 0, 1, 1), 
				checkCardinality("agencyRating", (AgencyRatingCriteria) o.getAgencyRating() != null ? 1 : 0, 1, 1), 
				checkCardinality("issuerType", (CollateralIssuerType) o.getIssuerType() != null ? 1 : 0, 1, 1), 
				checkCardinality("issuerName", (LegalEntity) o.getIssuerName() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<EligibilityQuery> validate(RosettaPath path, EligibilityQuery o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EligibilityQuery", ValidationType.CARDINALITY, "EligibilityQuery", path, "", error);
		}
		return success("EligibilityQuery", ValidationType.CARDINALITY, "EligibilityQuery", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EligibilityQuery o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EligibilityQuery", ValidationType.CARDINALITY, "EligibilityQuery", path, "", res.getError());
				}
				return success("EligibilityQuery", ValidationType.CARDINALITY, "EligibilityQuery", path, "");
			})
			.collect(toList());
	}

}
