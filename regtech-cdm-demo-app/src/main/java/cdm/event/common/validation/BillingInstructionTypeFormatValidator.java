package cdm.event.common.validation;

import cdm.event.common.BillingInstruction;
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

public class BillingInstructionTypeFormatValidator implements Validator<BillingInstruction> {

	private List<ComparisonResult> getComparisonResults(BillingInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<BillingInstruction> validate(RosettaPath path, BillingInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BillingInstruction", ValidationType.TYPE_FORMAT, "BillingInstruction", path, "", error);
		}
		return success("BillingInstruction", ValidationType.TYPE_FORMAT, "BillingInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BillingInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BillingInstruction", ValidationType.TYPE_FORMAT, "BillingInstruction", path, "", res.getError());
				}
				return success("BillingInstruction", ValidationType.TYPE_FORMAT, "BillingInstruction", path, "");
			})
			.collect(toList());
	}

}
