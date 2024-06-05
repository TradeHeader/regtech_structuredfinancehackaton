package cdm.event.common.validation;

import cdm.event.common.PartyChangeInstruction;
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

public class PartyChangeInstructionTypeFormatValidator implements Validator<PartyChangeInstruction> {

	private List<ComparisonResult> getComparisonResults(PartyChangeInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PartyChangeInstruction> validate(RosettaPath path, PartyChangeInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PartyChangeInstruction", ValidationType.TYPE_FORMAT, "PartyChangeInstruction", path, "", error);
		}
		return success("PartyChangeInstruction", ValidationType.TYPE_FORMAT, "PartyChangeInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PartyChangeInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PartyChangeInstruction", ValidationType.TYPE_FORMAT, "PartyChangeInstruction", path, "", res.getError());
				}
				return success("PartyChangeInstruction", ValidationType.TYPE_FORMAT, "PartyChangeInstruction", path, "");
			})
			.collect(toList());
	}

}
