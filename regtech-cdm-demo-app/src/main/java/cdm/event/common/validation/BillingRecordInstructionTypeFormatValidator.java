package cdm.event.common.validation;

import cdm.event.common.BillingRecordInstruction;
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

public class BillingRecordInstructionTypeFormatValidator implements Validator<BillingRecordInstruction> {

	private List<ComparisonResult> getComparisonResults(BillingRecordInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<BillingRecordInstruction> validate(RosettaPath path, BillingRecordInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BillingRecordInstruction", ValidationType.TYPE_FORMAT, "BillingRecordInstruction", path, "", error);
		}
		return success("BillingRecordInstruction", ValidationType.TYPE_FORMAT, "BillingRecordInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BillingRecordInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BillingRecordInstruction", ValidationType.TYPE_FORMAT, "BillingRecordInstruction", path, "", res.getError());
				}
				return success("BillingRecordInstruction", ValidationType.TYPE_FORMAT, "BillingRecordInstruction", path, "");
			})
			.collect(toList());
	}

}
