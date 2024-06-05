package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.FxLinkedNotionalSchedule;
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

public class FxLinkedNotionalScheduleTypeFormatValidator implements Validator<FxLinkedNotionalSchedule> {

	private List<ComparisonResult> getComparisonResults(FxLinkedNotionalSchedule o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FxLinkedNotionalSchedule> validate(RosettaPath path, FxLinkedNotionalSchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FxLinkedNotionalSchedule", ValidationType.TYPE_FORMAT, "FxLinkedNotionalSchedule", path, "", error);
		}
		return success("FxLinkedNotionalSchedule", ValidationType.TYPE_FORMAT, "FxLinkedNotionalSchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FxLinkedNotionalSchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FxLinkedNotionalSchedule", ValidationType.TYPE_FORMAT, "FxLinkedNotionalSchedule", path, "", res.getError());
				}
				return success("FxLinkedNotionalSchedule", ValidationType.TYPE_FORMAT, "FxLinkedNotionalSchedule", path, "");
			})
			.collect(toList());
	}

}
