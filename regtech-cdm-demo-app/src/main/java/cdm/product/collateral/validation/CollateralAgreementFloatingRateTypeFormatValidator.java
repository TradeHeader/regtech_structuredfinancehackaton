package cdm.product.collateral.validation;

import cdm.product.collateral.CollateralAgreementFloatingRate;
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

public class CollateralAgreementFloatingRateTypeFormatValidator implements Validator<CollateralAgreementFloatingRate> {

	private List<ComparisonResult> getComparisonResults(CollateralAgreementFloatingRate o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CollateralAgreementFloatingRate> validate(RosettaPath path, CollateralAgreementFloatingRate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralAgreementFloatingRate", ValidationType.TYPE_FORMAT, "CollateralAgreementFloatingRate", path, "", error);
		}
		return success("CollateralAgreementFloatingRate", ValidationType.TYPE_FORMAT, "CollateralAgreementFloatingRate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralAgreementFloatingRate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralAgreementFloatingRate", ValidationType.TYPE_FORMAT, "CollateralAgreementFloatingRate", path, "", res.getError());
				}
				return success("CollateralAgreementFloatingRate", ValidationType.TYPE_FORMAT, "CollateralAgreementFloatingRate", path, "");
			})
			.collect(toList());
	}

}
