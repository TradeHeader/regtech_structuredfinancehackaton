package cdm.event.common.validation;

import cdm.base.math.QuantityChangeDirectionEnum;
import cdm.event.common.QuantityChangeInstruction;
import cdm.product.common.settlement.PriceQuantity;
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

public class QuantityChangeInstructionValidator implements Validator<QuantityChangeInstruction> {

	private List<ComparisonResult> getComparisonResults(QuantityChangeInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("change", (List<? extends PriceQuantity>) o.getChange() == null ? 0 : ((List<? extends PriceQuantity>) o.getChange()).size(), 1, 0), 
				checkCardinality("direction", (QuantityChangeDirectionEnum) o.getDirection() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<QuantityChangeInstruction> validate(RosettaPath path, QuantityChangeInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("QuantityChangeInstruction", ValidationType.CARDINALITY, "QuantityChangeInstruction", path, "", error);
		}
		return success("QuantityChangeInstruction", ValidationType.CARDINALITY, "QuantityChangeInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, QuantityChangeInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("QuantityChangeInstruction", ValidationType.CARDINALITY, "QuantityChangeInstruction", path, "", res.getError());
				}
				return success("QuantityChangeInstruction", ValidationType.CARDINALITY, "QuantityChangeInstruction", path, "");
			})
			.collect(toList());
	}

}
