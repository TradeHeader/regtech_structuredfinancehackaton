package cdm.event.common.validation;

import cdm.event.common.BillingRecordInstruction;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.observable.event.Observation;
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

public class BillingRecordInstructionValidator implements Validator<BillingRecordInstruction> {

	private List<ComparisonResult> getComparisonResults(BillingRecordInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("tradeState", (ReferenceWithMetaTradeState) o.getTradeState() != null ? 1 : 0, 1, 1), 
				checkCardinality("observation", (List<? extends Observation>) o.getObservation() == null ? 0 : ((List<? extends Observation>) o.getObservation()).size(), 1, 0), 
				checkCardinality("recordStartDate", (Date) o.getRecordStartDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("recordEndDate", (Date) o.getRecordEndDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("settlementDate", (Date) o.getSettlementDate() != null ? 1 : 0, 1, 1)
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
			return failure("BillingRecordInstruction", ValidationType.CARDINALITY, "BillingRecordInstruction", path, "", error);
		}
		return success("BillingRecordInstruction", ValidationType.CARDINALITY, "BillingRecordInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BillingRecordInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BillingRecordInstruction", ValidationType.CARDINALITY, "BillingRecordInstruction", path, "", res.getError());
				}
				return success("BillingRecordInstruction", ValidationType.CARDINALITY, "BillingRecordInstruction", path, "");
			})
			.collect(toList());
	}

}
