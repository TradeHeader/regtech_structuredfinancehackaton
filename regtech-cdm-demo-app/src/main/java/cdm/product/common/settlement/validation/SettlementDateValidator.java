package cdm.product.common.settlement.validation;

import cdm.base.datetime.AdjustableDates;
import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.base.datetime.BusinessDateRange;
import cdm.product.common.settlement.SettlementDate;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class SettlementDateValidator implements Validator<SettlementDate> {

	private List<ComparisonResult> getComparisonResults(SettlementDate o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("adjustableOrRelativeDate", (AdjustableOrAdjustedOrRelativeDate) o.getAdjustableOrRelativeDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("valueDate", (Date) o.getValueDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("adjustableDates", (AdjustableDates) o.getAdjustableDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("businessDateRange", (BusinessDateRange) o.getBusinessDateRange() != null ? 1 : 0, 0, 1), 
				checkCardinality("cashSettlementBusinessDays", (Integer) o.getCashSettlementBusinessDays() != null ? 1 : 0, 0, 1), 
				checkCardinality("paymentDelay", (Boolean) o.getPaymentDelay() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<SettlementDate> validate(RosettaPath path, SettlementDate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SettlementDate", ValidationType.CARDINALITY, "SettlementDate", path, "", error);
		}
		return success("SettlementDate", ValidationType.CARDINALITY, "SettlementDate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SettlementDate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SettlementDate", ValidationType.CARDINALITY, "SettlementDate", path, "", res.getError());
				}
				return success("SettlementDate", ValidationType.CARDINALITY, "SettlementDate", path, "");
			})
			.collect(toList());
	}

}
