package cdm.product.collateral.validation;

import cdm.product.collateral.CollateralTreatment;
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

public class CollateralTreatmentTypeFormatValidator implements Validator<CollateralTreatment> {

	private List<ComparisonResult> getComparisonResults(CollateralTreatment o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CollateralTreatment> validate(RosettaPath path, CollateralTreatment o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralTreatment", ValidationType.TYPE_FORMAT, "CollateralTreatment", path, "", error);
		}
		return success("CollateralTreatment", ValidationType.TYPE_FORMAT, "CollateralTreatment", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralTreatment o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralTreatment", ValidationType.TYPE_FORMAT, "CollateralTreatment", path, "", res.getError());
				}
				return success("CollateralTreatment", ValidationType.TYPE_FORMAT, "CollateralTreatment", path, "");
			})
			.collect(toList());
	}

}
