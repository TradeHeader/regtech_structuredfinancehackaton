package cdm.product.collateral.validation;

import cdm.product.collateral.Collateral;
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

public class CollateralTypeFormatValidator implements Validator<Collateral> {

	private List<ComparisonResult> getComparisonResults(Collateral o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Collateral> validate(RosettaPath path, Collateral o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Collateral", ValidationType.TYPE_FORMAT, "Collateral", path, "", error);
		}
		return success("Collateral", ValidationType.TYPE_FORMAT, "Collateral", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Collateral o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Collateral", ValidationType.TYPE_FORMAT, "Collateral", path, "", res.getError());
				}
				return success("Collateral", ValidationType.TYPE_FORMAT, "Collateral", path, "");
			})
			.collect(toList());
	}

}
