package cdm.product.template.validation;

import cdm.product.template.SecurityPayout;
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

public class SecurityPayoutTypeFormatValidator implements Validator<SecurityPayout> {

	private List<ComparisonResult> getComparisonResults(SecurityPayout o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<SecurityPayout> validate(RosettaPath path, SecurityPayout o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SecurityPayout", ValidationType.TYPE_FORMAT, "SecurityPayout", path, "", error);
		}
		return success("SecurityPayout", ValidationType.TYPE_FORMAT, "SecurityPayout", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SecurityPayout o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SecurityPayout", ValidationType.TYPE_FORMAT, "SecurityPayout", path, "", res.getError());
				}
				return success("SecurityPayout", ValidationType.TYPE_FORMAT, "SecurityPayout", path, "");
			})
			.collect(toList());
	}

}
