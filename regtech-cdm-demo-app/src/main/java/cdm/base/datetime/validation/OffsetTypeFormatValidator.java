package cdm.base.datetime.validation;

import cdm.base.datetime.Offset;
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

public class OffsetTypeFormatValidator implements Validator<Offset> {

	private List<ComparisonResult> getComparisonResults(Offset o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("periodMultiplier", o.getPeriodMultiplier(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<Offset> validate(RosettaPath path, Offset o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Offset", ValidationType.TYPE_FORMAT, "Offset", path, "", error);
		}
		return success("Offset", ValidationType.TYPE_FORMAT, "Offset", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Offset o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Offset", ValidationType.TYPE_FORMAT, "Offset", path, "", res.getError());
				}
				return success("Offset", ValidationType.TYPE_FORMAT, "Offset", path, "");
			})
			.collect(toList());
	}

}
