package cdm.observable.asset.validation;

import cdm.observable.asset.CashCollateralValuationMethod;
import cdm.observable.asset.CsaTypeEnum;
import cdm.observable.asset.PartyDeterminationEnum;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CashCollateralValuationMethodValidator implements Validator<CashCollateralValuationMethod> {

	private List<ComparisonResult> getComparisonResults(CashCollateralValuationMethod o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("applicableCsa", (CsaTypeEnum) o.getApplicableCsa() != null ? 1 : 0, 0, 1), 
				checkCardinality("cashCollateralCurrency", (String) o.getCashCollateralCurrency() != null ? 1 : 0, 0, 1), 
				checkCardinality("cashCollateralInterestRate", (FieldWithMetaString) o.getCashCollateralInterestRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("agreedDiscountRate", (FieldWithMetaString) o.getAgreedDiscountRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("protectedParty", (List<PartyDeterminationEnum>) o.getProtectedParty() == null ? 0 : ((List<PartyDeterminationEnum>) o.getProtectedParty()).size(), 0, 2), 
				checkCardinality("prescribedDocumentationAdjustment", (Boolean) o.getPrescribedDocumentationAdjustment() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CashCollateralValuationMethod> validate(RosettaPath path, CashCollateralValuationMethod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CashCollateralValuationMethod", ValidationType.CARDINALITY, "CashCollateralValuationMethod", path, "", error);
		}
		return success("CashCollateralValuationMethod", ValidationType.CARDINALITY, "CashCollateralValuationMethod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CashCollateralValuationMethod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CashCollateralValuationMethod", ValidationType.CARDINALITY, "CashCollateralValuationMethod", path, "", res.getError());
				}
				return success("CashCollateralValuationMethod", ValidationType.CARDINALITY, "CashCollateralValuationMethod", path, "");
			})
			.collect(toList());
	}

}
