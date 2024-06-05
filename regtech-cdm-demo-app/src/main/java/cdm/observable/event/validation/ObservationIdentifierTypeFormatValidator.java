package cdm.observable.event.validation;

import cdm.observable.event.ObservationIdentifier;
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

public class ObservationIdentifierTypeFormatValidator implements Validator<ObservationIdentifier> {

	private List<ComparisonResult> getComparisonResults(ObservationIdentifier o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ObservationIdentifier> validate(RosettaPath path, ObservationIdentifier o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ObservationIdentifier", ValidationType.TYPE_FORMAT, "ObservationIdentifier", path, "", error);
		}
		return success("ObservationIdentifier", ValidationType.TYPE_FORMAT, "ObservationIdentifier", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ObservationIdentifier o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ObservationIdentifier", ValidationType.TYPE_FORMAT, "ObservationIdentifier", path, "", res.getError());
				}
				return success("ObservationIdentifier", ValidationType.TYPE_FORMAT, "ObservationIdentifier", path, "");
			})
			.collect(toList());
	}

}
