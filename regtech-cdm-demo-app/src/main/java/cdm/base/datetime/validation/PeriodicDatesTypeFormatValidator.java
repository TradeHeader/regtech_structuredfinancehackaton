package cdm.base.datetime.validation;

import cdm.base.datetime.PeriodicDates;
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

public class PeriodicDatesTypeFormatValidator implements Validator<PeriodicDates> {

	private List<ComparisonResult> getComparisonResults(PeriodicDates o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PeriodicDates> validate(RosettaPath path, PeriodicDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PeriodicDates", ValidationType.TYPE_FORMAT, "PeriodicDates", path, "", error);
		}
		return success("PeriodicDates", ValidationType.TYPE_FORMAT, "PeriodicDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PeriodicDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PeriodicDates", ValidationType.TYPE_FORMAT, "PeriodicDates", path, "", res.getError());
				}
				return success("PeriodicDates", ValidationType.TYPE_FORMAT, "PeriodicDates", path, "");
			})
			.collect(toList());
	}

}
