package cdm.base.staticdata.party.validation;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.base.staticdata.party.PayerReceiver;
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

public class PayerReceiverValidator implements Validator<PayerReceiver> {

	private List<ComparisonResult> getComparisonResults(PayerReceiver o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("payer", (CounterpartyRoleEnum) o.getPayer() != null ? 1 : 0, 1, 1), 
				checkCardinality("receiver", (CounterpartyRoleEnum) o.getReceiver() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<PayerReceiver> validate(RosettaPath path, PayerReceiver o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PayerReceiver", ValidationType.CARDINALITY, "PayerReceiver", path, "", error);
		}
		return success("PayerReceiver", ValidationType.CARDINALITY, "PayerReceiver", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PayerReceiver o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PayerReceiver", ValidationType.CARDINALITY, "PayerReceiver", path, "", res.getError());
				}
				return success("PayerReceiver", ValidationType.CARDINALITY, "PayerReceiver", path, "");
			})
			.collect(toList());
	}

}
