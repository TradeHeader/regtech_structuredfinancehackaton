package cdm.base.math.validation;

import cdm.base.math.MeasureSchedule;
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

public class MeasureScheduleTypeFormatValidator implements Validator<MeasureSchedule> {

	private List<ComparisonResult> getComparisonResults(MeasureSchedule o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<MeasureSchedule> validate(RosettaPath path, MeasureSchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MeasureSchedule", ValidationType.TYPE_FORMAT, "MeasureSchedule", path, "", error);
		}
		return success("MeasureSchedule", ValidationType.TYPE_FORMAT, "MeasureSchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MeasureSchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MeasureSchedule", ValidationType.TYPE_FORMAT, "MeasureSchedule", path, "", res.getError());
				}
				return success("MeasureSchedule", ValidationType.TYPE_FORMAT, "MeasureSchedule", path, "");
			})
			.collect(toList());
	}

}
