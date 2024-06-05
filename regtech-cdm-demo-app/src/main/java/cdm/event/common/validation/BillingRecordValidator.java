package cdm.event.common.validation;

import cdm.event.common.BillingRecord;
import cdm.event.common.Transfer;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.observable.asset.Money;
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

public class BillingRecordValidator implements Validator<BillingRecord> {

	private List<ComparisonResult> getComparisonResults(BillingRecord o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("tradeState", (ReferenceWithMetaTradeState) o.getTradeState() != null ? 1 : 0, 1, 1), 
				checkCardinality("recordTransfer", (Transfer) o.getRecordTransfer() != null ? 1 : 0, 1, 1), 
				checkCardinality("recordStartDate", (Date) o.getRecordStartDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("recordEndDate", (Date) o.getRecordEndDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("minimumFee", (Money) o.getMinimumFee() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<BillingRecord> validate(RosettaPath path, BillingRecord o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BillingRecord", ValidationType.CARDINALITY, "BillingRecord", path, "", error);
		}
		return success("BillingRecord", ValidationType.CARDINALITY, "BillingRecord", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BillingRecord o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BillingRecord", ValidationType.CARDINALITY, "BillingRecord", path, "", res.getError());
				}
				return success("BillingRecord", ValidationType.CARDINALITY, "BillingRecord", path, "");
			})
			.collect(toList());
	}

}
