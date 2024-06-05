package cdm.product.common.schedule.validation;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.product.common.schedule.PaymentDateSchedule;
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

public class PaymentDateScheduleValidator implements Validator<PaymentDateSchedule> {

	private List<ComparisonResult> getComparisonResults(PaymentDateSchedule o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("finalPaymentDate", (AdjustableOrRelativeDate) o.getFinalPaymentDate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PaymentDateSchedule> validate(RosettaPath path, PaymentDateSchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PaymentDateSchedule", ValidationType.CARDINALITY, "PaymentDateSchedule", path, "", error);
		}
		return success("PaymentDateSchedule", ValidationType.CARDINALITY, "PaymentDateSchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PaymentDateSchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PaymentDateSchedule", ValidationType.CARDINALITY, "PaymentDateSchedule", path, "", res.getError());
				}
				return success("PaymentDateSchedule", ValidationType.CARDINALITY, "PaymentDateSchedule", path, "");
			})
			.collect(toList());
	}

}