package cdm.base.datetime.validation;

import cdm.base.datetime.RelativeDateOffset;
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

public class RelativeDateOffsetTypeFormatValidator implements Validator<RelativeDateOffset> {

	private List<ComparisonResult> getComparisonResults(RelativeDateOffset o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("periodMultiplier", o.getPeriodMultiplier(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<RelativeDateOffset> validate(RosettaPath path, RelativeDateOffset o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("RelativeDateOffset", ValidationType.TYPE_FORMAT, "RelativeDateOffset", path, "", error);
		}
		return success("RelativeDateOffset", ValidationType.TYPE_FORMAT, "RelativeDateOffset", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, RelativeDateOffset o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("RelativeDateOffset", ValidationType.TYPE_FORMAT, "RelativeDateOffset", path, "", res.getError());
				}
				return success("RelativeDateOffset", ValidationType.TYPE_FORMAT, "RelativeDateOffset", path, "");
			})
			.collect(toList());
	}

}
