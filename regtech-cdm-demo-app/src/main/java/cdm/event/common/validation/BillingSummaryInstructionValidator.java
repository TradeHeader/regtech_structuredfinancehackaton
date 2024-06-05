package cdm.event.common.validation;

import cdm.event.common.BillingSummaryInstruction;
import cdm.event.common.RecordAmountTypeEnum;
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

public class BillingSummaryInstructionValidator implements Validator<BillingSummaryInstruction> {

	private List<ComparisonResult> getComparisonResults(BillingSummaryInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("summaryAmountType", (RecordAmountTypeEnum) o.getSummaryAmountType() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<BillingSummaryInstruction> validate(RosettaPath path, BillingSummaryInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BillingSummaryInstruction", ValidationType.CARDINALITY, "BillingSummaryInstruction", path, "", error);
		}
		return success("BillingSummaryInstruction", ValidationType.CARDINALITY, "BillingSummaryInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BillingSummaryInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BillingSummaryInstruction", ValidationType.CARDINALITY, "BillingSummaryInstruction", path, "", res.getError());
				}
				return success("BillingSummaryInstruction", ValidationType.CARDINALITY, "BillingSummaryInstruction", path, "");
			})
			.collect(toList());
	}

}
