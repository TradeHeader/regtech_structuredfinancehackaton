package cdm.observable.asset.validation;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.base.datetime.BusinessCenterTime;
import cdm.observable.asset.PerformanceValuationDates;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.observable.common.TimeTypeEnum;
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

public class PerformanceValuationDatesValidator implements Validator<PerformanceValuationDates> {

	private List<ComparisonResult> getComparisonResults(PerformanceValuationDates o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("determinationMethod", (DeterminationMethodEnum) o.getDeterminationMethod() != null ? 1 : 0, 1, 1), 
				checkCardinality("valuationDates", (AdjustableRelativeOrPeriodicDates) o.getValuationDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("valuationDate", (AdjustableOrRelativeDate) o.getValuationDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("valuationTime", (BusinessCenterTime) o.getValuationTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("valuationTimeType", (TimeTypeEnum) o.getValuationTimeType() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PerformanceValuationDates> validate(RosettaPath path, PerformanceValuationDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PerformanceValuationDates", ValidationType.CARDINALITY, "PerformanceValuationDates", path, "", error);
		}
		return success("PerformanceValuationDates", ValidationType.CARDINALITY, "PerformanceValuationDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PerformanceValuationDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PerformanceValuationDates", ValidationType.CARDINALITY, "PerformanceValuationDates", path, "", res.getError());
				}
				return success("PerformanceValuationDates", ValidationType.CARDINALITY, "PerformanceValuationDates", path, "");
			})
			.collect(toList());
	}

}
