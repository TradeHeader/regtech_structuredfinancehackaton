package cdm.event.common.validation;

import cdm.base.staticdata.party.Party;
import cdm.event.common.BillingInstruction;
import cdm.event.common.BillingRecordInstruction;
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

public class BillingInstructionValidator implements Validator<BillingInstruction> {

	private List<ComparisonResult> getComparisonResults(BillingInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("sendingParty", (Party) o.getSendingParty() != null ? 1 : 0, 1, 1), 
				checkCardinality("receivingParty", (Party) o.getReceivingParty() != null ? 1 : 0, 1, 1), 
				checkCardinality("billingStartDate", (Date) o.getBillingStartDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("billingEndDate", (Date) o.getBillingEndDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("billingRecordInstruction", (List<? extends BillingRecordInstruction>) o.getBillingRecordInstruction() == null ? 0 : ((List<? extends BillingRecordInstruction>) o.getBillingRecordInstruction()).size(), 1, 0)
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
			return failure("BillingInstruction", ValidationType.CARDINALITY, "BillingInstruction", path, "", error);
		}
		return success("BillingInstruction", ValidationType.CARDINALITY, "BillingInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BillingInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BillingInstruction", ValidationType.CARDINALITY, "BillingInstruction", path, "", res.getError());
				}
				return success("BillingInstruction", ValidationType.CARDINALITY, "BillingInstruction", path, "");
			})
			.collect(toList());
	}

}
