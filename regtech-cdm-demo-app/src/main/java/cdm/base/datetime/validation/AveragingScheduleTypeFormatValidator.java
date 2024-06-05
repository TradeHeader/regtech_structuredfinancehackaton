package cdm.base.datetime.validation;

import cdm.base.datetime.AveragingSchedule;
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

public class AveragingScheduleTypeFormatValidator implements Validator<AveragingSchedule> {

	private List<ComparisonResult> getComparisonResults(AveragingSchedule o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AveragingSchedule> validate(RosettaPath path, AveragingSchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AveragingSchedule", ValidationType.TYPE_FORMAT, "AveragingSchedule", path, "", error);
		}
		return success("AveragingSchedule", ValidationType.TYPE_FORMAT, "AveragingSchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AveragingSchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AveragingSchedule", ValidationType.TYPE_FORMAT, "AveragingSchedule", path, "", res.getError());
				}
				return success("AveragingSchedule", ValidationType.TYPE_FORMAT, "AveragingSchedule", path, "");
			})
			.collect(toList());
	}

}
