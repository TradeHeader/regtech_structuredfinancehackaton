package cdm.product.asset.floatingrate.validation;

import cdm.observable.asset.Money;
import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails;
import cdm.product.asset.floatingrate.FloatingRateProcessingDetails;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails;
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

public class FloatingAmountCalculationDetailsValidator implements Validator<FloatingAmountCalculationDetails> {

	private List<ComparisonResult> getComparisonResults(FloatingAmountCalculationDetails o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("calculationPeriod", (CalculationPeriodBase) o.getCalculationPeriod() != null ? 1 : 0, 1, 1), 
				checkCardinality("calculationPeriodNotionalAmount", (Money) o.getCalculationPeriodNotionalAmount() != null ? 1 : 0, 1, 1), 
				checkCardinality("floatingRate", (FloatingRateSettingDetails) o.getFloatingRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("processingDetails", (FloatingRateProcessingDetails) o.getProcessingDetails() != null ? 1 : 0, 0, 1), 
				checkCardinality("appliedRate", (BigDecimal) o.getAppliedRate() != null ? 1 : 0, 1, 1), 
				checkCardinality("yearFraction", (BigDecimal) o.getYearFraction() != null ? 1 : 0, 1, 1), 
				checkCardinality("calculatedAmount", (BigDecimal) o.getCalculatedAmount() != null ? 1 : 0, 1, 1), 
				checkCardinality("spreadExclusiveCalculatedAMount", (BigDecimal) o.getSpreadExclusiveCalculatedAMount() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<FloatingAmountCalculationDetails> validate(RosettaPath path, FloatingAmountCalculationDetails o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingAmountCalculationDetails", ValidationType.CARDINALITY, "FloatingAmountCalculationDetails", path, "", error);
		}
		return success("FloatingAmountCalculationDetails", ValidationType.CARDINALITY, "FloatingAmountCalculationDetails", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingAmountCalculationDetails o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingAmountCalculationDetails", ValidationType.CARDINALITY, "FloatingAmountCalculationDetails", path, "", res.getError());
				}
				return success("FloatingAmountCalculationDetails", ValidationType.CARDINALITY, "FloatingAmountCalculationDetails", path, "");
			})
			.collect(toList());
	}

}
