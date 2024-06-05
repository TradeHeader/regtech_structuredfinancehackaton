package cdm.product.common.schedule.validation;

import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.Frequency;
import cdm.base.datetime.Offset;
import cdm.product.common.schedule.PayRelativeToEnum;
import cdm.product.common.schedule.PaymentDateSchedule;
import cdm.product.common.schedule.PaymentDates;
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

public class PaymentDatesValidator implements Validator<PaymentDates> {

	private List<ComparisonResult> getComparisonResults(PaymentDates o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("paymentFrequency", (Frequency) o.getPaymentFrequency() != null ? 1 : 0, 0, 1), 
				checkCardinality("firstPaymentDate", (Date) o.getFirstPaymentDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("lastRegularPaymentDate", (Date) o.getLastRegularPaymentDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("paymentDateSchedule", (PaymentDateSchedule) o.getPaymentDateSchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("payRelativeTo", (PayRelativeToEnum) o.getPayRelativeTo() != null ? 1 : 0, 0, 1), 
				checkCardinality("paymentDaysOffset", (Offset) o.getPaymentDaysOffset() != null ? 1 : 0, 0, 1), 
				checkCardinality("paymentDatesAdjustments", (BusinessDayAdjustments) o.getPaymentDatesAdjustments() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PaymentDates> validate(RosettaPath path, PaymentDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PaymentDates", ValidationType.CARDINALITY, "PaymentDates", path, "", error);
		}
		return success("PaymentDates", ValidationType.CARDINALITY, "PaymentDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PaymentDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PaymentDates", ValidationType.CARDINALITY, "PaymentDates", path, "", res.getError());
				}
				return success("PaymentDates", ValidationType.CARDINALITY, "PaymentDates", path, "");
			})
			.collect(toList());
	}

}
