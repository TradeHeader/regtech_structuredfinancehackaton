package cdm.base.datetime.validation;

import cdm.base.datetime.DateTimeList;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.time.ZonedDateTime;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class DateTimeListValidator implements Validator<DateTimeList> {

	private List<ComparisonResult> getComparisonResults(DateTimeList o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("dateTime", (List<ZonedDateTime>) o.getDateTime() == null ? 0 : ((List<ZonedDateTime>) o.getDateTime()).size(), 1, 0)
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
			return failure("DateTimeList", ValidationType.CARDINALITY, "DateTimeList", path, "", error);
		}
		return success("DateTimeList", ValidationType.CARDINALITY, "DateTimeList", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DateTimeList o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DateTimeList", ValidationType.CARDINALITY, "DateTimeList", path, "", res.getError());
				}
				return success("DateTimeList", ValidationType.CARDINALITY, "DateTimeList", path, "");
			})
			.collect(toList());
	}

}
