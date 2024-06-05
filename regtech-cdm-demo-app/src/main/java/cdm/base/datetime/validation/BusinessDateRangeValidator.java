package cdm.base.datetime.validation;

import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.BusinessDateRange;
import cdm.base.datetime.BusinessDayConventionEnum;
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

public class BusinessDateRangeValidator implements Validator<BusinessDateRange> {

	private List<ComparisonResult> getComparisonResults(BusinessDateRange o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("startDate", (Date) o.getStartDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("endDate", (Date) o.getEndDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("businessDayConvention", (BusinessDayConventionEnum) o.getBusinessDayConvention() != null ? 1 : 0, 1, 1), 
				checkCardinality("businessCenters", (BusinessCenters) o.getBusinessCenters() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<BusinessDateRange> validate(RosettaPath path, BusinessDateRange o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BusinessDateRange", ValidationType.CARDINALITY, "BusinessDateRange", path, "", error);
		}
		return success("BusinessDateRange", ValidationType.CARDINALITY, "BusinessDateRange", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BusinessDateRange o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BusinessDateRange", ValidationType.CARDINALITY, "BusinessDateRange", path, "", res.getError());
				}
				return success("BusinessDateRange", ValidationType.CARDINALITY, "BusinessDateRange", path, "");
			})
			.collect(toList());
	}

}
