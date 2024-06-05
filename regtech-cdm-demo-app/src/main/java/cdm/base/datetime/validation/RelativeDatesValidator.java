package cdm.base.datetime.validation;

import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.BusinessDayConventionEnum;
import cdm.base.datetime.DateRange;
import cdm.base.datetime.DayTypeEnum;
import cdm.base.datetime.PeriodEnum;
import cdm.base.datetime.RelativeDates;
import cdm.base.datetime.metafields.ReferenceWithMetaBusinessCenters;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.ReferenceWithMetaDate;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class RelativeDatesValidator implements Validator<RelativeDates> {

	private List<ComparisonResult> getComparisonResults(RelativeDates o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("periodMultiplier", (Integer) o.getPeriodMultiplier() != null ? 1 : 0, 1, 1), 
				checkCardinality("period", (PeriodEnum) o.getPeriod() != null ? 1 : 0, 1, 1), 
				checkCardinality("dayType", (DayTypeEnum) o.getDayType() != null ? 1 : 0, 0, 1), 
				checkCardinality("businessDayConvention", (BusinessDayConventionEnum) o.getBusinessDayConvention() != null ? 1 : 0, 1, 1), 
				checkCardinality("businessCenters", (BusinessCenters) o.getBusinessCenters() != null ? 1 : 0, 0, 1), 
				checkCardinality("businessCentersReference", (ReferenceWithMetaBusinessCenters) o.getBusinessCentersReference() != null ? 1 : 0, 0, 1), 
				checkCardinality("dateRelativeTo", (ReferenceWithMetaDate) o.getDateRelativeTo() != null ? 1 : 0, 0, 1), 
				checkCardinality("adjustedDate", (Date) o.getAdjustedDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("periodSkip", (Integer) o.getPeriodSkip() != null ? 1 : 0, 0, 1), 
				checkCardinality("scheduleBounds", (DateRange) o.getScheduleBounds() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<RelativeDates> validate(RosettaPath path, RelativeDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("RelativeDates", ValidationType.CARDINALITY, "RelativeDates", path, "", error);
		}
		return success("RelativeDates", ValidationType.CARDINALITY, "RelativeDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, RelativeDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("RelativeDates", ValidationType.CARDINALITY, "RelativeDates", path, "", res.getError());
				}
				return success("RelativeDates", ValidationType.CARDINALITY, "RelativeDates", path, "");
			})
			.collect(toList());
	}

}
