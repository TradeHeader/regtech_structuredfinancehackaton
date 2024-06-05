package cdm.event.common.validation;

import cdm.event.common.CollateralPosition;
import cdm.event.common.CollateralStatusEnum;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.observable.asset.Money;
import cdm.product.collateral.CollateralTreatment;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.Product;
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

public class CollateralPositionValidator implements Validator<CollateralPosition> {

	private List<ComparisonResult> getComparisonResults(CollateralPosition o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("priceQuantity", (List<? extends PriceQuantity>) o.getPriceQuantity() == null ? 0 : ((List<? extends PriceQuantity>) o.getPriceQuantity()).size(), 1, 0), 
				checkCardinality("product", (Product) o.getProduct() != null ? 1 : 0, 1, 1), 
				checkCardinality("cashBalance", (Money) o.getCashBalance() != null ? 1 : 0, 0, 1), 
				checkCardinality("tradeReference", (ReferenceWithMetaTradeState) o.getTradeReference() != null ? 1 : 0, 0, 1), 
				checkCardinality("treatment", (CollateralTreatment) o.getTreatment() != null ? 1 : 0, 0, 1), 
				checkCardinality("collateralPositionStatus", (CollateralStatusEnum) o.getCollateralPositionStatus() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CollateralPosition> validate(RosettaPath path, CollateralPosition o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CollateralPosition", ValidationType.CARDINALITY, "CollateralPosition", path, "", error);
		}
		return success("CollateralPosition", ValidationType.CARDINALITY, "CollateralPosition", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CollateralPosition o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CollateralPosition", ValidationType.CARDINALITY, "CollateralPosition", path, "", res.getError());
				}
				return success("CollateralPosition", ValidationType.CARDINALITY, "CollateralPosition", path, "");
			})
			.collect(toList());
	}

}
