package cdm.product.collateral.validation;

import cdm.product.collateral.CollateralProvisions;
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

public class CollateralProvisionsTypeFormatValidator implements Validator<CollateralProvisions> {

	private List<ComparisonResult> getComparisonResults(CollateralProvisions o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CollateralProvisions> validate(RosettaPath path, CollateralProvisions o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralProvisions", ValidationType.TYPE_FORMAT, "CollateralProvisions", path, "", error);
		}
		return success("CollateralProvisions", ValidationType.TYPE_FORMAT, "CollateralProvisions", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralProvisions o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralProvisions", ValidationType.TYPE_FORMAT, "CollateralProvisions", path, "", res.getError());
				}
				return success("CollateralProvisions", ValidationType.TYPE_FORMAT, "CollateralProvisions", path, "");
			})
			.collect(toList());
	}

}
