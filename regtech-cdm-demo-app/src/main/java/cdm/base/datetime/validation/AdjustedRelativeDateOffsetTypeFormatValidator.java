package cdm.base.datetime.validation;

import cdm.base.datetime.AdjustedRelativeDateOffset;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkNumber;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AdjustedRelativeDateOffsetTypeFormatValidator implements Validator<AdjustedRelativeDateOffset> {

	private List<ComparisonResult> getComparisonResults(AdjustedRelativeDateOffset o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("periodMultiplier", o.getPeriodMultiplier(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<AdjustedRelativeDateOffset> validate(RosettaPath path, AdjustedRelativeDateOffset o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AdjustedRelativeDateOffset", ValidationType.TYPE_FORMAT, "AdjustedRelativeDateOffset", path, "", error);
		}
		return success("AdjustedRelativeDateOffset", ValidationType.TYPE_FORMAT, "AdjustedRelativeDateOffset", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AdjustedRelativeDateOffset o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AdjustedRelativeDateOffset", ValidationType.TYPE_FORMAT, "AdjustedRelativeDateOffset", path, "", res.getError());
				}
				return success("AdjustedRelativeDateOffset", ValidationType.TYPE_FORMAT, "AdjustedRelativeDateOffset", path, "");
			})
			.collect(toList());
	}

}
