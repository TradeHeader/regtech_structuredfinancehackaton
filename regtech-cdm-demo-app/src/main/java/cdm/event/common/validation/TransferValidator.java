package cdm.event.common.validation;

import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.base.math.NonNegativeQuantity;
import cdm.base.staticdata.party.PartyReferencePayerReceiver;
import cdm.event.common.Reset;
import cdm.event.common.SettlementOrigin;
import cdm.event.common.Transfer;
import cdm.event.common.TransferExpression;
import cdm.observable.asset.Observable;
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

public class TransferValidator implements Validator<Transfer> {

	private List<ComparisonResult> getComparisonResults(Transfer o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("quantity", (NonNegativeQuantity) o.getQuantity() != null ? 1 : 0, 1, 1), 
				checkCardinality("observable", (Observable) o.getObservable() != null ? 1 : 0, 0, 1), 
				checkCardinality("payerReceiver", (PartyReferencePayerReceiver) o.getPayerReceiver() != null ? 1 : 0, 1, 1), 
				checkCardinality("settlementDate", (AdjustableOrAdjustedOrRelativeDate) o.getSettlementDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("settlementOrigin", (SettlementOrigin) o.getSettlementOrigin() != null ? 1 : 0, 0, 1), 
				checkCardinality("resetOrigin", (Reset) o.getResetOrigin() != null ? 1 : 0, 0, 1), 
				checkCardinality("transferExpression", (TransferExpression) o.getTransferExpression() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Transfer> validate(RosettaPath path, Transfer o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Transfer", ValidationType.CARDINALITY, "Transfer", path, "", error);
		}
		return success("Transfer", ValidationType.CARDINALITY, "Transfer", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Transfer o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Transfer", ValidationType.CARDINALITY, "Transfer", path, "", res.getError());
				}
				return success("Transfer", ValidationType.CARDINALITY, "Transfer", path, "");
			})
			.collect(toList());
	}

}
