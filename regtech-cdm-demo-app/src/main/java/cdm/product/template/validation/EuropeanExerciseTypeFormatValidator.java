package cdm.product.template.validation;

import cdm.product.template.EuropeanExercise;
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

public class EuropeanExerciseTypeFormatValidator implements Validator<EuropeanExercise> {

	private List<ComparisonResult> getComparisonResults(EuropeanExercise o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<EuropeanExercise> validate(RosettaPath path, EuropeanExercise o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EuropeanExercise", ValidationType.TYPE_FORMAT, "EuropeanExercise", path, "", error);
		}
		return success("EuropeanExercise", ValidationType.TYPE_FORMAT, "EuropeanExercise", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EuropeanExercise o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EuropeanExercise", ValidationType.TYPE_FORMAT, "EuropeanExercise", path, "", res.getError());
				}
				return success("EuropeanExercise", ValidationType.TYPE_FORMAT, "EuropeanExercise", path, "");
			})
			.collect(toList());
	}

}
