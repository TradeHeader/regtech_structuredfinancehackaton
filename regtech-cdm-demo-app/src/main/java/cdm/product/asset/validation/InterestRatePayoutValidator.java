package cdm.product.asset.validation;

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

public class InterestRatePayoutValidator implements Validator<InterestRatePayout> {

	private List<ComparisonResult> getComparisonResults(InterestRatePayout o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("payerReceiver", (PayerReceiver) o.getPayerReceiver() != null ? 1 : 0, 1, 1), 
				checkCardinality("priceQuantity", (ResolvablePriceQuantity) o.getPriceQuantity() != null ? 1 : 0, 0, 1), 
				checkCardinality("principalPayment", (PrincipalPayments) o.getPrincipalPayment() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementTerms", (SettlementTerms) o.getSettlementTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("rateSpecification", (RateSpecification) o.getRateSpecification() != null ? 1 : 0, 0, 1), 
				checkCardinality("dayCountFraction", (FieldWithMetaDayCountFractionEnum) o.getDayCountFraction() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationPeriodDates", (CalculationPeriodDates) o.getCalculationPeriodDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("paymentDates", (PaymentDates) o.getPaymentDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("paymentDate", (AdjustableDate) o.getPaymentDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("paymentDelay", (Boolean) o.getPaymentDelay() != null ? 1 : 0, 0, 1), 
				checkCardinality("resetDates", (ResetDates) o.getResetDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("discountingMethod", (DiscountingMethod) o.getDiscountingMethod() != null ? 1 : 0, 0, 1), 
				checkCardinality("compoundingMethod", (CompoundingMethodEnum) o.getCompoundingMethod() != null ? 1 : 0, 0, 1), 
				checkCardinality("cashflowRepresentation", (CashflowRepresentation) o.getCashflowRepresentation() != null ? 1 : 0, 0, 1), 
				checkCardinality("stubPeriod", (StubPeriod) o.getStubPeriod() != null ? 1 : 0, 0, 1), 
				checkCardinality("bondReference", (BondReference) o.getBondReference() != null ? 1 : 0, 0, 1), 
				checkCardinality("fixedAmount", (String) o.getFixedAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("floatingAmount", (String) o.getFloatingAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("spreadCalculationMethod", (SpreadCalculationMethodEnum) o.getSpreadCalculationMethod() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<InterestRatePayout> validate(RosettaPath path, InterestRatePayout o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("InterestRatePayout", ValidationType.CARDINALITY, "InterestRatePayout", path, "", error);
		}
		return success("InterestRatePayout", ValidationType.CARDINALITY, "InterestRatePayout", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, InterestRatePayout o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("InterestRatePayout", ValidationType.CARDINALITY, "InterestRatePayout", path, "", res.getError());
				}
				return success("InterestRatePayout", ValidationType.CARDINALITY, "InterestRatePayout", path, "");
			})
			.collect(toList());
	}

}
