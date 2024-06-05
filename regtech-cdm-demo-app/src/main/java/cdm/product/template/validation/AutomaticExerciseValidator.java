package cdm.product.template.validation;

import cdm.product.template.AutomaticExercise;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AutomaticExerciseValidator implements Validator<AutomaticExercise> {

	private List<ComparisonResult> getComparisonResults(AutomaticExercise o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("thresholdRate", (BigDecimal) o.getThresholdRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("isApplicable", (Boolean) o.getIsApplicable() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AutomaticExercise> validate(RosettaPath path, AutomaticExercise o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AutomaticExercise", ValidationType.CARDINALITY, "AutomaticExercise", path, "", error);
		}
		return success("AutomaticExercise", ValidationType.CARDINALITY, "AutomaticExercise", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AutomaticExercise o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AutomaticExercise", ValidationType.CARDINALITY, "AutomaticExercise", path, "", res.getError());
				}
				return success("AutomaticExercise", ValidationType.CARDINALITY, "AutomaticExercise", path, "");
			})
			.collect(toList());
	}

}
