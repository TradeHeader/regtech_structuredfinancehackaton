package cdm.product.asset.validation;

import cdm.product.asset.FloatingRateDefinition;
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

public class FloatingRateDefinitionValidator implements Validator<FloatingRateDefinition> {

	private List<ComparisonResult> getComparisonResults(FloatingRateDefinition o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("calculatedRate", (BigDecimal) o.getCalculatedRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("floatingRateMultiplier", (BigDecimal) o.getFloatingRateMultiplier() != null ? 1 : 0, 0, 1), 
				checkCardinality("spread", (BigDecimal) o.getSpread() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<FloatingRateDefinition> validate(RosettaPath path, FloatingRateDefinition o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingRateDefinition", ValidationType.CARDINALITY, "FloatingRateDefinition", path, "", error);
		}
		return success("FloatingRateDefinition", ValidationType.CARDINALITY, "FloatingRateDefinition", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingRateDefinition o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingRateDefinition", ValidationType.CARDINALITY, "FloatingRateDefinition", path, "", res.getError());
				}
				return success("FloatingRateDefinition", ValidationType.CARDINALITY, "FloatingRateDefinition", path, "");
			})
			.collect(toList());
	}

}
