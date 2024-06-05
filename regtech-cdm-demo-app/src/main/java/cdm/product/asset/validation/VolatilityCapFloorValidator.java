package cdm.product.asset.validation;

import cdm.product.asset.VolatilityCapFloor;
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

public class VolatilityCapFloorValidator implements Validator<VolatilityCapFloor> {

	private List<ComparisonResult> getComparisonResults(VolatilityCapFloor o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("applicable", (Boolean) o.getApplicable() != null ? 1 : 0, 1, 1), 
				checkCardinality("totalVolatilityCap", (BigDecimal) o.getTotalVolatilityCap() != null ? 1 : 0, 0, 1), 
				checkCardinality("volatilityCapFactor", (BigDecimal) o.getVolatilityCapFactor() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<VolatilityCapFloor> validate(RosettaPath path, VolatilityCapFloor o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("VolatilityCapFloor", ValidationType.CARDINALITY, "VolatilityCapFloor", path, "", error);
		}
		return success("VolatilityCapFloor", ValidationType.CARDINALITY, "VolatilityCapFloor", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, VolatilityCapFloor o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("VolatilityCapFloor", ValidationType.CARDINALITY, "VolatilityCapFloor", path, "", res.getError());
				}
				return success("VolatilityCapFloor", ValidationType.CARDINALITY, "VolatilityCapFloor", path, "");
			})
			.collect(toList());
	}

}
