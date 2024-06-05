package cdm.event.common.validation;

import cdm.base.math.Quantity;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.event.common.CalculateTransferInstruction;
import cdm.event.common.TradeState;
import cdm.product.template.metafields.ReferenceWithMetaPayout;
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

public class CalculateTransferInstructionValidator implements Validator<CalculateTransferInstruction> {

	private List<ComparisonResult> getComparisonResults(CalculateTransferInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("tradeState", (TradeState) o.getTradeState() != null ? 1 : 0, 1, 1), 
				checkCardinality("payout", (ReferenceWithMetaPayout) o.getPayout() != null ? 1 : 0, 1, 1), 
				checkCardinality("payerReceiver", (PayerReceiver) o.getPayerReceiver() != null ? 1 : 0, 0, 1), 
				checkCardinality("quantity", (Quantity) o.getQuantity() != null ? 1 : 0, 0, 1), 
				checkCardinality("date", (Date) o.getDate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CalculateTransferInstruction> validate(RosettaPath path, CalculateTransferInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalculateTransferInstruction", ValidationType.CARDINALITY, "CalculateTransferInstruction", path, "", error);
		}
		return success("CalculateTransferInstruction", ValidationType.CARDINALITY, "CalculateTransferInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculateTransferInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculateTransferInstruction", ValidationType.CARDINALITY, "CalculateTransferInstruction", path, "", res.getError());
				}
				return success("CalculateTransferInstruction", ValidationType.CARDINALITY, "CalculateTransferInstruction", path, "");
			})
			.collect(toList());
	}

}
