package cdm.observable.asset.validation;

import cdm.observable.asset.PriceSchedule;
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

public class PriceScheduleTypeFormatValidator implements Validator<PriceSchedule> {

	private List<ComparisonResult> getComparisonResults(PriceSchedule o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PriceSchedule> validate(RosettaPath path, PriceSchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PriceSchedule", ValidationType.TYPE_FORMAT, "PriceSchedule", path, "", error);
		}
		return success("PriceSchedule", ValidationType.TYPE_FORMAT, "PriceSchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PriceSchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PriceSchedule", ValidationType.TYPE_FORMAT, "PriceSchedule", path, "", res.getError());
				}
				return success("PriceSchedule", ValidationType.TYPE_FORMAT, "PriceSchedule", path, "");
			})
			.collect(toList());
	}

}
