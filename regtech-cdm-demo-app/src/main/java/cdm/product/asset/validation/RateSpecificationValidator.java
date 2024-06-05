package cdm.product.asset.validation;

import cdm.product.asset.FixedRateSpecification;
import cdm.product.asset.FloatingRateSpecification;
import cdm.product.asset.InflationRateSpecification;
import cdm.product.asset.RateSpecification;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class RateSpecificationValidator implements Validator<RateSpecification> {

	private List<ComparisonResult> getComparisonResults(RateSpecification o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("fixedRate", (FixedRateSpecification) o.getFixedRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("floatingRate", (FloatingRateSpecification) o.getFloatingRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("inflationRate", (InflationRateSpecification) o.getInflationRate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<RateSpecification> validate(RosettaPath path, RateSpecification o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("RateSpecification", ValidationType.CARDINALITY, "RateSpecification", path, "", error);
		}
		return success("RateSpecification", ValidationType.CARDINALITY, "RateSpecification", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, RateSpecification o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("RateSpecification", ValidationType.CARDINALITY, "RateSpecification", path, "", res.getError());
				}
				return success("RateSpecification", ValidationType.CARDINALITY, "RateSpecification", path, "");
			})
			.collect(toList());
	}

}
