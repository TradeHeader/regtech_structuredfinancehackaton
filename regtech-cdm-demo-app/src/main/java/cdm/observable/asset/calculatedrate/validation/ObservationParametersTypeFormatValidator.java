package cdm.observable.asset.calculatedrate.validation;

import cdm.observable.asset.calculatedrate.ObservationParameters;
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

public class ObservationParametersTypeFormatValidator implements Validator<ObservationParameters> {

	private List<ComparisonResult> getComparisonResults(ObservationParameters o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ObservationParameters> validate(RosettaPath path, ObservationParameters o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ObservationParameters", ValidationType.TYPE_FORMAT, "ObservationParameters", path, "", error);
		}
		return success("ObservationParameters", ValidationType.TYPE_FORMAT, "ObservationParameters", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ObservationParameters o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ObservationParameters", ValidationType.TYPE_FORMAT, "ObservationParameters", path, "", res.getError());
				}
				return success("ObservationParameters", ValidationType.TYPE_FORMAT, "ObservationParameters", path, "");
			})
			.collect(toList());
	}

}
