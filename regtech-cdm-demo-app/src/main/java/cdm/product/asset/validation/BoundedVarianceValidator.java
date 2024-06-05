package cdm.product.asset.validation;

import cdm.product.asset.BoundedVariance;
import cdm.product.asset.RealisedVarianceMethodEnum;
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

public class BoundedVarianceValidator implements Validator<BoundedVariance> {

	private List<ComparisonResult> getComparisonResults(BoundedVariance o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("realisedVarianceMethod", (RealisedVarianceMethodEnum) o.getRealisedVarianceMethod() != null ? 1 : 0, 1, 1), 
				checkCardinality("daysInRangeAdjustment", (Boolean) o.getDaysInRangeAdjustment() != null ? 1 : 0, 1, 1), 
				checkCardinality("upperBarrier", (BigDecimal) o.getUpperBarrier() != null ? 1 : 0, 0, 1), 
				checkCardinality("lowerBarrier", (BigDecimal) o.getLowerBarrier() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<BoundedVariance> validate(RosettaPath path, BoundedVariance o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BoundedVariance", ValidationType.CARDINALITY, "BoundedVariance", path, "", error);
		}
		return success("BoundedVariance", ValidationType.CARDINALITY, "BoundedVariance", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BoundedVariance o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BoundedVariance", ValidationType.CARDINALITY, "BoundedVariance", path, "", res.getError());
				}
				return success("BoundedVariance", ValidationType.CARDINALITY, "BoundedVariance", path, "");
			})
			.collect(toList());
	}

}
