package cdm.product.template.validation;

import cdm.product.template.OptionalEarlyTerminationAdjustedDates;
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

public class OptionalEarlyTerminationAdjustedDatesTypeFormatValidator implements Validator<OptionalEarlyTerminationAdjustedDates> {

	private List<ComparisonResult> getComparisonResults(OptionalEarlyTerminationAdjustedDates o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<OptionalEarlyTerminationAdjustedDates> validate(RosettaPath path, OptionalEarlyTerminationAdjustedDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OptionalEarlyTerminationAdjustedDates", ValidationType.TYPE_FORMAT, "OptionalEarlyTerminationAdjustedDates", path, "", error);
		}
		return success("OptionalEarlyTerminationAdjustedDates", ValidationType.TYPE_FORMAT, "OptionalEarlyTerminationAdjustedDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OptionalEarlyTerminationAdjustedDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OptionalEarlyTerminationAdjustedDates", ValidationType.TYPE_FORMAT, "OptionalEarlyTerminationAdjustedDates", path, "", res.getError());
				}
				return success("OptionalEarlyTerminationAdjustedDates", ValidationType.TYPE_FORMAT, "OptionalEarlyTerminationAdjustedDates", path, "");
			})
			.collect(toList());
	}

}
