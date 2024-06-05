package cdm.base.datetime.validation;

import cdm.base.datetime.AdjustableDate;
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

public class AdjustableDateTypeFormatValidator implements Validator<AdjustableDate> {

	private List<ComparisonResult> getComparisonResults(AdjustableDate o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AdjustableDate> validate(RosettaPath path, AdjustableDate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AdjustableDate", ValidationType.TYPE_FORMAT, "AdjustableDate", path, "", error);
		}
		return success("AdjustableDate", ValidationType.TYPE_FORMAT, "AdjustableDate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AdjustableDate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AdjustableDate", ValidationType.TYPE_FORMAT, "AdjustableDate", path, "", res.getError());
				}
				return success("AdjustableDate", ValidationType.TYPE_FORMAT, "AdjustableDate", path, "");
			})
			.collect(toList());
	}

}
