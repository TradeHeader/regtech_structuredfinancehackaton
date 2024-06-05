package cdm.base.math.validation;

import cdm.base.math.QuantitySchedule;
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

public class QuantityScheduleTypeFormatValidator implements Validator<QuantitySchedule> {

	private List<ComparisonResult> getComparisonResults(QuantitySchedule o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<QuantitySchedule> validate(RosettaPath path, QuantitySchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("QuantitySchedule", ValidationType.TYPE_FORMAT, "QuantitySchedule", path, "", error);
		}
		return success("QuantitySchedule", ValidationType.TYPE_FORMAT, "QuantitySchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, QuantitySchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("QuantitySchedule", ValidationType.TYPE_FORMAT, "QuantitySchedule", path, "", res.getError());
				}
				return success("QuantitySchedule", ValidationType.TYPE_FORMAT, "QuantitySchedule", path, "");
			})
			.collect(toList());
	}

}
