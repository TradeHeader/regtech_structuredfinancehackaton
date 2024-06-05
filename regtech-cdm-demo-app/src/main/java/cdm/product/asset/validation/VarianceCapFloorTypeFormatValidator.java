package cdm.product.asset.validation;

import cdm.product.asset.VarianceCapFloor;
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

public class VarianceCapFloorTypeFormatValidator implements Validator<VarianceCapFloor> {

	private List<ComparisonResult> getComparisonResults(VarianceCapFloor o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<VarianceCapFloor> validate(RosettaPath path, VarianceCapFloor o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("VarianceCapFloor", ValidationType.TYPE_FORMAT, "VarianceCapFloor", path, "", error);
		}
		return success("VarianceCapFloor", ValidationType.TYPE_FORMAT, "VarianceCapFloor", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, VarianceCapFloor o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("VarianceCapFloor", ValidationType.TYPE_FORMAT, "VarianceCapFloor", path, "", res.getError());
				}
				return success("VarianceCapFloor", ValidationType.TYPE_FORMAT, "VarianceCapFloor", path, "");
			})
			.collect(toList());
	}

}
