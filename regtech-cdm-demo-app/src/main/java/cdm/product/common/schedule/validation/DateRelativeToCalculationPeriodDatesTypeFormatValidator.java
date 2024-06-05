package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.DateRelativeToCalculationPeriodDates;
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

public class DateRelativeToCalculationPeriodDatesTypeFormatValidator implements Validator<DateRelativeToCalculationPeriodDates> {

	private List<ComparisonResult> getComparisonResults(DateRelativeToCalculationPeriodDates o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DateRelativeToCalculationPeriodDates> validate(RosettaPath path, DateRelativeToCalculationPeriodDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DateRelativeToCalculationPeriodDates", ValidationType.TYPE_FORMAT, "DateRelativeToCalculationPeriodDates", path, "", error);
		}
		return success("DateRelativeToCalculationPeriodDates", ValidationType.TYPE_FORMAT, "DateRelativeToCalculationPeriodDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DateRelativeToCalculationPeriodDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DateRelativeToCalculationPeriodDates", ValidationType.TYPE_FORMAT, "DateRelativeToCalculationPeriodDates", path, "", res.getError());
				}
				return success("DateRelativeToCalculationPeriodDates", ValidationType.TYPE_FORMAT, "DateRelativeToCalculationPeriodDates", path, "");
			})
			.collect(toList());
	}

}
