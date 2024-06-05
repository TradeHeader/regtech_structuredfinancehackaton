package cdm.product.template.validation.exists;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.observable.asset.ValuationDates;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.product.common.schedule.ObservationTerms;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.FxFeature;
import cdm.product.template.PerformancePayout;
import cdm.product.template.PortfolioReturnTerms;
import cdm.product.template.Product;
import cdm.product.template.ReturnTerms;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class PerformancePayoutOnlyExistsValidator implements ValidatorWithArg<PerformancePayout, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PerformancePayout> ValidationResult<PerformancePayout> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("payerReceiver", ExistenceChecker.isSet((PayerReceiver) o.getPayerReceiver()))
				.put("priceQuantity", ExistenceChecker.isSet((ResolvablePriceQuantity) o.getPriceQuantity()))
				.put("principalPayment", ExistenceChecker.isSet((PrincipalPayments) o.getPrincipalPayment()))
				.put("settlementTerms", ExistenceChecker.isSet((SettlementTerms) o.getSettlementTerms()))
				.put("observationTerms", ExistenceChecker.isSet((ObservationTerms) o.getObservationTerms()))
				.put("valuationDates", ExistenceChecker.isSet((ValuationDates) o.getValuationDates()))
				.put("paymentDates", ExistenceChecker.isSet((PaymentDates) o.getPaymentDates()))
				.put("underlier", ExistenceChecker.isSet((Product) o.getUnderlier()))
				.put("fxFeature", ExistenceChecker.isSet((List<? extends FxFeature>) o.getFxFeature()))
				.put("returnTerms", ExistenceChecker.isSet((ReturnTerms) o.getReturnTerms()))
				.put("portfolioReturnTerms", ExistenceChecker.isSet((List<? extends PortfolioReturnTerms>) o.getPortfolioReturnTerms()))
				.put("initialValuationPrice", ExistenceChecker.isSet((ReferenceWithMetaPriceSchedule) o.getInitialValuationPrice()))
				.put("interimValuationPrice", ExistenceChecker.isSet((ReferenceWithMetaPriceSchedule) o.getInterimValuationPrice()))
				.put("finalValuationPrice", ExistenceChecker.isSet((ReferenceWithMetaPriceSchedule) o.getFinalValuationPrice()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PerformancePayout", ValidationType.ONLY_EXISTS, "PerformancePayout", path, "");
		}
		return failure("PerformancePayout", ValidationType.ONLY_EXISTS, "PerformancePayout", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
