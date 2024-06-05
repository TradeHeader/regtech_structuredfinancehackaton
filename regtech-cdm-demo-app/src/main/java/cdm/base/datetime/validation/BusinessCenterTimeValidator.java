package cdm.base.datetime.validation;

import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.metafields.FieldWithMetaBusinessCenterEnum;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.time.LocalTime;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class BusinessCenterTimeValidator implements Validator<BusinessCenterTime> {

	private List<ComparisonResult> getComparisonResults(BusinessCenterTime o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("hourMinuteTime", (LocalTime) o.getHourMinuteTime() != null ? 1 : 0, 1, 1), 
				checkCardinality("businessCenter", (FieldWithMetaBusinessCenterEnum) o.getBusinessCenter() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<BusinessCenterTime> validate(RosettaPath path, BusinessCenterTime o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BusinessCenterTime", ValidationType.CARDINALITY, "BusinessCenterTime", path, "", error);
		}
		return success("BusinessCenterTime", ValidationType.CARDINALITY, "BusinessCenterTime", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BusinessCenterTime o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BusinessCenterTime", ValidationType.CARDINALITY, "BusinessCenterTime", path, "", res.getError());
				}
				return success("BusinessCenterTime", ValidationType.CARDINALITY, "BusinessCenterTime", path, "");
			})
			.collect(toList());
	}

}
