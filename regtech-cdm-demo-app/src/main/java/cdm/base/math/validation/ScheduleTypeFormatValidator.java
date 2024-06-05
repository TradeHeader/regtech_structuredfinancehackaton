package cdm.base.math.validation;

import cdm.base.math.Schedule;
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

public class ScheduleTypeFormatValidator implements Validator<Schedule> {

	private List<ComparisonResult> getComparisonResults(Schedule o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Schedule> validate(RosettaPath path, Schedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Schedule", ValidationType.TYPE_FORMAT, "Schedule", path, "", error);
		}
		return success("Schedule", ValidationType.TYPE_FORMAT, "Schedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Schedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Schedule", ValidationType.TYPE_FORMAT, "Schedule", path, "", res.getError());
				}
				return success("Schedule", ValidationType.TYPE_FORMAT, "Schedule", path, "");
			})
			.collect(toList());
	}

}
