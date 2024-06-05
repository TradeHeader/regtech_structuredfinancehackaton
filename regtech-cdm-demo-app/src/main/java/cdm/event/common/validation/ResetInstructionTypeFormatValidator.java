package cdm.event.common.validation;

import cdm.event.common.ResetInstruction;
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

public class ResetInstructionTypeFormatValidator implements Validator<ResetInstruction> {

	private List<ComparisonResult> getComparisonResults(ResetInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ResetInstruction> validate(RosettaPath path, ResetInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ResetInstruction", ValidationType.TYPE_FORMAT, "ResetInstruction", path, "", error);
		}
		return success("ResetInstruction", ValidationType.TYPE_FORMAT, "ResetInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ResetInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ResetInstruction", ValidationType.TYPE_FORMAT, "ResetInstruction", path, "", res.getError());
				}
				return success("ResetInstruction", ValidationType.TYPE_FORMAT, "ResetInstruction", path, "");
			})
			.collect(toList());
	}

}
