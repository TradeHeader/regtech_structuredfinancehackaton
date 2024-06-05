package cdm.base.datetime.validation;

import cdm.base.datetime.CalculationPeriodFrequency;
import cdm.base.datetime.PeriodExtendedEnum;
import cdm.base.datetime.RollConventionEnum;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CalculationPeriodFrequencyValidator implements Validator<CalculationPeriodFrequency> {

	private List<ComparisonResult> getComparisonResults(CalculationPeriodFrequency o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("periodMultiplier", (Integer) o.getPeriodMultiplier() != null ? 1 : 0, 1, 1), 
				checkCardinality("period", (PeriodExtendedEnum) o.getPeriod() != null ? 1 : 0, 1, 1), 
				checkCardinality("rollConvention", (RollConventionEnum) o.getRollConvention() != null ? 1 : 0, 1, 1), 
				checkCardinality("balanceOfFirstPeriod", (Boolean) o.getBalanceOfFirstPeriod() != null ? 1 : 0, 0, 1)
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
			return failure("CalculationPeriodFrequency", ValidationType.CARDINALITY, "CalculationPeriodFrequency", path, "", error);
		}
		return success("CalculationPeriodFrequency", ValidationType.CARDINALITY, "CalculationPeriodFrequency", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculationPeriodFrequency o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculationPeriodFrequency", ValidationType.CARDINALITY, "CalculationPeriodFrequency", path, "", res.getError());
				}
				return success("CalculationPeriodFrequency", ValidationType.CARDINALITY, "CalculationPeriodFrequency", path, "");
			})
			.collect(toList());
	}

}
