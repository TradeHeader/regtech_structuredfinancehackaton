package cdm.product.common.schedule.validation;

import cdm.observable.asset.Money;
import cdm.product.asset.FloatingRateDefinition;
import cdm.product.common.schedule.CalculationPeriod;
import cdm.product.common.schedule.FxLinkedNotionalAmount;
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

public class CalculationPeriodValidator implements Validator<CalculationPeriod> {

	private List<ComparisonResult> getComparisonResults(CalculationPeriod o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("adjustedStartDate", (Date) o.getAdjustedStartDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("adjustedEndDate", (Date) o.getAdjustedEndDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("unadjustedStartDate", (Date) o.getUnadjustedStartDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("unadjustedEndDate", (Date) o.getUnadjustedEndDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationPeriodNumberOfDays", (Integer) o.getCalculationPeriodNumberOfDays() != null ? 1 : 0, 0, 1), 
				checkCardinality("notionalAmount", (BigDecimal) o.getNotionalAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("fxLinkedNotionalAmount", (FxLinkedNotionalAmount) o.getFxLinkedNotionalAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("floatingRateDefinition", (FloatingRateDefinition) o.getFloatingRateDefinition() != null ? 1 : 0, 0, 1), 
				checkCardinality("fixedRate", (BigDecimal) o.getFixedRate() != null ? 1 : 0, 0, 1), 
				checkCardinality("dayCountYearFraction", (BigDecimal) o.getDayCountYearFraction() != null ? 1 : 0, 0, 1), 
				checkCardinality("forecastAmount", (Money) o.getForecastAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("forecastRate", (BigDecimal) o.getForecastRate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CalculationPeriod> validate(RosettaPath path, CalculationPeriod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalculationPeriod", ValidationType.CARDINALITY, "CalculationPeriod", path, "", error);
		}
		return success("CalculationPeriod", ValidationType.CARDINALITY, "CalculationPeriod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculationPeriod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculationPeriod", ValidationType.CARDINALITY, "CalculationPeriod", path, "", res.getError());
				}
				return success("CalculationPeriod", ValidationType.CARDINALITY, "CalculationPeriod", path, "");
			})
			.collect(toList());
	}

}
