package cdm.base.math.validation;

import cdm.base.math.Rounding;
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

public class RoundingTypeFormatValidator implements Validator<Rounding> {

	private List<ComparisonResult> getComparisonResults(Rounding o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("precision", o.getPrecision(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<Rounding> validate(RosettaPath path, Rounding o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Rounding", ValidationType.TYPE_FORMAT, "Rounding", path, "", error);
		}
		return success("Rounding", ValidationType.TYPE_FORMAT, "Rounding", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Rounding o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Rounding", ValidationType.TYPE_FORMAT, "Rounding", path, "", res.getError());
				}
				return success("Rounding", ValidationType.TYPE_FORMAT, "Rounding", path, "");
			})
			.collect(toList());
	}

}
