package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.ObservationSchedule;
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

public class ObservationScheduleTypeFormatValidator implements Validator<ObservationSchedule> {

	private List<ComparisonResult> getComparisonResults(ObservationSchedule o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ObservationSchedule> validate(RosettaPath path, ObservationSchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ObservationSchedule", ValidationType.TYPE_FORMAT, "ObservationSchedule", path, "", error);
		}
		return success("ObservationSchedule", ValidationType.TYPE_FORMAT, "ObservationSchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ObservationSchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ObservationSchedule", ValidationType.TYPE_FORMAT, "ObservationSchedule", path, "", res.getError());
				}
				return success("ObservationSchedule", ValidationType.TYPE_FORMAT, "ObservationSchedule", path, "");
			})
			.collect(toList());
	}

}
