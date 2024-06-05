package cdm.base.math.validation;

import cdm.base.math.Measure;
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

public class MeasureTypeFormatValidator implements Validator<Measure> {

	private List<ComparisonResult> getComparisonResults(Measure o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Measure> validate(RosettaPath path, Measure o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Measure", ValidationType.TYPE_FORMAT, "Measure", path, "", error);
		}
		return success("Measure", ValidationType.TYPE_FORMAT, "Measure", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Measure o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Measure", ValidationType.TYPE_FORMAT, "Measure", path, "", res.getError());
				}
				return success("Measure", ValidationType.TYPE_FORMAT, "Measure", path, "");
			})
			.collect(toList());
	}

}
