package cdm.event.common.validation;

import cdm.base.math.Quantity;
import cdm.event.common.ReturnInstruction;
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

public class ReturnInstructionValidator implements Validator<ReturnInstruction> {

	private List<ComparisonResult> getComparisonResults(ReturnInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("quantity", (List<? extends Quantity>) o.getQuantity() == null ? 0 : ((List<? extends Quantity>) o.getQuantity()).size(), 1, 0)
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
			return failure("ReturnInstruction", ValidationType.CARDINALITY, "ReturnInstruction", path, "", error);
		}
		return success("ReturnInstruction", ValidationType.CARDINALITY, "ReturnInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ReturnInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ReturnInstruction", ValidationType.CARDINALITY, "ReturnInstruction", path, "", res.getError());
				}
				return success("ReturnInstruction", ValidationType.CARDINALITY, "ReturnInstruction", path, "");
			})
			.collect(toList());
	}

}
