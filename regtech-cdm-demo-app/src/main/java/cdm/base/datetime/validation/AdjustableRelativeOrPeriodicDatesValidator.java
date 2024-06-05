package cdm.base.datetime.validation;

import cdm.base.datetime.AdjustableDates;
import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.base.datetime.PeriodicDates;
import cdm.base.datetime.RelativeDates;
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

public class AdjustableRelativeOrPeriodicDatesValidator implements Validator<AdjustableRelativeOrPeriodicDates> {

	private List<ComparisonResult> getComparisonResults(AdjustableRelativeOrPeriodicDates o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("adjustableDates", (AdjustableDates) o.getAdjustableDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("relativeDates", (RelativeDates) o.getRelativeDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("periodicDates", (PeriodicDates) o.getPeriodicDates() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AdjustableRelativeOrPeriodicDates> validate(RosettaPath path, AdjustableRelativeOrPeriodicDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AdjustableRelativeOrPeriodicDates", ValidationType.CARDINALITY, "AdjustableRelativeOrPeriodicDates", path, "", error);
		}
		return success("AdjustableRelativeOrPeriodicDates", ValidationType.CARDINALITY, "AdjustableRelativeOrPeriodicDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AdjustableRelativeOrPeriodicDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AdjustableRelativeOrPeriodicDates", ValidationType.CARDINALITY, "AdjustableRelativeOrPeriodicDates", path, "", res.getError());
				}
				return success("AdjustableRelativeOrPeriodicDates", ValidationType.CARDINALITY, "AdjustableRelativeOrPeriodicDates", path, "");
			})
			.collect(toList());
	}

}
