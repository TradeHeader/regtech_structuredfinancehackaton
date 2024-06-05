package cdm.event.common.validation;

import cdm.event.common.SplitInstruction;
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

public class SplitInstructionTypeFormatValidator implements Validator<SplitInstruction> {

	private List<ComparisonResult> getComparisonResults(SplitInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<SplitInstruction> validate(RosettaPath path, SplitInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SplitInstruction", ValidationType.TYPE_FORMAT, "SplitInstruction", path, "", error);
		}
		return success("SplitInstruction", ValidationType.TYPE_FORMAT, "SplitInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SplitInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SplitInstruction", ValidationType.TYPE_FORMAT, "SplitInstruction", path, "", res.getError());
				}
				return success("SplitInstruction", ValidationType.TYPE_FORMAT, "SplitInstruction", path, "");
			})
			.collect(toList());
	}

}
