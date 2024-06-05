package cdm.product.common.settlement.validation;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.observable.asset.Money;
import cdm.product.common.settlement.PaymentDetail;
import cdm.product.common.settlement.PaymentRule;
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

public class PaymentDetailValidator implements Validator<PaymentDetail> {

	private List<ComparisonResult> getComparisonResults(PaymentDetail o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("paymentDate", (AdjustableOrRelativeDate) o.getPaymentDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("paymentRule", (PaymentRule) o.getPaymentRule() != null ? 1 : 0, 1, 1), 
				checkCardinality("paymentAmount", (Money) o.getPaymentAmount() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PaymentDetail> validate(RosettaPath path, PaymentDetail o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PaymentDetail", ValidationType.CARDINALITY, "PaymentDetail", path, "", error);
		}
		return success("PaymentDetail", ValidationType.CARDINALITY, "PaymentDetail", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PaymentDetail o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PaymentDetail", ValidationType.CARDINALITY, "PaymentDetail", path, "", res.getError());
				}
				return success("PaymentDetail", ValidationType.CARDINALITY, "PaymentDetail", path, "");
			})
			.collect(toList());
	}

}
