package cdm.event.common.validation;

import cdm.event.common.Valuation;
import cdm.event.common.ValuationInstruction;
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

public class ValuationInstructionValidator implements Validator<ValuationInstruction> {

	private List<ComparisonResult> getComparisonResults(ValuationInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("valuation", (List<? extends Valuation>) o.getValuation() == null ? 0 : ((List<? extends Valuation>) o.getValuation()).size(), 1, 0), 
				checkCardinality("replace", (Boolean) o.getReplace() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<ValuationInstruction> validate(RosettaPath path, ValuationInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ValuationInstruction", ValidationType.CARDINALITY, "ValuationInstruction", path, "", error);
		}
		return success("ValuationInstruction", ValidationType.CARDINALITY, "ValuationInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ValuationInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ValuationInstruction", ValidationType.CARDINALITY, "ValuationInstruction", path, "", res.getError());
				}
				return success("ValuationInstruction", ValidationType.CARDINALITY, "ValuationInstruction", path, "");
			})
			.collect(toList());
	}

}
