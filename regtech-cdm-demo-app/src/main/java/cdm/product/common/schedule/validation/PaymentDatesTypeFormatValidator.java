package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.PaymentDates;
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

public class PaymentDatesTypeFormatValidator implements Validator<PaymentDates> {

	private List<ComparisonResult> getComparisonResults(PaymentDates o) {
		return Lists.<ComparisonResult>newArrayList(
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
			return failure("PaymentDates", ValidationType.TYPE_FORMAT, "PaymentDates", path, "", error);
		}
		return success("PaymentDates", ValidationType.TYPE_FORMAT, "PaymentDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PaymentDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PaymentDates", ValidationType.TYPE_FORMAT, "PaymentDates", path, "", res.getError());
				}
				return success("PaymentDates", ValidationType.TYPE_FORMAT, "PaymentDates", path, "");
			})
			.collect(toList());
	}

}
