package cdm.product.template.validation;

import cdm.product.template.Duration;
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

public class DurationTypeFormatValidator implements Validator<Duration> {

	private List<ComparisonResult> getComparisonResults(Duration o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Duration> validate(RosettaPath path, Duration o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Duration", ValidationType.TYPE_FORMAT, "Duration", path, "", error);
		}
		return success("Duration", ValidationType.TYPE_FORMAT, "Duration", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Duration o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Duration", ValidationType.TYPE_FORMAT, "Duration", path, "", res.getError());
				}
				return success("Duration", ValidationType.TYPE_FORMAT, "Duration", path, "");
			})
			.collect(toList());
	}

}
