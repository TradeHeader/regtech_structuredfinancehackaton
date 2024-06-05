package cdm.observable.asset.fro.validation;

import cdm.observable.asset.fro.FloatingRateIndexDefinition;
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

public class FloatingRateIndexDefinitionTypeFormatValidator implements Validator<FloatingRateIndexDefinition> {

	private List<ComparisonResult> getComparisonResults(FloatingRateIndexDefinition o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FloatingRateIndexDefinition> validate(RosettaPath path, FloatingRateIndexDefinition o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingRateIndexDefinition", ValidationType.TYPE_FORMAT, "FloatingRateIndexDefinition", path, "", error);
		}
		return success("FloatingRateIndexDefinition", ValidationType.TYPE_FORMAT, "FloatingRateIndexDefinition", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingRateIndexDefinition o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingRateIndexDefinition", ValidationType.TYPE_FORMAT, "FloatingRateIndexDefinition", path, "", res.getError());
				}
				return success("FloatingRateIndexDefinition", ValidationType.TYPE_FORMAT, "FloatingRateIndexDefinition", path, "");
			})
			.collect(toList());
	}

}
