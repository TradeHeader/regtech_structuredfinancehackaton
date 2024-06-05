package cdm.product.template.validation;

import cdm.product.template.ExerciseFee;
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

public class ExerciseFeeTypeFormatValidator implements Validator<ExerciseFee> {

	private List<ComparisonResult> getComparisonResults(ExerciseFee o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ExerciseFee> validate(RosettaPath path, ExerciseFee o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ExerciseFee", ValidationType.TYPE_FORMAT, "ExerciseFee", path, "", error);
		}
		return success("ExerciseFee", ValidationType.TYPE_FORMAT, "ExerciseFee", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ExerciseFee o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ExerciseFee", ValidationType.TYPE_FORMAT, "ExerciseFee", path, "", res.getError());
				}
				return success("ExerciseFee", ValidationType.TYPE_FORMAT, "ExerciseFee", path, "");
			})
			.collect(toList());
	}

}
