package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.CalculationPeriod;
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

public class CalculationPeriodTypeFormatValidator implements Validator<CalculationPeriod> {

	private List<ComparisonResult> getComparisonResults(CalculationPeriod o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("calculationPeriodNumberOfDays", o.getCalculationPeriodNumberOfDays(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<CalculationPeriod> validate(RosettaPath path, CalculationPeriod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalculationPeriod", ValidationType.TYPE_FORMAT, "CalculationPeriod", path, "", error);
		}
		return success("CalculationPeriod", ValidationType.TYPE_FORMAT, "CalculationPeriod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculationPeriod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculationPeriod", ValidationType.TYPE_FORMAT, "CalculationPeriod", path, "", res.getError());
				}
				return success("CalculationPeriod", ValidationType.TYPE_FORMAT, "CalculationPeriod", path, "");
			})
			.collect(toList());
	}

}
