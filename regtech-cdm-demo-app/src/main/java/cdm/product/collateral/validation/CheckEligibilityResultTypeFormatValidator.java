package cdm.product.collateral.validation;

import cdm.product.collateral.CheckEligibilityResult;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CheckEligibilityResultTypeFormatValidator implements Validator<CheckEligibilityResult> {

	private List<ComparisonResult> getComparisonResults(CheckEligibilityResult o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CheckEligibilityResult> validate(RosettaPath path, CheckEligibilityResult o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CheckEligibilityResult", ValidationType.TYPE_FORMAT, "CheckEligibilityResult", path, "", error);
		}
		return success("CheckEligibilityResult", ValidationType.TYPE_FORMAT, "CheckEligibilityResult", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CheckEligibilityResult o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CheckEligibilityResult", ValidationType.TYPE_FORMAT, "CheckEligibilityResult", path, "", res.getError());
				}
				return success("CheckEligibilityResult", ValidationType.TYPE_FORMAT, "CheckEligibilityResult", path, "");
			})
			.collect(toList());
	}

}
