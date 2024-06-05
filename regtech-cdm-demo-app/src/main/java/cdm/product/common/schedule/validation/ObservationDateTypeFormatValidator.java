package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.ObservationDate;
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

public class ObservationDateTypeFormatValidator implements Validator<ObservationDate> {

	private List<ComparisonResult> getComparisonResults(ObservationDate o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ObservationDate> validate(RosettaPath path, ObservationDate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ObservationDate", ValidationType.TYPE_FORMAT, "ObservationDate", path, "", error);
		}
		return success("ObservationDate", ValidationType.TYPE_FORMAT, "ObservationDate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ObservationDate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ObservationDate", ValidationType.TYPE_FORMAT, "ObservationDate", path, "", res.getError());
				}
				return success("ObservationDate", ValidationType.TYPE_FORMAT, "ObservationDate", path, "");
			})
			.collect(toList());
	}

}
