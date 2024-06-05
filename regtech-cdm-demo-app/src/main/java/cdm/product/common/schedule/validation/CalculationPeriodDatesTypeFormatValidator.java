package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.CalculationPeriodDates;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CalculationPeriodDatesTypeFormatValidator implements Validator<CalculationPeriodDates> {

	private List<ComparisonResult> getComparisonResults(CalculationPeriodDates o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CalculationPeriodDates> validate(RosettaPath path, CalculationPeriodDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalculationPeriodDates", ValidationType.TYPE_FORMAT, "CalculationPeriodDates", path, "", error);
		}
		return success("CalculationPeriodDates", ValidationType.TYPE_FORMAT, "CalculationPeriodDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculationPeriodDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculationPeriodDates", ValidationType.TYPE_FORMAT, "CalculationPeriodDates", path, "", res.getError());
				}
				return success("CalculationPeriodDates", ValidationType.TYPE_FORMAT, "CalculationPeriodDates", path, "");
			})
			.collect(toList());
	}

}
