package cdm.base.datetime.validation;

import cdm.base.datetime.CalculationFrequency;
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

public class CalculationFrequencyTypeFormatValidator implements Validator<CalculationFrequency> {

	private List<ComparisonResult> getComparisonResults(CalculationFrequency o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CalculationFrequency> validate(RosettaPath path, CalculationFrequency o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalculationFrequency", ValidationType.TYPE_FORMAT, "CalculationFrequency", path, "", error);
		}
		return success("CalculationFrequency", ValidationType.TYPE_FORMAT, "CalculationFrequency", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculationFrequency o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculationFrequency", ValidationType.TYPE_FORMAT, "CalculationFrequency", path, "", res.getError());
				}
				return success("CalculationFrequency", ValidationType.TYPE_FORMAT, "CalculationFrequency", path, "");
			})
			.collect(toList());
	}

}
