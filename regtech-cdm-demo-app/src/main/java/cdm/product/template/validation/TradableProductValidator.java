package cdm.product.template.validation;

import cdm.base.staticdata.party.Counterparty;
import cdm.product.common.NotionalAdjustmentEnum;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
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

public class TradableProductValidator implements Validator<TradableProduct> {

	private List<ComparisonResult> getComparisonResults(TradableProduct o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("product", (Product) o.getProduct() != null ? 1 : 0, 1, 1), 
				checkCardinality("tradeLot", (List<? extends TradeLot>) o.getTradeLot() == null ? 0 : ((List<? extends TradeLot>) o.getTradeLot()).size(), 1, 0), 
				checkCardinality("counterparty", (List<? extends Counterparty>) o.getCounterparty() == null ? 0 : ((List<? extends Counterparty>) o.getCounterparty()).size(), 2, 2), 
				checkCardinality("adjustment", (NotionalAdjustmentEnum) o.getAdjustment() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<TradableProduct> validate(RosettaPath path, TradableProduct o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TradableProduct", ValidationType.CARDINALITY, "TradableProduct", path, "", error);
		}
		return success("TradableProduct", ValidationType.CARDINALITY, "TradableProduct", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TradableProduct o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TradableProduct", ValidationType.CARDINALITY, "TradableProduct", path, "", res.getError());
				}
				return success("TradableProduct", ValidationType.CARDINALITY, "TradableProduct", path, "");
			})
			.collect(toList());
	}

}
