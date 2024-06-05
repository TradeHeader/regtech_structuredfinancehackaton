package cdm.event.common.validation;

import cdm.event.common.IndexTransitionInstruction;
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

public class IndexTransitionInstructionTypeFormatValidator implements Validator<IndexTransitionInstruction> {

	private List<ComparisonResult> getComparisonResults(IndexTransitionInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<IndexTransitionInstruction> validate(RosettaPath path, IndexTransitionInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("IndexTransitionInstruction", ValidationType.TYPE_FORMAT, "IndexTransitionInstruction", path, "", error);
		}
		return success("IndexTransitionInstruction", ValidationType.TYPE_FORMAT, "IndexTransitionInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, IndexTransitionInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("IndexTransitionInstruction", ValidationType.TYPE_FORMAT, "IndexTransitionInstruction", path, "", res.getError());
				}
				return success("IndexTransitionInstruction", ValidationType.TYPE_FORMAT, "IndexTransitionInstruction", path, "");
			})
			.collect(toList());
	}

}
