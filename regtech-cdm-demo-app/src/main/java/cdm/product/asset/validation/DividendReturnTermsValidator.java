package cdm.product.asset.validation;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.asset.DividendAmountTypeEnum;
import cdm.product.asset.DividendCompositionEnum;
import cdm.product.asset.DividendCurrency;
import cdm.product.asset.DividendEntitlementEnum;
import cdm.product.asset.DividendPeriodEnum;
import cdm.product.asset.DividendReturnTerms;
import cdm.product.asset.NonCashDividendTreatmentEnum;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class DividendReturnTermsValidator implements Validator<DividendReturnTerms> {

	private List<ComparisonResult> getComparisonResults(DividendReturnTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("dividendReinvestment", (Boolean) o.getDividendReinvestment() != null ? 1 : 0, 0, 1), 
				checkCardinality("dividendEntitlement", (DividendEntitlementEnum) o.getDividendEntitlement() != null ? 1 : 0, 0, 1), 
				checkCardinality("dividendAmountType", (DividendAmountTypeEnum) o.getDividendAmountType() != null ? 1 : 0, 0, 1), 
				checkCardinality("performance", (String) o.getPerformance() != null ? 1 : 0, 0, 1), 
				checkCardinality("firstOrSecondPeriod", (DividendPeriodEnum) o.getFirstOrSecondPeriod() != null ? 1 : 0, 0, 1), 
				checkCardinality("extraordinaryDividendsParty", (AncillaryRoleEnum) o.getExtraordinaryDividendsParty() != null ? 1 : 0, 0, 1), 
				checkCardinality("excessDividendAmount", (DividendAmountTypeEnum) o.getExcessDividendAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("dividendCurrency", (DividendCurrency) o.getDividendCurrency() != null ? 1 : 0, 0, 1), 
				checkCardinality("nonCashDividendTreatment", (NonCashDividendTreatmentEnum) o.getNonCashDividendTreatment() != null ? 1 : 0, 0, 1), 
				checkCardinality("dividendComposition", (DividendCompositionEnum) o.getDividendComposition() != null ? 1 : 0, 0, 1), 
				checkCardinality("specialDividends", (Boolean) o.getSpecialDividends() != null ? 1 : 0, 0, 1), 
				checkCardinality("materialDividend", (Boolean) o.getMaterialDividend() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<DividendReturnTerms> validate(RosettaPath path, DividendReturnTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DividendReturnTerms", ValidationType.CARDINALITY, "DividendReturnTerms", path, "", error);
		}
		return success("DividendReturnTerms", ValidationType.CARDINALITY, "DividendReturnTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DividendReturnTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DividendReturnTerms", ValidationType.CARDINALITY, "DividendReturnTerms", path, "", res.getError());
				}
				return success("DividendReturnTerms", ValidationType.CARDINALITY, "DividendReturnTerms", path, "");
			})
			.collect(toList());
	}

}
