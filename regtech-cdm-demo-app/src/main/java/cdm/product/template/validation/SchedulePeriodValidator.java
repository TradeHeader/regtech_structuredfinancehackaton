package cdm.product.template.validation;

import cdm.base.datetime.DateRange;
import cdm.product.asset.CalculationScheduleDeliveryPeriods;
import cdm.product.template.SchedulePeriod;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class SchedulePeriodValidator implements Validator<SchedulePeriod> {

	private List<ComparisonResult> getComparisonResults(SchedulePeriod o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("calculationPeriod", (DateRange) o.getCalculationPeriod() != null ? 1 : 0, 1, 1), 
				checkCardinality("paymentDate", (Date) o.getPaymentDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("fixingPeriod", (DateRange) o.getFixingPeriod() != null ? 1 : 0, 1, 1), 
				checkCardinality("deliveryPeriod", (CalculationScheduleDeliveryPeriods) o.getDeliveryPeriod() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<SchedulePeriod> validate(RosettaPath path, SchedulePeriod o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SchedulePeriod", ValidationType.CARDINALITY, "SchedulePeriod", path, "", error);
		}
		return success("SchedulePeriod", ValidationType.CARDINALITY, "SchedulePeriod", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SchedulePeriod o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SchedulePeriod", ValidationType.CARDINALITY, "SchedulePeriod", path, "", res.getError());
				}
				return success("SchedulePeriod", ValidationType.CARDINALITY, "SchedulePeriod", path, "");
			})
			.collect(toList());
	}

}
