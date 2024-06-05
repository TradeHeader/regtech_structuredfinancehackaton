package cdm.base.datetime.validation;

import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
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

public class AdjustableRelativeOrPeriodicDatesTypeFormatValidator implements Validator<AdjustableRelativeOrPeriodicDates> {

	private List<ComparisonResult> getComparisonResults(AdjustableRelativeOrPeriodicDates o) {
		return Lists.<ComparisonResult>newArrayList(
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
			return failure("AdjustableRelativeOrPeriodicDates", ValidationType.TYPE_FORMAT, "AdjustableRelativeOrPeriodicDates", path, "", error);
		}
		return success("AdjustableRelativeOrPeriodicDates", ValidationType.TYPE_FORMAT, "AdjustableRelativeOrPeriodicDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AdjustableRelativeOrPeriodicDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AdjustableRelativeOrPeriodicDates", ValidationType.TYPE_FORMAT, "AdjustableRelativeOrPeriodicDates", path, "", res.getError());
				}
				return success("AdjustableRelativeOrPeriodicDates", ValidationType.TYPE_FORMAT, "AdjustableRelativeOrPeriodicDates", path, "");
			})
			.collect(toList());
	}

}
