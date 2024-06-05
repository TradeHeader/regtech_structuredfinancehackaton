package cdm.product.template.validation;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.observable.asset.ValuationDates;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.product.common.schedule.ObservationTerms;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.PerformancePayout;
import cdm.product.template.Product;
import cdm.product.template.ReturnTerms;
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

public class PerformancePayoutValidator implements Validator<PerformancePayout> {

	private List<ComparisonResult> getComparisonResults(PerformancePayout o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("payerReceiver", (PayerReceiver) o.getPayerReceiver() != null ? 1 : 0, 1, 1), 
				checkCardinality("priceQuantity", (ResolvablePriceQuantity) o.getPriceQuantity() != null ? 1 : 0, 0, 1), 
				checkCardinality("principalPayment", (PrincipalPayments) o.getPrincipalPayment() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementTerms", (SettlementTerms) o.getSettlementTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("observationTerms", (ObservationTerms) o.getObservationTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("valuationDates", (ValuationDates) o.getValuationDates() != null ? 1 : 0, 1, 1), 
				checkCardinality("paymentDates", (PaymentDates) o.getPaymentDates() != null ? 1 : 0, 1, 1), 
				checkCardinality("underlier", (Product) o.getUnderlier() != null ? 1 : 0, 0, 1), 
				checkCardinality("returnTerms", (ReturnTerms) o.getReturnTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("initialValuationPrice", (ReferenceWithMetaPriceSchedule) o.getInitialValuationPrice() != null ? 1 : 0, 0, 1), 
				checkCardinality("interimValuationPrice", (ReferenceWithMetaPriceSchedule) o.getInterimValuationPrice() != null ? 1 : 0, 0, 1), 
				checkCardinality("finalValuationPrice", (ReferenceWithMetaPriceSchedule) o.getFinalValuationPrice() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PerformancePayout> validate(RosettaPath path, PerformancePayout o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PerformancePayout", ValidationType.CARDINALITY, "PerformancePayout", path, "", error);
		}
		return success("PerformancePayout", ValidationType.CARDINALITY, "PerformancePayout", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PerformancePayout o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PerformancePayout", ValidationType.CARDINALITY, "PerformancePayout", path, "", res.getError());
				}
				return success("PerformancePayout", ValidationType.CARDINALITY, "PerformancePayout", path, "");
			})
			.collect(toList());
	}

}
