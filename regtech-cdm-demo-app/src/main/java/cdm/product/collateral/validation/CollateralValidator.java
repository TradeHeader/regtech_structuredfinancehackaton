package cdm.product.collateral.validation;

import cdm.product.collateral.Collateral;
import cdm.product.collateral.CollateralProvisions;
import cdm.product.collateral.IndependentAmount;
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

public class CollateralValidator implements Validator<Collateral> {

	private List<ComparisonResult> getComparisonResults(Collateral o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("independentAmount", (IndependentAmount) o.getIndependentAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("collateralProvisions", (CollateralProvisions) o.getCollateralProvisions() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Collateral> validate(RosettaPath path, Collateral o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Collateral", ValidationType.CARDINALITY, "Collateral", path, "", error);
		}
		return success("Collateral", ValidationType.CARDINALITY, "Collateral", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Collateral o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Collateral", ValidationType.CARDINALITY, "Collateral", path, "", res.getError());
				}
				return success("Collateral", ValidationType.CARDINALITY, "Collateral", path, "");
			})
			.collect(toList());
	}

}
