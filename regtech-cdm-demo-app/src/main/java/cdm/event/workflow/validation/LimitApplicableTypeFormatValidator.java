package cdm.event.workflow.validation;

import cdm.event.workflow.LimitApplicable;
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

public class LimitApplicableTypeFormatValidator implements Validator<LimitApplicable> {

	private List<ComparisonResult> getComparisonResults(LimitApplicable o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("clipSize", o.getClipSize(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<LimitApplicable> validate(RosettaPath path, LimitApplicable o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("LimitApplicable", ValidationType.TYPE_FORMAT, "LimitApplicable", path, "", error);
		}
		return success("LimitApplicable", ValidationType.TYPE_FORMAT, "LimitApplicable", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, LimitApplicable o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("LimitApplicable", ValidationType.TYPE_FORMAT, "LimitApplicable", path, "", res.getError());
				}
				return success("LimitApplicable", ValidationType.TYPE_FORMAT, "LimitApplicable", path, "");
			})
			.collect(toList());
	}

}
