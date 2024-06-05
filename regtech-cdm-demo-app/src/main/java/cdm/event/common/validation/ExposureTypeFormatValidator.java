package cdm.event.common.validation;

import cdm.event.common.Exposure;
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

public class ExposureTypeFormatValidator implements Validator<Exposure> {

	private List<ComparisonResult> getComparisonResults(Exposure o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Exposure> validate(RosettaPath path, Exposure o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Exposure", ValidationType.TYPE_FORMAT, "Exposure", path, "", error);
		}
		return success("Exposure", ValidationType.TYPE_FORMAT, "Exposure", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Exposure o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Exposure", ValidationType.TYPE_FORMAT, "Exposure", path, "", res.getError());
				}
				return success("Exposure", ValidationType.TYPE_FORMAT, "Exposure", path, "");
			})
			.collect(toList());
	}

}
