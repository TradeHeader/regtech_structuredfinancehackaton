package cdm.event.common.validation;

import cdm.event.common.ExerciseEvent;
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

public class ExerciseEventTypeFormatValidator implements Validator<ExerciseEvent> {

	private List<ComparisonResult> getComparisonResults(ExerciseEvent o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ExerciseEvent> validate(RosettaPath path, ExerciseEvent o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ExerciseEvent", ValidationType.TYPE_FORMAT, "ExerciseEvent", path, "", error);
		}
		return success("ExerciseEvent", ValidationType.TYPE_FORMAT, "ExerciseEvent", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ExerciseEvent o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ExerciseEvent", ValidationType.TYPE_FORMAT, "ExerciseEvent", path, "", res.getError());
				}
				return success("ExerciseEvent", ValidationType.TYPE_FORMAT, "ExerciseEvent", path, "");
			})
			.collect(toList());
	}

}
