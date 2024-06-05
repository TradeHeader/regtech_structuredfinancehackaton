package cdm.product.asset.validation;

import cdm.observable.asset.Money;
import cdm.product.asset.FixedAmountCalculationDetails;
import cdm.product.common.schedule.CalculationPeriodBase;
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

public class FixedAmountCalculationDetailsValidator implements Validator<FixedAmountCalculationDetails> {

	private List<ComparisonResult> getComparisonResults(FixedAmountCalculationDetails o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("calculationPeriod", (CalculationPeriodBase) o.getCalculationPeriod() != null ? 1 : 0, 1, 1), 
				checkCardinality("calculationPeriodNotionalAmount", (Money) o.getCalculationPeriodNotionalAmount() != null ? 1 : 0, 1, 1), 
				checkCardinality("fixedRate", (BigDecimal) o.getFixedRate() != null ? 1 : 0, 1, 1), 
				checkCardinality("yearFraction", (BigDecimal) o.getYearFraction() != null ? 1 : 0, 1, 1), 
				checkCardinality("calculatedAmount", (BigDecimal) o.getCalculatedAmount() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<FixedAmountCalculationDetails> validate(RosettaPath path, FixedAmountCalculationDetails o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FixedAmountCalculationDetails", ValidationType.CARDINALITY, "FixedAmountCalculationDetails", path, "", error);
		}
		return success("FixedAmountCalculationDetails", ValidationType.CARDINALITY, "FixedAmountCalculationDetails", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FixedAmountCalculationDetails o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FixedAmountCalculationDetails", ValidationType.CARDINALITY, "FixedAmountCalculationDetails", path, "", res.getError());
				}
				return success("FixedAmountCalculationDetails", ValidationType.CARDINALITY, "FixedAmountCalculationDetails", path, "");
			})
			.collect(toList());
	}

}
