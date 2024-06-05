package cdm.product.template.validation;

import cdm.base.staticdata.party.BuyerSeller;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.product.asset.AssetDeliveryInformation;
import cdm.product.common.schedule.ObservationTerms;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.template.CalculationSchedule;
import cdm.product.template.OptionExercise;
import cdm.product.template.OptionFeature;
import cdm.product.template.OptionPayout;
import cdm.product.template.OptionTypeEnum;
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

public class OptionPayoutValidator implements Validator<OptionPayout> {

	private List<ComparisonResult> getComparisonResults(OptionPayout o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("payerReceiver", (PayerReceiver) o.getPayerReceiver() != null ? 1 : 0, 1, 1), 
				checkCardinality("priceQuantity", (ResolvablePriceQuantity) o.getPriceQuantity() != null ? 1 : 0, 0, 1), 
				checkCardinality("principalPayment", (PrincipalPayments) o.getPrincipalPayment() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementTerms", (SettlementTerms) o.getSettlementTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("buyerSeller", (BuyerSeller) o.getBuyerSeller() != null ? 1 : 0, 1, 1), 
				checkCardinality("optionType", (OptionTypeEnum) o.getOptionType() != null ? 1 : 0, 0, 1), 
				checkCardinality("feature", (OptionFeature) o.getFeature() != null ? 1 : 0, 0, 1), 
				checkCardinality("exerciseTerms", (OptionExercise) o.getExerciseTerms() != null ? 1 : 0, 1, 1), 
				checkCardinality("underlier", (Product) o.getUnderlier() != null ? 1 : 0, 1, 1), 
				checkCardinality("observationTerms", (ObservationTerms) o.getObservationTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("schedule", (CalculationSchedule) o.getSchedule() != null ? 1 : 0, 0, 1), 
				checkCardinality("delivery", (AssetDeliveryInformation) o.getDelivery() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<OptionPayout> validate(RosettaPath path, OptionPayout o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OptionPayout", ValidationType.CARDINALITY, "OptionPayout", path, "", error);
		}
		return success("OptionPayout", ValidationType.CARDINALITY, "OptionPayout", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OptionPayout o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OptionPayout", ValidationType.CARDINALITY, "OptionPayout", path, "", res.getError());
				}
				return success("OptionPayout", ValidationType.CARDINALITY, "OptionPayout", path, "");
			})
			.collect(toList());
	}

}
