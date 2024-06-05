package cdm.product.template.validation;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.observable.asset.Money;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.AssetLeg;
import cdm.product.template.AssetPayout;
import cdm.product.template.DividendTerms;
import cdm.product.template.Duration;
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

public class AssetPayoutValidator implements Validator<AssetPayout> {

	private List<ComparisonResult> getComparisonResults(AssetPayout o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("payerReceiver", (PayerReceiver) o.getPayerReceiver() != null ? 1 : 0, 1, 1), 
				checkCardinality("priceQuantity", (ResolvablePriceQuantity) o.getPriceQuantity() != null ? 1 : 0, 0, 1), 
				checkCardinality("principalPayment", (PrincipalPayments) o.getPrincipalPayment() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementTerms", (SettlementTerms) o.getSettlementTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("assetLeg", (List<? extends AssetLeg>) o.getAssetLeg() == null ? 0 : ((List<? extends AssetLeg>) o.getAssetLeg()).size(), 1, 0), 
				checkCardinality("securityInformation", (Product) o.getSecurityInformation() != null ? 1 : 0, 1, 1), 
				checkCardinality("durationType", (Duration) o.getDurationType() != null ? 1 : 0, 1, 1), 
				checkCardinality("minimumFee", (Money) o.getMinimumFee() != null ? 1 : 0, 0, 1), 
				checkCardinality("dividendTerms", (DividendTerms) o.getDividendTerms() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AssetPayout> validate(RosettaPath path, AssetPayout o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AssetPayout", ValidationType.CARDINALITY, "AssetPayout", path, "", error);
		}
		return success("AssetPayout", ValidationType.CARDINALITY, "AssetPayout", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AssetPayout o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AssetPayout", ValidationType.CARDINALITY, "AssetPayout", path, "", res.getError());
				}
				return success("AssetPayout", ValidationType.CARDINALITY, "AssetPayout", path, "");
			})
			.collect(toList());
	}

}
