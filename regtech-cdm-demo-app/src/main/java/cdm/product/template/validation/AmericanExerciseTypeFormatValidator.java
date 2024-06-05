package cdm.product.template.validation;

import cdm.product.template.AmericanExercise;
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

public class AmericanExerciseTypeFormatValidator implements Validator<AmericanExercise> {

	private List<ComparisonResult> getComparisonResults(AmericanExercise o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AmericanExercise> validate(RosettaPath path, AmericanExercise o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AmericanExercise", ValidationType.TYPE_FORMAT, "AmericanExercise", path, "", error);
		}
		return success("AmericanExercise", ValidationType.TYPE_FORMAT, "AmericanExercise", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AmericanExercise o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AmericanExercise", ValidationType.TYPE_FORMAT, "AmericanExercise", path, "", res.getError());
				}
				return success("AmericanExercise", ValidationType.TYPE_FORMAT, "AmericanExercise", path, "");
			})
			.collect(toList());
	}

}
