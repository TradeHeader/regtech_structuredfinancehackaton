package cdm.observable.asset.validation;

import cdm.observable.asset.SecurityValuationModel;
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

public class SecurityValuationModelTypeFormatValidator implements Validator<SecurityValuationModel> {

	private List<ComparisonResult> getComparisonResults(SecurityValuationModel o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<SecurityValuationModel> validate(RosettaPath path, SecurityValuationModel o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SecurityValuationModel", ValidationType.TYPE_FORMAT, "SecurityValuationModel", path, "", error);
		}
		return success("SecurityValuationModel", ValidationType.TYPE_FORMAT, "SecurityValuationModel", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SecurityValuationModel o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SecurityValuationModel", ValidationType.TYPE_FORMAT, "SecurityValuationModel", path, "", res.getError());
				}
				return success("SecurityValuationModel", ValidationType.TYPE_FORMAT, "SecurityValuationModel", path, "");
			})
			.collect(toList());
	}

}
