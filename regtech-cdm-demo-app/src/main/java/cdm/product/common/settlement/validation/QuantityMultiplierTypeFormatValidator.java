package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.QuantityMultiplier;
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

public class QuantityMultiplierTypeFormatValidator implements Validator<QuantityMultiplier> {

	private List<ComparisonResult> getComparisonResults(QuantityMultiplier o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<QuantityMultiplier> validate(RosettaPath path, QuantityMultiplier o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("QuantityMultiplier", ValidationType.TYPE_FORMAT, "QuantityMultiplier", path, "", error);
		}
		return success("QuantityMultiplier", ValidationType.TYPE_FORMAT, "QuantityMultiplier", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, QuantityMultiplier o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("QuantityMultiplier", ValidationType.TYPE_FORMAT, "QuantityMultiplier", path, "", res.getError());
				}
				return success("QuantityMultiplier", ValidationType.TYPE_FORMAT, "QuantityMultiplier", path, "");
			})
			.collect(toList());
	}

}
