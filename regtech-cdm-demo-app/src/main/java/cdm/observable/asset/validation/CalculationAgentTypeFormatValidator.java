package cdm.observable.asset.validation;

import cdm.observable.asset.CalculationAgent;
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

public class CalculationAgentTypeFormatValidator implements Validator<CalculationAgent> {

	private List<ComparisonResult> getComparisonResults(CalculationAgent o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CalculationAgent> validate(RosettaPath path, CalculationAgent o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalculationAgent", ValidationType.TYPE_FORMAT, "CalculationAgent", path, "", error);
		}
		return success("CalculationAgent", ValidationType.TYPE_FORMAT, "CalculationAgent", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculationAgent o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculationAgent", ValidationType.TYPE_FORMAT, "CalculationAgent", path, "", res.getError());
				}
				return success("CalculationAgent", ValidationType.TYPE_FORMAT, "CalculationAgent", path, "");
			})
			.collect(toList());
	}

}
