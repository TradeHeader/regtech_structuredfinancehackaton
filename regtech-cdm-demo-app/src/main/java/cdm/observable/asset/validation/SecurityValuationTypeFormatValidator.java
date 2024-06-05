package cdm.observable.asset.validation;

import cdm.observable.asset.SecurityValuation;
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

public class SecurityValuationTypeFormatValidator implements Validator<SecurityValuation> {

	private List<ComparisonResult> getComparisonResults(SecurityValuation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<SecurityValuation> validate(RosettaPath path, SecurityValuation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SecurityValuation", ValidationType.TYPE_FORMAT, "SecurityValuation", path, "", error);
		}
		return success("SecurityValuation", ValidationType.TYPE_FORMAT, "SecurityValuation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SecurityValuation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SecurityValuation", ValidationType.TYPE_FORMAT, "SecurityValuation", path, "", res.getError());
				}
				return success("SecurityValuation", ValidationType.TYPE_FORMAT, "SecurityValuation", path, "");
			})
			.collect(toList());
	}

}
