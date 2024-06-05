package cdm.event.common.validation;

import cdm.event.common.BillingSummary;
import cdm.event.common.RecordAmountTypeEnum;
import cdm.event.common.Transfer;
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

public class BillingSummaryValidator implements Validator<BillingSummary> {

	private List<ComparisonResult> getComparisonResults(BillingSummary o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("summaryTransfer", (Transfer) o.getSummaryTransfer() != null ? 1 : 0, 0, 1), 
				checkCardinality("summaryAmountType", (RecordAmountTypeEnum) o.getSummaryAmountType() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<BillingSummary> validate(RosettaPath path, BillingSummary o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BillingSummary", ValidationType.CARDINALITY, "BillingSummary", path, "", error);
		}
		return success("BillingSummary", ValidationType.CARDINALITY, "BillingSummary", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BillingSummary o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BillingSummary", ValidationType.CARDINALITY, "BillingSummary", path, "", res.getError());
				}
				return success("BillingSummary", ValidationType.CARDINALITY, "BillingSummary", path, "");
			})
			.collect(toList());
	}

}
