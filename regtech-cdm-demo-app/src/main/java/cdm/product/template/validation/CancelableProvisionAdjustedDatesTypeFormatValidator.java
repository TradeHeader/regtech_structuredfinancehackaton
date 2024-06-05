package cdm.product.template.validation;

import cdm.product.template.CancelableProvisionAdjustedDates;
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

public class CancelableProvisionAdjustedDatesTypeFormatValidator implements Validator<CancelableProvisionAdjustedDates> {

	private List<ComparisonResult> getComparisonResults(CancelableProvisionAdjustedDates o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CancelableProvisionAdjustedDates> validate(RosettaPath path, CancelableProvisionAdjustedDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CancelableProvisionAdjustedDates", ValidationType.TYPE_FORMAT, "CancelableProvisionAdjustedDates", path, "", error);
		}
		return success("CancelableProvisionAdjustedDates", ValidationType.TYPE_FORMAT, "CancelableProvisionAdjustedDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CancelableProvisionAdjustedDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CancelableProvisionAdjustedDates", ValidationType.TYPE_FORMAT, "CancelableProvisionAdjustedDates", path, "", res.getError());
				}
				return success("CancelableProvisionAdjustedDates", ValidationType.TYPE_FORMAT, "CancelableProvisionAdjustedDates", path, "");
			})
			.collect(toList());
	}

}
