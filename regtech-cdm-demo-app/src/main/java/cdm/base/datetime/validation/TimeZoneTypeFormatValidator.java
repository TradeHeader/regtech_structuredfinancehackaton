package cdm.base.datetime.validation;

import cdm.base.datetime.TimeZone;
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

public class TimeZoneTypeFormatValidator implements Validator<TimeZone> {

	private List<ComparisonResult> getComparisonResults(TimeZone o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<TimeZone> validate(RosettaPath path, TimeZone o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TimeZone", ValidationType.TYPE_FORMAT, "TimeZone", path, "", error);
		}
		return success("TimeZone", ValidationType.TYPE_FORMAT, "TimeZone", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TimeZone o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TimeZone", ValidationType.TYPE_FORMAT, "TimeZone", path, "", res.getError());
				}
				return success("TimeZone", ValidationType.TYPE_FORMAT, "TimeZone", path, "");
			})
			.collect(toList());
	}

}
