package cdm.observable.asset.validation;

import cdm.observable.asset.ObservationSource;
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

public class ObservationSourceTypeFormatValidator implements Validator<ObservationSource> {

	private List<ComparisonResult> getComparisonResults(ObservationSource o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ObservationSource> validate(RosettaPath path, ObservationSource o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ObservationSource", ValidationType.TYPE_FORMAT, "ObservationSource", path, "", error);
		}
		return success("ObservationSource", ValidationType.TYPE_FORMAT, "ObservationSource", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ObservationSource o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ObservationSource", ValidationType.TYPE_FORMAT, "ObservationSource", path, "", res.getError());
				}
				return success("ObservationSource", ValidationType.TYPE_FORMAT, "ObservationSource", path, "");
			})
			.collect(toList());
	}

}
