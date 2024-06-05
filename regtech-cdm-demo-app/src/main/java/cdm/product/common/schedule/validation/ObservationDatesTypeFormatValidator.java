package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.ObservationDates;
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

public class ObservationDatesTypeFormatValidator implements Validator<ObservationDates> {

	private List<ComparisonResult> getComparisonResults(ObservationDates o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ObservationDates> validate(RosettaPath path, ObservationDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ObservationDates", ValidationType.TYPE_FORMAT, "ObservationDates", path, "", error);
		}
		return success("ObservationDates", ValidationType.TYPE_FORMAT, "ObservationDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ObservationDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ObservationDates", ValidationType.TYPE_FORMAT, "ObservationDates", path, "", res.getError());
				}
				return success("ObservationDates", ValidationType.TYPE_FORMAT, "ObservationDates", path, "");
			})
			.collect(toList());
	}

}
