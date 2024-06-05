package cdm.product.asset.validation.exists;

import cdm.base.datetime.AdjustableDate;
import cdm.base.datetime.daycount.metafields.FieldWithMetaDayCountFractionEnum;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.product.asset.BondReference;
import cdm.product.asset.CashflowRepresentation;
import cdm.product.asset.CompoundingMethodEnum;
import cdm.product.asset.DiscountingMethod;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.RateSpecification;
import cdm.product.asset.SpreadCalculationMethodEnum;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.common.schedule.ResetDates;
import cdm.product.common.schedule.StubPeriod;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
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

public class InterestRatePayoutOnlyExistsValidator implements ValidatorWithArg<InterestRatePayout, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends InterestRatePayout> ValidationResult<InterestRatePayout> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("payerReceiver", ExistenceChecker.isSet((PayerReceiver) o.getPayerReceiver()))
				.put("priceQuantity", ExistenceChecker.isSet((ResolvablePriceQuantity) o.getPriceQuantity()))
				.put("principalPayment", ExistenceChecker.isSet((PrincipalPayments) o.getPrincipalPayment()))
				.put("settlementTerms", ExistenceChecker.isSet((SettlementTerms) o.getSettlementTerms()))
				.put("rateSpecification", ExistenceChecker.isSet((RateSpecification) o.getRateSpecification()))
				.put("dayCountFraction", ExistenceChecker.isSet((FieldWithMetaDayCountFractionEnum) o.getDayCountFraction()))
				.put("calculationPeriodDates", ExistenceChecker.isSet((CalculationPeriodDates) o.getCalculationPeriodDates()))
				.put("paymentDates", ExistenceChecker.isSet((PaymentDates) o.getPaymentDates()))
				.put("paymentDate", ExistenceChecker.isSet((AdjustableDate) o.getPaymentDate()))
				.put("paymentDelay", ExistenceChecker.isSet((Boolean) o.getPaymentDelay()))
				.put("resetDates", ExistenceChecker.isSet((ResetDates) o.getResetDates()))
				.put("discountingMethod", ExistenceChecker.isSet((DiscountingMethod) o.getDiscountingMethod()))
				.put("compoundingMethod", ExistenceChecker.isSet((CompoundingMethodEnum) o.getCompoundingMethod()))
				.put("cashflowRepresentation", ExistenceChecker.isSet((CashflowRepresentation) o.getCashflowRepresentation()))
				.put("stubPeriod", ExistenceChecker.isSet((StubPeriod) o.getStubPeriod()))
				.put("bondReference", ExistenceChecker.isSet((BondReference) o.getBondReference()))
				.put("fixedAmount", ExistenceChecker.isSet((String) o.getFixedAmount()))
				.put("floatingAmount", ExistenceChecker.isSet((String) o.getFloatingAmount()))
				.put("spreadCalculationMethod", ExistenceChecker.isSet((SpreadCalculationMethodEnum) o.getSpreadCalculationMethod()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("InterestRatePayout", ValidationType.ONLY_EXISTS, "InterestRatePayout", path, "");
		}
		return failure("InterestRatePayout", ValidationType.ONLY_EXISTS, "InterestRatePayout", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
