package cdm.product.collateral.validation;

import cdm.product.collateral.CollateralValuationTreatment;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CollateralValuationTreatmentValidator implements Validator<CollateralValuationTreatment> {

	private List<ComparisonResult> getComparisonResults(CollateralValuationTreatment o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("haircutPercentage", (BigDecimal) o.getHaircutPercentage() != null ? 1 : 0, 0, 1), 
				checkCardinality("marginPercentage", (BigDecimal) o.getMarginPercentage() != null ? 1 : 0, 0, 1), 
				checkCardinality("fxHaircutPercentage", (BigDecimal) o.getFxHaircutPercentage() != null ? 1 : 0, 0, 1), 
				checkCardinality("additionalHaircutPercentage", (BigDecimal) o.getAdditionalHaircutPercentage() != null ? 1 : 0, 0, 1)
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
			return failure("CollateralValuationTreatment", ValidationType.CARDINALITY, "CollateralValuationTreatment", path, "", error);
		}
		return success("CollateralValuationTreatment", ValidationType.CARDINALITY, "CollateralValuationTreatment", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralValuationTreatment o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralValuationTreatment", ValidationType.CARDINALITY, "CollateralValuationTreatment", path, "", res.getError());
				}
				return success("CollateralValuationTreatment", ValidationType.CARDINALITY, "CollateralValuationTreatment", path, "");
			})
			.collect(toList());
	}

}
