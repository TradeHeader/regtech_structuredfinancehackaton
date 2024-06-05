package cdm.base.datetime.validation;

import cdm.base.datetime.AdjustableDate;
import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustedRelativeDateOffset;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AdjustableOrRelativeDateValidator implements Validator<AdjustableOrRelativeDate> {

	private List<ComparisonResult> getComparisonResults(AdjustableOrRelativeDate o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("adjustableDate", (AdjustableDate) o.getAdjustableDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("relativeDate", (AdjustedRelativeDateOffset) o.getRelativeDate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AdjustableOrRelativeDate> validate(RosettaPath path, AdjustableOrRelativeDate o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AdjustableOrRelativeDate", ValidationType.CARDINALITY, "AdjustableOrRelativeDate", path, "", error);
		}
		return success("AdjustableOrRelativeDate", ValidationType.CARDINALITY, "AdjustableOrRelativeDate", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AdjustableOrRelativeDate o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AdjustableOrRelativeDate", ValidationType.CARDINALITY, "AdjustableOrRelativeDate", path, "", res.getError());
				}
				return success("AdjustableOrRelativeDate", ValidationType.CARDINALITY, "AdjustableOrRelativeDate", path, "");
			})
			.collect(toList());
	}

}
