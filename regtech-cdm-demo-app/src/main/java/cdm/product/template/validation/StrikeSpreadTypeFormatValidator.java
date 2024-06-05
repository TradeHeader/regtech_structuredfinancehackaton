package cdm.product.template.validation;

import cdm.product.template.StrikeSpread;
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

public class StrikeSpreadTypeFormatValidator implements Validator<StrikeSpread> {

	private List<ComparisonResult> getComparisonResults(StrikeSpread o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<StrikeSpread> validate(RosettaPath path, StrikeSpread o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("StrikeSpread", ValidationType.TYPE_FORMAT, "StrikeSpread", path, "", error);
		}
		return success("StrikeSpread", ValidationType.TYPE_FORMAT, "StrikeSpread", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, StrikeSpread o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("StrikeSpread", ValidationType.TYPE_FORMAT, "StrikeSpread", path, "", res.getError());
				}
				return success("StrikeSpread", ValidationType.TYPE_FORMAT, "StrikeSpread", path, "");
			})
			.collect(toList());
	}

}
