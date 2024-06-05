package cdm.observable.asset.fro.validation;

import cdm.observable.asset.fro.FloatingRateIndexIdentification;
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

public class FloatingRateIndexIdentificationTypeFormatValidator implements Validator<FloatingRateIndexIdentification> {

	private List<ComparisonResult> getComparisonResults(FloatingRateIndexIdentification o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FloatingRateIndexIdentification> validate(RosettaPath path, FloatingRateIndexIdentification o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingRateIndexIdentification", ValidationType.TYPE_FORMAT, "FloatingRateIndexIdentification", path, "", error);
		}
		return success("FloatingRateIndexIdentification", ValidationType.TYPE_FORMAT, "FloatingRateIndexIdentification", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingRateIndexIdentification o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingRateIndexIdentification", ValidationType.TYPE_FORMAT, "FloatingRateIndexIdentification", path, "", res.getError());
				}
				return success("FloatingRateIndexIdentification", ValidationType.TYPE_FORMAT, "FloatingRateIndexIdentification", path, "");
			})
			.collect(toList());
	}

}
