package cdm.base.datetime.validation;

import cdm.base.datetime.BusinessCenters;
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

public class BusinessCentersTypeFormatValidator implements Validator<BusinessCenters> {

	private List<ComparisonResult> getComparisonResults(BusinessCenters o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<BusinessCenters> validate(RosettaPath path, BusinessCenters o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BusinessCenters", ValidationType.TYPE_FORMAT, "BusinessCenters", path, "", error);
		}
		return success("BusinessCenters", ValidationType.TYPE_FORMAT, "BusinessCenters", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BusinessCenters o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BusinessCenters", ValidationType.TYPE_FORMAT, "BusinessCenters", path, "", res.getError());
				}
				return success("BusinessCenters", ValidationType.TYPE_FORMAT, "BusinessCenters", path, "");
			})
			.collect(toList());
	}

}
