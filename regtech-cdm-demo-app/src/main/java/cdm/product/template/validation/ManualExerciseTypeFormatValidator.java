package cdm.product.template.validation;

import cdm.product.template.ManualExercise;
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

public class ManualExerciseTypeFormatValidator implements Validator<ManualExercise> {

	private List<ComparisonResult> getComparisonResults(ManualExercise o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ManualExercise> validate(RosettaPath path, ManualExercise o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ManualExercise", ValidationType.TYPE_FORMAT, "ManualExercise", path, "", error);
		}
		return success("ManualExercise", ValidationType.TYPE_FORMAT, "ManualExercise", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ManualExercise o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ManualExercise", ValidationType.TYPE_FORMAT, "ManualExercise", path, "", res.getError());
				}
				return success("ManualExercise", ValidationType.TYPE_FORMAT, "ManualExercise", path, "");
			})
			.collect(toList());
	}

}
