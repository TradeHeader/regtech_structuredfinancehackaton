package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.RateSchedule;
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

public class RateScheduleTypeFormatValidator implements Validator<RateSchedule> {

	private List<ComparisonResult> getComparisonResults(RateSchedule o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<RateSchedule> validate(RosettaPath path, RateSchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("RateSchedule", ValidationType.TYPE_FORMAT, "RateSchedule", path, "", error);
		}
		return success("RateSchedule", ValidationType.TYPE_FORMAT, "RateSchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, RateSchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("RateSchedule", ValidationType.TYPE_FORMAT, "RateSchedule", path, "", res.getError());
				}
				return success("RateSchedule", ValidationType.TYPE_FORMAT, "RateSchedule", path, "");
			})
			.collect(toList());
	}

}
