package cdm.product.asset.floatingrate.validation;

import cdm.product.asset.floatingrate.FloatingRateProcessingParameters;
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

public class FloatingRateProcessingParametersTypeFormatValidator implements Validator<FloatingRateProcessingParameters> {

	private List<ComparisonResult> getComparisonResults(FloatingRateProcessingParameters o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FloatingRateProcessingParameters> validate(RosettaPath path, FloatingRateProcessingParameters o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingRateProcessingParameters", ValidationType.TYPE_FORMAT, "FloatingRateProcessingParameters", path, "", error);
		}
		return success("FloatingRateProcessingParameters", ValidationType.TYPE_FORMAT, "FloatingRateProcessingParameters", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingRateProcessingParameters o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingRateProcessingParameters", ValidationType.TYPE_FORMAT, "FloatingRateProcessingParameters", path, "", res.getError());
				}
				return success("FloatingRateProcessingParameters", ValidationType.TYPE_FORMAT, "FloatingRateProcessingParameters", path, "");
			})
			.collect(toList());
	}

}
