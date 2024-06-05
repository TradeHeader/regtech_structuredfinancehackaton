package cdm.product.common.settlement.validation;

import cdm.base.datetime.AdjustableDate;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.observable.asset.Money;
import cdm.product.common.settlement.PrincipalPayment;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class PrincipalPaymentValidator implements Validator<PrincipalPayment> {

	private List<ComparisonResult> getComparisonResults(PrincipalPayment o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("principalPaymentDate", (AdjustableDate) o.getPrincipalPaymentDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("payerReceiver", (PayerReceiver) o.getPayerReceiver() != null ? 1 : 0, 0, 1), 
				checkCardinality("principalAmount", (Money) o.getPrincipalAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("discountFactor", (BigDecimal) o.getDiscountFactor() != null ? 1 : 0, 0, 1), 
				checkCardinality("presentValuePrincipalAmount", (Money) o.getPresentValuePrincipalAmount() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PrincipalPayment> validate(RosettaPath path, PrincipalPayment o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PrincipalPayment", ValidationType.CARDINALITY, "PrincipalPayment", path, "", error);
		}
		return success("PrincipalPayment", ValidationType.CARDINALITY, "PrincipalPayment", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PrincipalPayment o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PrincipalPayment", ValidationType.CARDINALITY, "PrincipalPayment", path, "", res.getError());
				}
				return success("PrincipalPayment", ValidationType.CARDINALITY, "PrincipalPayment", path, "");
			})
			.collect(toList());
	}

}
