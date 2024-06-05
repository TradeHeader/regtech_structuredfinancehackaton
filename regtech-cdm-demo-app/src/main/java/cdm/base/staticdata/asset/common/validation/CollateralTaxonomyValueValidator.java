package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.CollateralTaxonomyValue;
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

public class CollateralTaxonomyValueValidator implements Validator<CollateralTaxonomyValue> {

	private List<ComparisonResult> getComparisonResults(CollateralTaxonomyValue o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CollateralTaxonomyValue> validate(RosettaPath path, CollateralTaxonomyValue o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralTaxonomyValue", ValidationType.CARDINALITY, "CollateralTaxonomyValue", path, "", error);
		}
		return success("CollateralTaxonomyValue", ValidationType.CARDINALITY, "CollateralTaxonomyValue", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralTaxonomyValue o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralTaxonomyValue", ValidationType.CARDINALITY, "CollateralTaxonomyValue", path, "", res.getError());
				}
				return success("CollateralTaxonomyValue", ValidationType.CARDINALITY, "CollateralTaxonomyValue", path, "");
			})
			.collect(toList());
	}

}
