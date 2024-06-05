package cdm.base.datetime.validation;

import cdm.base.datetime.CalculationPeriodFrequency;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkNumber;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CalculationPeriodFrequencyTypeFormatValidator implements Validator<CalculationPeriodFrequency> {

	private List<ComparisonResult> getComparisonResults(CalculationPeriodFrequency o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("periodMultiplier", o.getPeriodMultiplier(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<CalculationPeriodFrequency> validate(RosettaPath path, CalculationPeriodFrequency o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalculationPeriodFrequency", ValidationType.TYPE_FORMAT, "CalculationPeriodFrequency", path, "", error);
		}
		return success("CalculationPeriodFrequency", ValidationType.TYPE_FORMAT, "CalculationPeriodFrequency", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculationPeriodFrequency o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculationPeriodFrequency", ValidationType.TYPE_FORMAT, "CalculationPeriodFrequency", path, "", res.getError());
				}
				return success("CalculationPeriodFrequency", ValidationType.TYPE_FORMAT, "CalculationPeriodFrequency", path, "");
			})
			.collect(toList());
	}

}
