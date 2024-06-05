package cdm.product.collateral.validation;

import cdm.product.collateral.CollateralCriteriaBase;
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

public class CollateralCriteriaBaseTypeFormatValidator implements Validator<CollateralCriteriaBase> {

	private List<ComparisonResult> getComparisonResults(CollateralCriteriaBase o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CollateralCriteriaBase> validate(RosettaPath path, CollateralCriteriaBase o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralCriteriaBase", ValidationType.TYPE_FORMAT, "CollateralCriteriaBase", path, "", error);
		}
		return success("CollateralCriteriaBase", ValidationType.TYPE_FORMAT, "CollateralCriteriaBase", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralCriteriaBase o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralCriteriaBase", ValidationType.TYPE_FORMAT, "CollateralCriteriaBase", path, "", res.getError());
				}
				return success("CollateralCriteriaBase", ValidationType.TYPE_FORMAT, "CollateralCriteriaBase", path, "");
			})
			.collect(toList());
	}

}
