package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.FinalCalculationPeriodDateAdjustment;
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

public class FinalCalculationPeriodDateAdjustmentTypeFormatValidator implements Validator<FinalCalculationPeriodDateAdjustment> {

	private List<ComparisonResult> getComparisonResults(FinalCalculationPeriodDateAdjustment o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FinalCalculationPeriodDateAdjustment> validate(RosettaPath path, FinalCalculationPeriodDateAdjustment o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FinalCalculationPeriodDateAdjustment", ValidationType.TYPE_FORMAT, "FinalCalculationPeriodDateAdjustment", path, "", error);
		}
		return success("FinalCalculationPeriodDateAdjustment", ValidationType.TYPE_FORMAT, "FinalCalculationPeriodDateAdjustment", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FinalCalculationPeriodDateAdjustment o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FinalCalculationPeriodDateAdjustment", ValidationType.TYPE_FORMAT, "FinalCalculationPeriodDateAdjustment", path, "", res.getError());
				}
				return success("FinalCalculationPeriodDateAdjustment", ValidationType.TYPE_FORMAT, "FinalCalculationPeriodDateAdjustment", path, "");
			})
			.collect(toList());
	}

}
