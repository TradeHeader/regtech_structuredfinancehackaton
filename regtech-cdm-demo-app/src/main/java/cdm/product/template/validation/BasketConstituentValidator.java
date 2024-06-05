package cdm.product.template.validation;

import cdm.base.staticdata.asset.common.AssetPool;
import cdm.base.staticdata.asset.common.Index;
import cdm.base.staticdata.asset.common.Loan;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaCommodity;
import cdm.product.asset.ForeignExchange;
import cdm.product.template.Basket;
import cdm.product.template.BasketConstituent;
import cdm.product.template.ContractualProduct;
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

public class BasketConstituentValidator implements Validator<BasketConstituent> {

	private List<ComparisonResult> getComparisonResults(BasketConstituent o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("contractualProduct", (ContractualProduct) o.getContractualProduct() != null ? 1 : 0, 0, 1), 
				checkCardinality("index", (Index) o.getIndex() != null ? 1 : 0, 0, 1), 
				checkCardinality("loan", (Loan) o.getLoan() != null ? 1 : 0, 0, 1), 
				checkCardinality("assetPool", (AssetPool) o.getAssetPool() != null ? 1 : 0, 0, 1), 
				checkCardinality("foreignExchange", (ForeignExchange) o.getForeignExchange() != null ? 1 : 0, 0, 1), 
				checkCardinality("commodity", (ReferenceWithMetaCommodity) o.getCommodity() != null ? 1 : 0, 0, 1), 
				checkCardinality("security", (Security) o.getSecurity() != null ? 1 : 0, 0, 1), 
				checkCardinality("basket", (Basket) o.getBasket() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<BasketConstituent> validate(RosettaPath path, BasketConstituent o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("BasketConstituent", ValidationType.CARDINALITY, "BasketConstituent", path, "", error);
		}
		return success("BasketConstituent", ValidationType.CARDINALITY, "BasketConstituent", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, BasketConstituent o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("BasketConstituent", ValidationType.CARDINALITY, "BasketConstituent", path, "", res.getError());
				}
				return success("BasketConstituent", ValidationType.CARDINALITY, "BasketConstituent", path, "");
			})
			.collect(toList());
	}

}
