package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.PaymentCalculationPeriod;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class PaymentCalculationPeriodTypeFormatValidator implements Validator<PaymentCalculationPeriod> {

	private List<ComparisonResult> getComparisonResults(PaymentCalculationPeriod o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PaymentCalculationPeriod> validate(RosettaPath path, PaymentCalculationPeriod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PaymentCalculationPeriod", ValidationType.TYPE_FORMAT, "PaymentCalculationPeriod", path, "", error);
		}
		return success("PaymentCalculationPeriod", ValidationType.TYPE_FORMAT, "PaymentCalculationPeriod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PaymentCalculationPeriod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PaymentCalculationPeriod", ValidationType.TYPE_FORMAT, "PaymentCalculationPeriod", path, "", res.getError());
				}
				return success("PaymentCalculationPeriod", ValidationType.TYPE_FORMAT, "PaymentCalculationPeriod", path, "");
			})
			.collect(toList());
	}

}
