package cdm.product.asset.validation.exists;

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
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CommodityPayoutOnlyExistsValidator implements ValidatorWithArg<CommodityPayout, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CommodityPayout> ValidationResult<CommodityPayout> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("payerReceiver", ExistenceChecker.isSet((PayerReceiver) o.getPayerReceiver()))
				.put("priceQuantity", ExistenceChecker.isSet((ResolvablePriceQuantity) o.getPriceQuantity()))
				.put("principalPayment", ExistenceChecker.isSet((PrincipalPayments) o.getPrincipalPayment()))
				.put("settlementTerms", ExistenceChecker.isSet((SettlementTerms) o.getSettlementTerms()))
				.put("averagingFeature", ExistenceChecker.isSet((AveragingCalculation) o.getAveragingFeature()))
				.put("commodityPriceReturnTerms", ExistenceChecker.isSet((CommodityPriceReturnTerms) o.getCommodityPriceReturnTerms()))
				.put("pricingDates", ExistenceChecker.isSet((PricingDates) o.getPricingDates()))
				.put("schedule", ExistenceChecker.isSet((CalculationSchedule) o.getSchedule()))
				.put("calculationPeriodDates", ExistenceChecker.isSet((CalculationPeriodDates) o.getCalculationPeriodDates()))
				.put("paymentDates", ExistenceChecker.isSet((PaymentDates) o.getPaymentDates()))
				.put("underlier", ExistenceChecker.isSet((Product) o.getUnderlier()))
				.put("fxFeature", ExistenceChecker.isSet((FxFeature) o.getFxFeature()))
				.put("delivery", ExistenceChecker.isSet((AssetDeliveryInformation) o.getDelivery()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CommodityPayout", ValidationType.ONLY_EXISTS, "CommodityPayout", path, "");
		}
		return failure("CommodityPayout", ValidationType.ONLY_EXISTS, "CommodityPayout", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
