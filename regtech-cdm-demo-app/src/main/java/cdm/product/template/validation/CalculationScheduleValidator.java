package cdm.product.template.validation;

import cdm.product.template.CalculationSchedule;
import cdm.product.template.SchedulePeriod;
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

public class CalculationScheduleValidator implements Validator<CalculationSchedule> {

	private List<ComparisonResult> getComparisonResults(CalculationSchedule o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("schedulePeriod", (List<? extends SchedulePeriod>) o.getSchedulePeriod() == null ? 0 : ((List<? extends SchedulePeriod>) o.getSchedulePeriod()).size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<CalculationSchedule> validate(RosettaPath path, CalculationSchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalculationSchedule", ValidationType.CARDINALITY, "CalculationSchedule", path, "", error);
		}
		return success("CalculationSchedule", ValidationType.CARDINALITY, "CalculationSchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculationSchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculationSchedule", ValidationType.CARDINALITY, "CalculationSchedule", path, "", res.getError());
				}
				return success("CalculationSchedule", ValidationType.CARDINALITY, "CalculationSchedule", path, "");
			})
			.collect(toList());
	}

}
