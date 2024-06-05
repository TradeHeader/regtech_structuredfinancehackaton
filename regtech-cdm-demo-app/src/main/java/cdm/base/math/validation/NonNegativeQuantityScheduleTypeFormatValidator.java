package cdm.base.math.validation;

import cdm.base.math.NonNegativeQuantitySchedule;
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

public class NonNegativeQuantityScheduleTypeFormatValidator implements Validator<NonNegativeQuantitySchedule> {

	private List<ComparisonResult> getComparisonResults(NonNegativeQuantitySchedule o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<NonNegativeQuantitySchedule> validate(RosettaPath path, NonNegativeQuantitySchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("NonNegativeQuantitySchedule", ValidationType.TYPE_FORMAT, "NonNegativeQuantitySchedule", path, "", error);
		}
		return success("NonNegativeQuantitySchedule", ValidationType.TYPE_FORMAT, "NonNegativeQuantitySchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, NonNegativeQuantitySchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("NonNegativeQuantitySchedule", ValidationType.TYPE_FORMAT, "NonNegativeQuantitySchedule", path, "", res.getError());
				}
				return success("NonNegativeQuantitySchedule", ValidationType.TYPE_FORMAT, "NonNegativeQuantitySchedule", path, "");
			})
			.collect(toList());
	}

}
