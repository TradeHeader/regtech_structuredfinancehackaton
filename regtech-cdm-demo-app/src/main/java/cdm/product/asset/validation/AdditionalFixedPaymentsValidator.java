package cdm.product.asset.validation;

import cdm.product.asset.AdditionalFixedPayments;
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

public class AdditionalFixedPaymentsValidator implements Validator<AdditionalFixedPayments> {

	private List<ComparisonResult> getComparisonResults(AdditionalFixedPayments o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("interestShortfallReimbursement", (Boolean) o.getInterestShortfallReimbursement() != null ? 1 : 0, 0, 1), 
				checkCardinality("principalShortfallReimbursement", (Boolean) o.getPrincipalShortfallReimbursement() != null ? 1 : 0, 0, 1), 
				checkCardinality("writedownReimbursement", (Boolean) o.getWritedownReimbursement() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AdditionalFixedPayments> validate(RosettaPath path, AdditionalFixedPayments o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AdditionalFixedPayments", ValidationType.CARDINALITY, "AdditionalFixedPayments", path, "", error);
		}
		return success("AdditionalFixedPayments", ValidationType.CARDINALITY, "AdditionalFixedPayments", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AdditionalFixedPayments o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AdditionalFixedPayments", ValidationType.CARDINALITY, "AdditionalFixedPayments", path, "", res.getError());
				}
				return success("AdditionalFixedPayments", ValidationType.CARDINALITY, "AdditionalFixedPayments", path, "");
			})
			.collect(toList());
	}

}
