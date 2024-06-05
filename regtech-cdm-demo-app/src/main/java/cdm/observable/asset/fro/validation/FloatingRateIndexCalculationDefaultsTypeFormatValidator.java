package cdm.observable.asset.fro.validation;

import cdm.observable.asset.fro.FloatingRateIndexCalculationDefaults;
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

public class FloatingRateIndexCalculationDefaultsTypeFormatValidator implements Validator<FloatingRateIndexCalculationDefaults> {

	private List<ComparisonResult> getComparisonResults(FloatingRateIndexCalculationDefaults o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FloatingRateIndexCalculationDefaults> validate(RosettaPath path, FloatingRateIndexCalculationDefaults o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingRateIndexCalculationDefaults", ValidationType.TYPE_FORMAT, "FloatingRateIndexCalculationDefaults", path, "", error);
		}
		return success("FloatingRateIndexCalculationDefaults", ValidationType.TYPE_FORMAT, "FloatingRateIndexCalculationDefaults", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingRateIndexCalculationDefaults o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingRateIndexCalculationDefaults", ValidationType.TYPE_FORMAT, "FloatingRateIndexCalculationDefaults", path, "", res.getError());
				}
				return success("FloatingRateIndexCalculationDefaults", ValidationType.TYPE_FORMAT, "FloatingRateIndexCalculationDefaults", path, "");
			})
			.collect(toList());
	}

}
