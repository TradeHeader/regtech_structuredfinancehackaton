package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.DateRelativeToCalculationPeriodDates;
import cdm.product.common.schedule.metafields.ReferenceWithMetaCalculationPeriodDates;
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

public class DateRelativeToCalculationPeriodDatesValidator implements Validator<DateRelativeToCalculationPeriodDates> {

	private List<ComparisonResult> getComparisonResults(DateRelativeToCalculationPeriodDates o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("calculationPeriodDatesReference", (List<? extends ReferenceWithMetaCalculationPeriodDates>) o.getCalculationPeriodDatesReference() == null ? 0 : ((List<? extends ReferenceWithMetaCalculationPeriodDates>) o.getCalculationPeriodDatesReference()).size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<DateRelativeToCalculationPeriodDates> validate(RosettaPath path, DateRelativeToCalculationPeriodDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DateRelativeToCalculationPeriodDates", ValidationType.CARDINALITY, "DateRelativeToCalculationPeriodDates", path, "", error);
		}
		return success("DateRelativeToCalculationPeriodDates", ValidationType.CARDINALITY, "DateRelativeToCalculationPeriodDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DateRelativeToCalculationPeriodDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DateRelativeToCalculationPeriodDates", ValidationType.CARDINALITY, "DateRelativeToCalculationPeriodDates", path, "", res.getError());
				}
				return success("DateRelativeToCalculationPeriodDates", ValidationType.CARDINALITY, "DateRelativeToCalculationPeriodDates", path, "");
			})
			.collect(toList());
	}

}
