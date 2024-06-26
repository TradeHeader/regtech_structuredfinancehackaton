package cdm.product.template.validation;

import cdm.product.template.SecurityLeg;
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

public class SecurityLegTypeFormatValidator implements Validator<SecurityLeg> {

	private List<ComparisonResult> getComparisonResults(SecurityLeg o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<SecurityLeg> validate(RosettaPath path, SecurityLeg o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SecurityLeg", ValidationType.TYPE_FORMAT, "SecurityLeg", path, "", error);
		}
		return success("SecurityLeg", ValidationType.TYPE_FORMAT, "SecurityLeg", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SecurityLeg o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SecurityLeg", ValidationType.TYPE_FORMAT, "SecurityLeg", path, "", res.getError());
				}
				return success("SecurityLeg", ValidationType.TYPE_FORMAT, "SecurityLeg", path, "");
			})
			.collect(toList());
	}

}
