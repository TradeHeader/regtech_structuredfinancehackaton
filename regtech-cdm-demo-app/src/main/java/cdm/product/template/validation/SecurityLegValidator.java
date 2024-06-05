package cdm.product.template.validation;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.staticdata.party.BuyerSeller;
import cdm.observable.asset.ExchangeRate;
import cdm.observable.asset.Money;
import cdm.product.common.settlement.DeliveryMethodEnum;
import cdm.product.template.SecurityLeg;
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

public class SecurityLegValidator implements Validator<SecurityLeg> {

	private List<ComparisonResult> getComparisonResults(SecurityLeg o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("buyerSeller", (BuyerSeller) o.getBuyerSeller() != null ? 1 : 0, 1, 1), 
				checkCardinality("settlementDate", (AdjustableOrRelativeDate) o.getSettlementDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("settlementAmount", (Money) o.getSettlementAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementCurrency", (String) o.getSettlementCurrency() != null ? 1 : 0, 0, 1), 
				checkCardinality("fxRate", (ExchangeRate) o.getFxRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("deliveryDate", (AdjustableOrRelativeDate) o.getDeliveryDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("deliveryMethod", (DeliveryMethodEnum) o.getDeliveryMethod() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<SecurityLeg> validate(RosettaPath path, SecurityLeg o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SecurityLeg", ValidationType.CARDINALITY, "SecurityLeg", path, "", error);
		}
		return success("SecurityLeg", ValidationType.CARDINALITY, "SecurityLeg", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SecurityLeg o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SecurityLeg", ValidationType.CARDINALITY, "SecurityLeg", path, "", res.getError());
				}
				return success("SecurityLeg", ValidationType.CARDINALITY, "SecurityLeg", path, "");
			})
			.collect(toList());
	}

}
