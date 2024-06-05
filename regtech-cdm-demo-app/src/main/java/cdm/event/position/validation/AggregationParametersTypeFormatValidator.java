package cdm.event.position.validation;

import cdm.event.position.AggregationParameters;
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

public class AggregationParametersTypeFormatValidator implements Validator<AggregationParameters> {

	private List<ComparisonResult> getComparisonResults(AggregationParameters o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AggregationParameters> validate(RosettaPath path, AggregationParameters o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AggregationParameters", ValidationType.TYPE_FORMAT, "AggregationParameters", path, "", error);
		}
		return success("AggregationParameters", ValidationType.TYPE_FORMAT, "AggregationParameters", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AggregationParameters o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AggregationParameters", ValidationType.TYPE_FORMAT, "AggregationParameters", path, "", res.getError());
				}
				return success("AggregationParameters", ValidationType.TYPE_FORMAT, "AggregationParameters", path, "");
			})
			.collect(toList());
	}

}
