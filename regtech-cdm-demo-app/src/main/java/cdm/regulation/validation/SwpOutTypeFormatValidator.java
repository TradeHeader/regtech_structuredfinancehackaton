package cdm.regulation.validation;

import cdm.regulation.SwpOut;
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

public class SwpOutTypeFormatValidator implements Validator<SwpOut> {

	private List<ComparisonResult> getComparisonResults(SwpOut o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<SwpOut> validate(RosettaPath path, SwpOut o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SwpOut", ValidationType.TYPE_FORMAT, "SwpOut", path, "", error);
		}
		return success("SwpOut", ValidationType.TYPE_FORMAT, "SwpOut", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SwpOut o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SwpOut", ValidationType.TYPE_FORMAT, "SwpOut", path, "", res.getError());
				}
				return success("SwpOut", ValidationType.TYPE_FORMAT, "SwpOut", path, "");
			})
			.collect(toList());
	}

}
