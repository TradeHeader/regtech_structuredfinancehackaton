package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.Security;
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

public class SecurityTypeFormatValidator implements Validator<Security> {

	private List<ComparisonResult> getComparisonResults(Security o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Security> validate(RosettaPath path, Security o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Security", ValidationType.TYPE_FORMAT, "Security", path, "", error);
		}
		return success("Security", ValidationType.TYPE_FORMAT, "Security", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Security o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Security", ValidationType.TYPE_FORMAT, "Security", path, "", res.getError());
				}
				return success("Security", ValidationType.TYPE_FORMAT, "Security", path, "");
			})
			.collect(toList());
	}

}
