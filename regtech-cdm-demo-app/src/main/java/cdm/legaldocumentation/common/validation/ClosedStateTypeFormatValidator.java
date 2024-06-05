package cdm.legaldocumentation.common.validation;

import cdm.legaldocumentation.common.ClosedState;
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

public class ClosedStateTypeFormatValidator implements Validator<ClosedState> {

	private List<ComparisonResult> getComparisonResults(ClosedState o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ClosedState> validate(RosettaPath path, ClosedState o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ClosedState", ValidationType.TYPE_FORMAT, "ClosedState", path, "", error);
		}
		return success("ClosedState", ValidationType.TYPE_FORMAT, "ClosedState", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ClosedState o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ClosedState", ValidationType.TYPE_FORMAT, "ClosedState", path, "", res.getError());
				}
				return success("ClosedState", ValidationType.TYPE_FORMAT, "ClosedState", path, "");
			})
			.collect(toList());
	}

}
