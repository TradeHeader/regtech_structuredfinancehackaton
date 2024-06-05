package cdm.product.template.validation;

import cdm.product.template.CancelableProvision;
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

public class CancelableProvisionTypeFormatValidator implements Validator<CancelableProvision> {

	private List<ComparisonResult> getComparisonResults(CancelableProvision o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CancelableProvision> validate(RosettaPath path, CancelableProvision o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CancelableProvision", ValidationType.TYPE_FORMAT, "CancelableProvision", path, "", error);
		}
		return success("CancelableProvision", ValidationType.TYPE_FORMAT, "CancelableProvision", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CancelableProvision o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CancelableProvision", ValidationType.TYPE_FORMAT, "CancelableProvision", path, "", res.getError());
				}
				return success("CancelableProvision", ValidationType.TYPE_FORMAT, "CancelableProvision", path, "");
			})
			.collect(toList());
	}

}
