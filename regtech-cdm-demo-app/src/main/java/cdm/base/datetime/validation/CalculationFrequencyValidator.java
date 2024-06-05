package cdm.base.datetime.validation;

import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.CalculationFrequency;
import cdm.base.datetime.DayOfWeekEnum;
import cdm.base.datetime.Period;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CalculationFrequencyValidator implements Validator<CalculationFrequency> {

	private List<ComparisonResult> getComparisonResults(CalculationFrequency o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("period", (Period) o.getPeriod() != null ? 1 : 0, 1, 1), 
				checkCardinality("monthOfYear", (BigDecimal) o.getMonthOfYear() != null ? 1 : 0, 0, 1), 
				checkCardinality("dayOfMonth", (BigDecimal) o.getDayOfMonth() != null ? 1 : 0, 0, 1), 
				checkCardinality("dayOfWeek", (DayOfWeekEnum) o.getDayOfWeek() != null ? 1 : 0, 0, 1), 
				checkCardinality("weekOfMonth", (BigDecimal) o.getWeekOfMonth() != null ? 1 : 0, 0, 1), 
				checkCardinality("offsetDays", (BigDecimal) o.getOffsetDays() != null ? 1 : 0, 1, 1), 
				checkCardinality("dateLocation", (BusinessCenterTime) o.getDateLocation() != null ? 1 : 0, 1, 1)
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
			return failure("CalculationFrequency", ValidationType.CARDINALITY, "CalculationFrequency", path, "", error);
		}
		return success("CalculationFrequency", ValidationType.CARDINALITY, "CalculationFrequency", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculationFrequency o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculationFrequency", ValidationType.CARDINALITY, "CalculationFrequency", path, "", res.getError());
				}
				return success("CalculationFrequency", ValidationType.CARDINALITY, "CalculationFrequency", path, "");
			})
			.collect(toList());
	}

}
