package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.CalculationPeriodData;
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

public class CalculationPeriodDataTypeFormatValidator implements Validator<CalculationPeriodData> {

	private List<ComparisonResult> getComparisonResults(CalculationPeriodData o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("daysInPeriod", o.getDaysInPeriod(), empty(), of(0), empty(), empty()), 
				checkNumber("daysInLeapYearPeriod", o.getDaysInLeapYearPeriod(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<CalculationPeriodData> validate(RosettaPath path, CalculationPeriodData o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalculationPeriodData", ValidationType.TYPE_FORMAT, "CalculationPeriodData", path, "", error);
		}
		return success("CalculationPeriodData", ValidationType.TYPE_FORMAT, "CalculationPeriodData", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculationPeriodData o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculationPeriodData", ValidationType.TYPE_FORMAT, "CalculationPeriodData", path, "", res.getError());
				}
				return success("CalculationPeriodData", ValidationType.TYPE_FORMAT, "CalculationPeriodData", path, "");
			})
			.collect(toList());
	}

}
