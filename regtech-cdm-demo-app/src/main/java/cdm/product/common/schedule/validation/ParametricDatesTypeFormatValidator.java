package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.ParametricDates;
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

public class ParametricDatesTypeFormatValidator implements Validator<ParametricDates> {

	private List<ComparisonResult> getComparisonResults(ParametricDates o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ParametricDates> validate(RosettaPath path, ParametricDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ParametricDates", ValidationType.TYPE_FORMAT, "ParametricDates", path, "", error);
		}
		return success("ParametricDates", ValidationType.TYPE_FORMAT, "ParametricDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ParametricDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ParametricDates", ValidationType.TYPE_FORMAT, "ParametricDates", path, "", res.getError());
				}
				return success("ParametricDates", ValidationType.TYPE_FORMAT, "ParametricDates", path, "");
			})
			.collect(toList());
	}

}
