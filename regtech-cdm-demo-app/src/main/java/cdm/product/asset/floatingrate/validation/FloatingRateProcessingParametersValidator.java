package cdm.product.asset.floatingrate.validation;

import cdm.base.math.Rounding;
import cdm.observable.asset.Price;
import cdm.product.asset.NegativeInterestRateTreatmentEnum;
import cdm.product.asset.RateTreatmentEnum;
import cdm.product.asset.floatingrate.FloatingRateProcessingParameters;
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

public class FloatingRateProcessingParametersValidator implements Validator<FloatingRateProcessingParameters> {

	private List<ComparisonResult> getComparisonResults(FloatingRateProcessingParameters o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("initialRate", (Price) o.getInitialRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("multiplier", (BigDecimal) o.getMultiplier() != null ? 1 : 0, 0, 1), 
				checkCardinality("spread", (BigDecimal) o.getSpread() != null ? 1 : 0, 0, 1), 
				checkCardinality("treatment", (RateTreatmentEnum) o.getTreatment() != null ? 1 : 0, 0, 1), 
				checkCardinality("capRate", (BigDecimal) o.getCapRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("floorRate", (BigDecimal) o.getFloorRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("rounding", (Rounding) o.getRounding() != null ? 1 : 0, 0, 1), 
				checkCardinality("negativeTreatment", (NegativeInterestRateTreatmentEnum) o.getNegativeTreatment() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<FloatingRateProcessingParameters> validate(RosettaPath path, FloatingRateProcessingParameters o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingRateProcessingParameters", ValidationType.CARDINALITY, "FloatingRateProcessingParameters", path, "", error);
		}
		return success("FloatingRateProcessingParameters", ValidationType.CARDINALITY, "FloatingRateProcessingParameters", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingRateProcessingParameters o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingRateProcessingParameters", ValidationType.CARDINALITY, "FloatingRateProcessingParameters", path, "", res.getError());
				}
				return success("FloatingRateProcessingParameters", ValidationType.CARDINALITY, "FloatingRateProcessingParameters", path, "");
			})
			.collect(toList());
	}

}
