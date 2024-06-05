package cdm.product.template.validation;

import cdm.product.template.ExercisePeriod;
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

public class ExercisePeriodTypeFormatValidator implements Validator<ExercisePeriod> {

	private List<ComparisonResult> getComparisonResults(ExercisePeriod o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ExercisePeriod> validate(RosettaPath path, ExercisePeriod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ExercisePeriod", ValidationType.TYPE_FORMAT, "ExercisePeriod", path, "", error);
		}
		return success("ExercisePeriod", ValidationType.TYPE_FORMAT, "ExercisePeriod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ExercisePeriod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ExercisePeriod", ValidationType.TYPE_FORMAT, "ExercisePeriod", path, "", res.getError());
				}
				return success("ExercisePeriod", ValidationType.TYPE_FORMAT, "ExercisePeriod", path, "");
			})
			.collect(toList());
	}

}
