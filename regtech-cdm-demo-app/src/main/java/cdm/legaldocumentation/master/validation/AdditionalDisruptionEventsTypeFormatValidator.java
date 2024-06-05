package cdm.legaldocumentation.master.validation;

import cdm.legaldocumentation.master.AdditionalDisruptionEvents;
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

public class AdditionalDisruptionEventsTypeFormatValidator implements Validator<AdditionalDisruptionEvents> {

	private List<ComparisonResult> getComparisonResults(AdditionalDisruptionEvents o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AdditionalDisruptionEvents> validate(RosettaPath path, AdditionalDisruptionEvents o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AdditionalDisruptionEvents", ValidationType.TYPE_FORMAT, "AdditionalDisruptionEvents", path, "", error);
		}
		return success("AdditionalDisruptionEvents", ValidationType.TYPE_FORMAT, "AdditionalDisruptionEvents", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AdditionalDisruptionEvents o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AdditionalDisruptionEvents", ValidationType.TYPE_FORMAT, "AdditionalDisruptionEvents", path, "", res.getError());
				}
				return success("AdditionalDisruptionEvents", ValidationType.TYPE_FORMAT, "AdditionalDisruptionEvents", path, "");
			})
			.collect(toList());
	}

}
