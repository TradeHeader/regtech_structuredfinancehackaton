package cdm.product.template.validation;

import cdm.product.template.BermudaExercise;
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

public class BermudaExerciseTypeFormatValidator implements Validator<BermudaExercise> {

	private List<ComparisonResult> getComparisonResults(BermudaExercise o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<BermudaExercise> validate(RosettaPath path, BermudaExercise o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BermudaExercise", ValidationType.TYPE_FORMAT, "BermudaExercise", path, "", error);
		}
		return success("BermudaExercise", ValidationType.TYPE_FORMAT, "BermudaExercise", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BermudaExercise o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BermudaExercise", ValidationType.TYPE_FORMAT, "BermudaExercise", path, "", res.getError());
				}
				return success("BermudaExercise", ValidationType.TYPE_FORMAT, "BermudaExercise", path, "");
			})
			.collect(toList());
	}

}
