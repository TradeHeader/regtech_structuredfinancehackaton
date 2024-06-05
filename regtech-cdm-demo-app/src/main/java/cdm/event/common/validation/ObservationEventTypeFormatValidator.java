package cdm.event.common.validation;

import cdm.event.common.ObservationEvent;
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

public class ObservationEventTypeFormatValidator implements Validator<ObservationEvent> {

	private List<ComparisonResult> getComparisonResults(ObservationEvent o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ObservationEvent> validate(RosettaPath path, ObservationEvent o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ObservationEvent", ValidationType.TYPE_FORMAT, "ObservationEvent", path, "", error);
		}
		return success("ObservationEvent", ValidationType.TYPE_FORMAT, "ObservationEvent", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ObservationEvent o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ObservationEvent", ValidationType.TYPE_FORMAT, "ObservationEvent", path, "", res.getError());
				}
				return success("ObservationEvent", ValidationType.TYPE_FORMAT, "ObservationEvent", path, "");
			})
			.collect(toList());
	}

}
