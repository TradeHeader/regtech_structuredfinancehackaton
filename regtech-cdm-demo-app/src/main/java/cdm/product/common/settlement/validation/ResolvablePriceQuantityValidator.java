package cdm.product.common.settlement.validation;

import cdm.base.math.Quantity;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.product.asset.FutureValueAmount;
import cdm.product.common.settlement.QuantityMultiplier;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.metafields.ReferenceWithMetaResolvablePriceQuantity;
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

public class ResolvablePriceQuantityValidator implements Validator<ResolvablePriceQuantity> {

	private List<ComparisonResult> getComparisonResults(ResolvablePriceQuantity o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("resolvedQuantity", (Quantity) o.getResolvedQuantity() != null ? 1 : 0, 0, 1), 
				checkCardinality("quantitySchedule", (ReferenceWithMetaNonNegativeQuantitySchedule) o.getQuantitySchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("quantityReference", (ReferenceWithMetaResolvablePriceQuantity) o.getQuantityReference() != null ? 1 : 0, 0, 1), 
				checkCardinality("quantityMultiplier", (QuantityMultiplier) o.getQuantityMultiplier() != null ? 1 : 0, 0, 1), 
				checkCardinality("reset", (Boolean) o.getReset() != null ? 1 : 0, 0, 1), 
				checkCardinality("futureValueNotional", (FutureValueAmount) o.getFutureValueNotional() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ResolvablePriceQuantity> validate(RosettaPath path, ResolvablePriceQuantity o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ResolvablePriceQuantity", ValidationType.CARDINALITY, "ResolvablePriceQuantity", path, "", error);
		}
		return success("ResolvablePriceQuantity", ValidationType.CARDINALITY, "ResolvablePriceQuantity", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ResolvablePriceQuantity o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ResolvablePriceQuantity", ValidationType.CARDINALITY, "ResolvablePriceQuantity", path, "", res.getError());
				}
				return success("ResolvablePriceQuantity", ValidationType.CARDINALITY, "ResolvablePriceQuantity", path, "");
			})
			.collect(toList());
	}

}
