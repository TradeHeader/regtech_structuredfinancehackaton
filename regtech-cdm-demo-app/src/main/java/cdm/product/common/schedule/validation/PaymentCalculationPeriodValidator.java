package cdm.product.common.schedule.validation;

import cdm.observable.asset.Money;
import cdm.product.common.schedule.CalculationPeriod;
import cdm.product.common.schedule.PaymentCalculationPeriod;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class PaymentCalculationPeriodValidator implements Validator<PaymentCalculationPeriod> {

	private List<ComparisonResult> getComparisonResults(PaymentCalculationPeriod o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("unadjustedPaymentDate", (Date) o.getUnadjustedPaymentDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("adjustedPaymentDate", (Date) o.getAdjustedPaymentDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationPeriod", (List<? extends CalculationPeriod>) o.getCalculationPeriod() == null ? 0 : ((List<? extends CalculationPeriod>) o.getCalculationPeriod()).size(), 1, 0), 
				checkCardinality("fixedPaymentAmount", (Money) o.getFixedPaymentAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("discountFactor", (BigDecimal) o.getDiscountFactor() != null ? 1 : 0, 0, 1), 
				checkCardinality("forecastPaymentAmount", (Money) o.getForecastPaymentAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("presentValueAmount", (Money) o.getPresentValueAmount() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PaymentCalculationPeriod> validate(RosettaPath path, PaymentCalculationPeriod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PaymentCalculationPeriod", ValidationType.CARDINALITY, "PaymentCalculationPeriod", path, "", error);
		}
		return success("PaymentCalculationPeriod", ValidationType.CARDINALITY, "PaymentCalculationPeriod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PaymentCalculationPeriod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PaymentCalculationPeriod", ValidationType.CARDINALITY, "PaymentCalculationPeriod", path, "", res.getError());
				}
				return success("PaymentCalculationPeriod", ValidationType.CARDINALITY, "PaymentCalculationPeriod", path, "");
			})
			.collect(toList());
	}

}
