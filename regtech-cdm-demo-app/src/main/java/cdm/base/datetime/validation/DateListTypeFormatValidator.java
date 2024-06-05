package cdm.base.datetime.validation;

import cdm.base.datetime.DateList;
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

public class DateListTypeFormatValidator implements Validator<DateList> {

	private List<ComparisonResult> getComparisonResults(DateList o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<DateList> validate(RosettaPath path, DateList o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DateList", ValidationType.TYPE_FORMAT, "DateList", path, "", error);
		}
		return success("DateList", ValidationType.TYPE_FORMAT, "DateList", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DateList o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DateList", ValidationType.TYPE_FORMAT, "DateList", path, "", res.getError());
				}
				return success("DateList", ValidationType.TYPE_FORMAT, "DateList", path, "");
			})
			.collect(toList());
	}

}
