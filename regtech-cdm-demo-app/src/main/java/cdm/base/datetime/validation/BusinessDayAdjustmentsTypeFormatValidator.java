package cdm.base.datetime.validation;

import cdm.base.datetime.BusinessDayAdjustments;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class BusinessDayAdjustmentsTypeFormatValidator implements Validator<BusinessDayAdjustments> {

	private List<ComparisonResult> getComparisonResults(BusinessDayAdjustments o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<BusinessDayAdjustments> validate(RosettaPath path, BusinessDayAdjustments o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BusinessDayAdjustments", ValidationType.TYPE_FORMAT, "BusinessDayAdjustments", path, "", error);
		}
		return success("BusinessDayAdjustments", ValidationType.TYPE_FORMAT, "BusinessDayAdjustments", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BusinessDayAdjustments o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BusinessDayAdjustments", ValidationType.TYPE_FORMAT, "BusinessDayAdjustments", path, "", res.getError());
				}
				return success("BusinessDayAdjustments", ValidationType.TYPE_FORMAT, "BusinessDayAdjustments", path, "");
			})
			.collect(toList());
	}

}
