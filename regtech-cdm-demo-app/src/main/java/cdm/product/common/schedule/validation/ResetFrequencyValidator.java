package cdm.product.common.schedule.validation;

import cdm.base.datetime.PeriodExtendedEnum;
import cdm.product.common.schedule.ResetFrequency;
import cdm.product.common.schedule.WeeklyRollConventionEnum;
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

public class ResetFrequencyValidator implements Validator<ResetFrequency> {

	private List<ComparisonResult> getComparisonResults(ResetFrequency o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("periodMultiplier", (Integer) o.getPeriodMultiplier() != null ? 1 : 0, 1, 1), 
				checkCardinality("period", (PeriodExtendedEnum) o.getPeriod() != null ? 1 : 0, 1, 1), 
				checkCardinality("weeklyRollConvention", (WeeklyRollConventionEnum) o.getWeeklyRollConvention() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ResetFrequency> validate(RosettaPath path, ResetFrequency o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ResetFrequency", ValidationType.CARDINALITY, "ResetFrequency", path, "", error);
		}
		return success("ResetFrequency", ValidationType.CARDINALITY, "ResetFrequency", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ResetFrequency o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ResetFrequency", ValidationType.CARDINALITY, "ResetFrequency", path, "", res.getError());
				}
				return success("ResetFrequency", ValidationType.CARDINALITY, "ResetFrequency", path, "");
			})
			.collect(toList());
	}

}
