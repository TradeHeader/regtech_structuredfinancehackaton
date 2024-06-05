package cdm.event.workflow.validation;

import cdm.event.workflow.Velocity;
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

public class VelocityTypeFormatValidator implements Validator<Velocity> {

	private List<ComparisonResult> getComparisonResults(Velocity o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("periodMultiplier", o.getPeriodMultiplier(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<Velocity> validate(RosettaPath path, Velocity o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Velocity", ValidationType.TYPE_FORMAT, "Velocity", path, "", error);
		}
		return success("Velocity", ValidationType.TYPE_FORMAT, "Velocity", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Velocity o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Velocity", ValidationType.TYPE_FORMAT, "Velocity", path, "", res.getError());
				}
				return success("Velocity", ValidationType.TYPE_FORMAT, "Velocity", path, "");
			})
			.collect(toList());
	}

}
