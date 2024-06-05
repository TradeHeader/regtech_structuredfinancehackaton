package cdm.event.common.validation;

import cdm.event.common.Reset;
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

public class ResetTypeFormatValidator implements Validator<Reset> {

	private List<ComparisonResult> getComparisonResults(Reset o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Reset> validate(RosettaPath path, Reset o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Reset", ValidationType.TYPE_FORMAT, "Reset", path, "", error);
		}
		return success("Reset", ValidationType.TYPE_FORMAT, "Reset", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Reset o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Reset", ValidationType.TYPE_FORMAT, "Reset", path, "", res.getError());
				}
				return success("Reset", ValidationType.TYPE_FORMAT, "Reset", path, "");
			})
			.collect(toList());
	}

}
