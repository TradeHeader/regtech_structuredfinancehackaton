package cdm.product.template.validation;

import cdm.product.template.MandatoryEarlyTerminationAdjustedDates;
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

public class MandatoryEarlyTerminationAdjustedDatesTypeFormatValidator implements Validator<MandatoryEarlyTerminationAdjustedDates> {

	private List<ComparisonResult> getComparisonResults(MandatoryEarlyTerminationAdjustedDates o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<MandatoryEarlyTerminationAdjustedDates> validate(RosettaPath path, MandatoryEarlyTerminationAdjustedDates o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MandatoryEarlyTerminationAdjustedDates", ValidationType.TYPE_FORMAT, "MandatoryEarlyTerminationAdjustedDates", path, "", error);
		}
		return success("MandatoryEarlyTerminationAdjustedDates", ValidationType.TYPE_FORMAT, "MandatoryEarlyTerminationAdjustedDates", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MandatoryEarlyTerminationAdjustedDates o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MandatoryEarlyTerminationAdjustedDates", ValidationType.TYPE_FORMAT, "MandatoryEarlyTerminationAdjustedDates", path, "", res.getError());
				}
				return success("MandatoryEarlyTerminationAdjustedDates", ValidationType.TYPE_FORMAT, "MandatoryEarlyTerminationAdjustedDates", path, "");
			})
			.collect(toList());
	}

}
