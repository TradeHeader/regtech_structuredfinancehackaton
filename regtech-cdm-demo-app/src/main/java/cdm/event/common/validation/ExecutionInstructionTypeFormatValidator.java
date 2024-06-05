package cdm.event.common.validation;

import cdm.event.common.ExecutionInstruction;
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

public class ExecutionInstructionTypeFormatValidator implements Validator<ExecutionInstruction> {

	private List<ComparisonResult> getComparisonResults(ExecutionInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ExecutionInstruction> validate(RosettaPath path, ExecutionInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ExecutionInstruction", ValidationType.TYPE_FORMAT, "ExecutionInstruction", path, "", error);
		}
		return success("ExecutionInstruction", ValidationType.TYPE_FORMAT, "ExecutionInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ExecutionInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ExecutionInstruction", ValidationType.TYPE_FORMAT, "ExecutionInstruction", path, "", res.getError());
				}
				return success("ExecutionInstruction", ValidationType.TYPE_FORMAT, "ExecutionInstruction", path, "");
			})
			.collect(toList());
	}

}
