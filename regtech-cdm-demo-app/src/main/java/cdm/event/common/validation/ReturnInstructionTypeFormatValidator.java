package cdm.event.common.validation;

import cdm.event.common.ReturnInstruction;
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

public class ReturnInstructionTypeFormatValidator implements Validator<ReturnInstruction> {

	private List<ComparisonResult> getComparisonResults(ReturnInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ReturnInstruction> validate(RosettaPath path, ReturnInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ReturnInstruction", ValidationType.TYPE_FORMAT, "ReturnInstruction", path, "", error);
		}
		return success("ReturnInstruction", ValidationType.TYPE_FORMAT, "ReturnInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ReturnInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ReturnInstruction", ValidationType.TYPE_FORMAT, "ReturnInstruction", path, "", res.getError());
				}
				return success("ReturnInstruction", ValidationType.TYPE_FORMAT, "ReturnInstruction", path, "");
			})
			.collect(toList());
	}

}
