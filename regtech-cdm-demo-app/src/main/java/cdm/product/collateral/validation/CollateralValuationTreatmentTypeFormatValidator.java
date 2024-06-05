package cdm.product.collateral.validation;

import cdm.product.collateral.CollateralValuationTreatment;
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

public class CollateralValuationTreatmentTypeFormatValidator implements Validator<CollateralValuationTreatment> {

	private List<ComparisonResult> getComparisonResults(CollateralValuationTreatment o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CollateralValuationTreatment> validate(RosettaPath path, CollateralValuationTreatment o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralValuationTreatment", ValidationType.TYPE_FORMAT, "CollateralValuationTreatment", path, "", error);
		}
		return success("CollateralValuationTreatment", ValidationType.TYPE_FORMAT, "CollateralValuationTreatment", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralValuationTreatment o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralValuationTreatment", ValidationType.TYPE_FORMAT, "CollateralValuationTreatment", path, "", res.getError());
				}
				return success("CollateralValuationTreatment", ValidationType.TYPE_FORMAT, "CollateralValuationTreatment", path, "");
			})
			.collect(toList());
	}

}
