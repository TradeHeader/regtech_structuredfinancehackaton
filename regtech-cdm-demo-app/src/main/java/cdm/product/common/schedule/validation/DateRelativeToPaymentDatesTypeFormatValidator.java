package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.DateRelativeToPaymentDates;
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

public class DateRelativeToPaymentDatesTypeFormatValidator implements Validator<DateRelativeToPaymentDates> {

	private List<ComparisonResult> getComparisonResults(DateRelativeToPaymentDates o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DateRelativeToPaymentDates> validate(RosettaPath path, DateRelativeToPaymentDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DateRelativeToPaymentDates", ValidationType.TYPE_FORMAT, "DateRelativeToPaymentDates", path, "", error);
		}
		return success("DateRelativeToPaymentDates", ValidationType.TYPE_FORMAT, "DateRelativeToPaymentDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DateRelativeToPaymentDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DateRelativeToPaymentDates", ValidationType.TYPE_FORMAT, "DateRelativeToPaymentDates", path, "", res.getError());
				}
				return success("DateRelativeToPaymentDates", ValidationType.TYPE_FORMAT, "DateRelativeToPaymentDates", path, "");
			})
			.collect(toList());
	}

}
