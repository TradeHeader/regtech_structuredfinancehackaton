package cdm.product.template.validation;

import cdm.product.template.ExerciseProcedure;
import cdm.product.template.OptionExercise;
import cdm.product.template.OptionStrike;
import cdm.product.template.OptionStyle;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class OptionExerciseValidator implements Validator<OptionExercise> {

	private List<ComparisonResult> getComparisonResults(OptionExercise o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("optionStyle", (OptionStyle) o.getOptionStyle() != null ? 1 : 0, 1, 1), 
				checkCardinality("strike", (OptionStrike) o.getStrike() != null ? 1 : 0, 0, 1), 
				checkCardinality("exerciseProcedure", (ExerciseProcedure) o.getExerciseProcedure() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<OptionExercise> validate(RosettaPath path, OptionExercise o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OptionExercise", ValidationType.CARDINALITY, "OptionExercise", path, "", error);
		}
		return success("OptionExercise", ValidationType.CARDINALITY, "OptionExercise", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OptionExercise o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OptionExercise", ValidationType.CARDINALITY, "OptionExercise", path, "", res.getError());
				}
				return success("OptionExercise", ValidationType.CARDINALITY, "OptionExercise", path, "");
			})
			.collect(toList());
	}

}
