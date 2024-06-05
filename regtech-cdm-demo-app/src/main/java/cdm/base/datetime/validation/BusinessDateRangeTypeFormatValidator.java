package cdm.base.datetime.validation;

import cdm.base.datetime.BusinessDateRange;
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

public class BusinessDateRangeTypeFormatValidator implements Validator<BusinessDateRange> {

	private List<ComparisonResult> getComparisonResults(BusinessDateRange o) {
		return Lists.<ComparisonResult>newArrayList(
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
			return failure("BusinessDateRange", ValidationType.TYPE_FORMAT, "BusinessDateRange", path, "", error);
		}
		return success("BusinessDateRange", ValidationType.TYPE_FORMAT, "BusinessDateRange", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BusinessDateRange o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BusinessDateRange", ValidationType.TYPE_FORMAT, "BusinessDateRange", path, "", res.getError());
				}
				return success("BusinessDateRange", ValidationType.TYPE_FORMAT, "BusinessDateRange", path, "");
			})
			.collect(toList());
	}

}
