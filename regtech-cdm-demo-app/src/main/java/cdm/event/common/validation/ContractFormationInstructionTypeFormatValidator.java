package cdm.event.common.validation;

import cdm.event.common.ContractFormationInstruction;
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

public class ContractFormationInstructionTypeFormatValidator implements Validator<ContractFormationInstruction> {

	private List<ComparisonResult> getComparisonResults(ContractFormationInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ContractFormationInstruction> validate(RosettaPath path, ContractFormationInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ContractFormationInstruction", ValidationType.TYPE_FORMAT, "ContractFormationInstruction", path, "", error);
		}
		return success("ContractFormationInstruction", ValidationType.TYPE_FORMAT, "ContractFormationInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ContractFormationInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ContractFormationInstruction", ValidationType.TYPE_FORMAT, "ContractFormationInstruction", path, "", res.getError());
				}
				return success("ContractFormationInstruction", ValidationType.TYPE_FORMAT, "ContractFormationInstruction", path, "");
			})
			.collect(toList());
	}

}