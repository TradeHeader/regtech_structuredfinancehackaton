package cdm.event.common.validation;

import cdm.event.common.Confirmation;
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

public class ConfirmationTypeFormatValidator implements Validator<Confirmation> {

	private List<ComparisonResult> getComparisonResults(Confirmation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Confirmation> validate(RosettaPath path, Confirmation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Confirmation", ValidationType.TYPE_FORMAT, "Confirmation", path, "", error);
		}
		return success("Confirmation", ValidationType.TYPE_FORMAT, "Confirmation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Confirmation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Confirmation", ValidationType.TYPE_FORMAT, "Confirmation", path, "", res.getError());
				}
				return success("Confirmation", ValidationType.TYPE_FORMAT, "Confirmation", path, "");
			})
			.collect(toList());
	}

}
