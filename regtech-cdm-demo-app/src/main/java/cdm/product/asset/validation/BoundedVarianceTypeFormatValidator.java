package cdm.product.asset.validation;

import cdm.product.asset.BoundedVariance;
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

public class BoundedVarianceTypeFormatValidator implements Validator<BoundedVariance> {

	private List<ComparisonResult> getComparisonResults(BoundedVariance o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<BoundedVariance> validate(RosettaPath path, BoundedVariance o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BoundedVariance", ValidationType.TYPE_FORMAT, "BoundedVariance", path, "", error);
		}
		return success("BoundedVariance", ValidationType.TYPE_FORMAT, "BoundedVariance", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BoundedVariance o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BoundedVariance", ValidationType.TYPE_FORMAT, "BoundedVariance", path, "", res.getError());
				}
				return success("BoundedVariance", ValidationType.TYPE_FORMAT, "BoundedVariance", path, "");
			})
			.collect(toList());
	}

}
