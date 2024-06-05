package cdm.event.common.validation;

import cdm.event.common.ExecutionDetails;
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

public class ExecutionDetailsTypeFormatValidator implements Validator<ExecutionDetails> {

	private List<ComparisonResult> getComparisonResults(ExecutionDetails o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ExecutionDetails> validate(RosettaPath path, ExecutionDetails o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ExecutionDetails", ValidationType.TYPE_FORMAT, "ExecutionDetails", path, "", error);
		}
		return success("ExecutionDetails", ValidationType.TYPE_FORMAT, "ExecutionDetails", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ExecutionDetails o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ExecutionDetails", ValidationType.TYPE_FORMAT, "ExecutionDetails", path, "", res.getError());
				}
				return success("ExecutionDetails", ValidationType.TYPE_FORMAT, "ExecutionDetails", path, "");
			})
			.collect(toList());
	}

}
