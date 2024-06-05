package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.DateRelativeToValuationDates;
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

public class DateRelativeToValuationDatesTypeFormatValidator implements Validator<DateRelativeToValuationDates> {

	private List<ComparisonResult> getComparisonResults(DateRelativeToValuationDates o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DateRelativeToValuationDates> validate(RosettaPath path, DateRelativeToValuationDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DateRelativeToValuationDates", ValidationType.TYPE_FORMAT, "DateRelativeToValuationDates", path, "", error);
		}
		return success("DateRelativeToValuationDates", ValidationType.TYPE_FORMAT, "DateRelativeToValuationDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DateRelativeToValuationDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DateRelativeToValuationDates", ValidationType.TYPE_FORMAT, "DateRelativeToValuationDates", path, "", res.getError());
				}
				return success("DateRelativeToValuationDates", ValidationType.TYPE_FORMAT, "DateRelativeToValuationDates", path, "");
			})
			.collect(toList());
	}

}
