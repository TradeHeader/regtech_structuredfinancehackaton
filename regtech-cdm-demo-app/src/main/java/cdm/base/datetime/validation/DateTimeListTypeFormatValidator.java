package cdm.base.datetime.validation;

import cdm.base.datetime.DateTimeList;
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

public class DateTimeListTypeFormatValidator implements Validator<DateTimeList> {

	private List<ComparisonResult> getComparisonResults(DateTimeList o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DateTimeList> validate(RosettaPath path, DateTimeList o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DateTimeList", ValidationType.TYPE_FORMAT, "DateTimeList", path, "", error);
		}
		return success("DateTimeList", ValidationType.TYPE_FORMAT, "DateTimeList", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DateTimeList o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DateTimeList", ValidationType.TYPE_FORMAT, "DateTimeList", path, "", res.getError());
				}
				return success("DateTimeList", ValidationType.TYPE_FORMAT, "DateTimeList", path, "");
			})
			.collect(toList());
	}

}
