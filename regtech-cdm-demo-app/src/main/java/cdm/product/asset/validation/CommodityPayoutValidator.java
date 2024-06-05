package cdm.product.asset.validation;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.product.asset.AssetDeliveryInformation;
import cdm.product.asset.CommodityPayout;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.common.settlement.CommodityPriceReturnTerms;
import cdm.product.common.settlement.PricingDates;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.AveragingCalculation;
import cdm.product.template.CalculationSchedule;
import cdm.product.template.FxFeature;
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

public class CommodityPayoutValidator implements Validator<CommodityPayout> {

	private List<ComparisonResult> getComparisonResults(CommodityPayout o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("payerReceiver", (PayerReceiver) o.getPayerReceiver() != null ? 1 : 0, 1, 1), 
				checkCardinality("priceQuantity", (ResolvablePriceQuantity) o.getPriceQuantity() != null ? 1 : 0, 0, 1), 
				checkCardinality("principalPayment", (PrincipalPayments) o.getPrincipalPayment() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementTerms", (SettlementTerms) o.getSettlementTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("averagingFeature", (AveragingCalculation) o.getAveragingFeature() != null ? 1 : 0, 0, 1), 
				checkCardinality("commodityPriceReturnTerms", (CommodityPriceReturnTerms) o.getCommodityPriceReturnTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("pricingDates", (PricingDates) o.getPricingDates() != null ? 1 : 0, 1, 1), 
				checkCardinality("schedule", (CalculationSchedule) o.getSchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationPeriodDates", (CalculationPeriodDates) o.getCalculationPeriodDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("paymentDates", (PaymentDates) o.getPaymentDates() != null ? 1 : 0, 1, 1), 
				checkCardinality("underlier", (Product) o.getUnderlier() != null ? 1 : 0, 1, 1), 
				checkCardinality("fxFeature", (FxFeature) o.getFxFeature() != null ? 1 : 0, 0, 1), 
				checkCardinality("delivery", (AssetDeliveryInformation) o.getDelivery() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CommodityPayout> validate(RosettaPath path, CommodityPayout o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CommodityPayout", ValidationType.CARDINALITY, "CommodityPayout", path, "", error);
		}
		return success("CommodityPayout", ValidationType.CARDINALITY, "CommodityPayout", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CommodityPayout o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CommodityPayout", ValidationType.CARDINALITY, "CommodityPayout", path, "", res.getError());
				}
				return success("CommodityPayout", ValidationType.CARDINALITY, "CommodityPayout", path, "");
			})
			.collect(toList());
	}

}
