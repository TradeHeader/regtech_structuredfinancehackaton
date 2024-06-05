package cdm.event.common.validation;

import cdm.event.common.TransferInstruction;
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

public class TransferInstructionValidator implements Validator<TransferInstruction> {

	private List<ComparisonResult> getComparisonResults(TransferInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<TransferInstruction> validate(RosettaPath path, TransferInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TransferInstruction", ValidationType.CARDINALITY, "TransferInstruction", path, "", error);
		}
		return success("TransferInstruction", ValidationType.CARDINALITY, "TransferInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TransferInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TransferInstruction", ValidationType.CARDINALITY, "TransferInstruction", path, "", res.getError());
				}
				return success("TransferInstruction", ValidationType.CARDINALITY, "TransferInstruction", path, "");
			})
			.collect(toList());
	}

}