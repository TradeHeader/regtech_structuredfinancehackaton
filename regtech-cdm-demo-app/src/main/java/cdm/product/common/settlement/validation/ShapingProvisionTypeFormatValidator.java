package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.ShapingProvision;
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

public class ShapingProvisionTypeFormatValidator implements Validator<ShapingProvision> {

	private List<ComparisonResult> getComparisonResults(ShapingProvision o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ShapingProvision> validate(RosettaPath path, ShapingProvision o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ShapingProvision", ValidationType.TYPE_FORMAT, "ShapingProvision", path, "", error);
		}
		return success("ShapingProvision", ValidationType.TYPE_FORMAT, "ShapingProvision", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ShapingProvision o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ShapingProvision", ValidationType.TYPE_FORMAT, "ShapingProvision", path, "", res.getError());
				}
				return success("ShapingProvision", ValidationType.TYPE_FORMAT, "ShapingProvision", path, "");
			})
			.collect(toList());
	}

}
