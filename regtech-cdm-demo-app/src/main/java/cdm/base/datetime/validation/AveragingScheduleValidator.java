package cdm.base.datetime.validation;

import cdm.base.datetime.AveragingSchedule;
import cdm.base.datetime.CalculationPeriodFrequency;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class AveragingScheduleValidator implements Validator<AveragingSchedule> {

	private List<ComparisonResult> getComparisonResults(AveragingSchedule o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("startDate", (Date) o.getStartDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("endDate", (Date) o.getEndDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("averagingPeriodFrequency", (CalculationPeriodFrequency) o.getAveragingPeriodFrequency() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<AveragingSchedule> validate(RosettaPath path, AveragingSchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AveragingSchedule", ValidationType.CARDINALITY, "AveragingSchedule", path, "", error);
		}
		return success("AveragingSchedule", ValidationType.CARDINALITY, "AveragingSchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AveragingSchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AveragingSchedule", ValidationType.CARDINALITY, "AveragingSchedule", path, "", res.getError());
				}
				return success("AveragingSchedule", ValidationType.CARDINALITY, "AveragingSchedule", path, "");
			})
			.collect(toList());
	}

}
