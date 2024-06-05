package cdm.event.common.validation;

import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class InstructionValidator implements Validator<Instruction> {

	private List<ComparisonResult> getComparisonResults(Instruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("primitiveInstruction", (PrimitiveInstruction) o.getPrimitiveInstruction() != null ? 1 : 0, 0, 1), 
				checkCardinality("before", (ReferenceWithMetaTradeState) o.getBefore() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Instruction> validate(RosettaPath path, Instruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Instruction", ValidationType.CARDINALITY, "Instruction", path, "", error);
		}
		return success("Instruction", ValidationType.CARDINALITY, "Instruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Instruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Instruction", ValidationType.CARDINALITY, "Instruction", path, "", res.getError());
				}
				return success("Instruction", ValidationType.CARDINALITY, "Instruction", path, "");
			})
			.collect(toList());
	}

}
