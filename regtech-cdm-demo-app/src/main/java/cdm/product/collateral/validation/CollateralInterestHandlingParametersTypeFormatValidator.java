package cdm.product.collateral.validation;

import cdm.product.collateral.CollateralInterestHandlingParameters;
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

public class CollateralInterestHandlingParametersTypeFormatValidator implements Validator<CollateralInterestHandlingParameters> {

	private List<ComparisonResult> getComparisonResults(CollateralInterestHandlingParameters o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CollateralInterestHandlingParameters> validate(RosettaPath path, CollateralInterestHandlingParameters o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralInterestHandlingParameters", ValidationType.TYPE_FORMAT, "CollateralInterestHandlingParameters", path, "", error);
		}
		return success("CollateralInterestHandlingParameters", ValidationType.TYPE_FORMAT, "CollateralInterestHandlingParameters", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralInterestHandlingParameters o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralInterestHandlingParameters", ValidationType.TYPE_FORMAT, "CollateralInterestHandlingParameters", path, "", res.getError());
				}
				return success("CollateralInterestHandlingParameters", ValidationType.TYPE_FORMAT, "CollateralInterestHandlingParameters", path, "");
			})
			.collect(toList());
	}

}
