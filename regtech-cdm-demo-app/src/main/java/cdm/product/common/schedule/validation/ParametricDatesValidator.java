package cdm.product.common.schedule.validation;

import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.DayOfWeekEnum;
import cdm.base.datetime.DayTypeEnum;
import cdm.product.asset.DayDistributionEnum;
import cdm.product.common.schedule.Lag;
import cdm.product.common.schedule.ParametricDates;
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

public class ParametricDatesValidator implements Validator<ParametricDates> {

	private List<ComparisonResult> getComparisonResults(ParametricDates o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("dayType", (DayTypeEnum) o.getDayType() != null ? 1 : 0, 1, 1), 
				checkCardinality("dayDistribution", (DayDistributionEnum) o.getDayDistribution() != null ? 1 : 0, 0, 1), 
				checkCardinality("dayOfWeek", (List<DayOfWeekEnum>) o.getDayOfWeek() == null ? 0 : ((List<DayOfWeekEnum>) o.getDayOfWeek()).size(), 0, 7), 
				checkCardinality("dayFrequency", (BigDecimal) o.getDayFrequency() != null ? 1 : 0, 0, 1), 
				checkCardinality("lag", (Lag) o.getLag() != null ? 1 : 0, 0, 1), 
				checkCardinality("businessCenters", (BusinessCenters) o.getBusinessCenters() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<ParametricDates> validate(RosettaPath path, ParametricDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ParametricDates", ValidationType.CARDINALITY, "ParametricDates", path, "", error);
		}
		return success("ParametricDates", ValidationType.CARDINALITY, "ParametricDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ParametricDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ParametricDates", ValidationType.CARDINALITY, "ParametricDates", path, "", res.getError());
				}
				return success("ParametricDates", ValidationType.CARDINALITY, "ParametricDates", path, "");
			})
			.collect(toList());
	}

}
