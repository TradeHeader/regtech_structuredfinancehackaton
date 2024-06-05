package cdm.regulation.validation;

import cdm.regulation.Sngl;
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

public class SnglTypeFormatValidator implements Validator<Sngl> {

	private List<ComparisonResult> getComparisonResults(Sngl o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Sngl> validate(RosettaPath path, Sngl o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Sngl", ValidationType.TYPE_FORMAT, "Sngl", path, "", error);
		}
		return success("Sngl", ValidationType.TYPE_FORMAT, "Sngl", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Sngl o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Sngl", ValidationType.TYPE_FORMAT, "Sngl", path, "", res.getError());
				}
				return success("Sngl", ValidationType.TYPE_FORMAT, "Sngl", path, "");
			})
			.collect(toList());
	}

}
