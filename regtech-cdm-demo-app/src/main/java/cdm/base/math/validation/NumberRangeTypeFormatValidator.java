package cdm.base.math.validation;

import cdm.base.math.NumberRange;
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

public class NumberRangeTypeFormatValidator implements Validator<NumberRange> {

	private List<ComparisonResult> getComparisonResults(NumberRange o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<NumberRange> validate(RosettaPath path, NumberRange o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("NumberRange", ValidationType.TYPE_FORMAT, "NumberRange", path, "", error);
		}
		return success("NumberRange", ValidationType.TYPE_FORMAT, "NumberRange", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, NumberRange o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("NumberRange", ValidationType.TYPE_FORMAT, "NumberRange", path, "", res.getError());
				}
				return success("NumberRange", ValidationType.TYPE_FORMAT, "NumberRange", path, "");
			})
			.collect(toList());
	}

}
