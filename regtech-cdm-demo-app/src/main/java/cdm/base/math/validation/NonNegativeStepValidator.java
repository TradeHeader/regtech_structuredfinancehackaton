package cdm.base.math.validation;

import cdm.base.math.NonNegativeStep;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class NonNegativeStepValidator implements Validator<NonNegativeStep> {

	private List<ComparisonResult> getComparisonResults(NonNegativeStep o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("stepDate", (Date) o.getStepDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("stepValue", (BigDecimal) o.getStepValue() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<NonNegativeStep> validate(RosettaPath path, NonNegativeStep o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("NonNegativeStep", ValidationType.CARDINALITY, "NonNegativeStep", path, "", error);
		}
		return success("NonNegativeStep", ValidationType.CARDINALITY, "NonNegativeStep", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, NonNegativeStep o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("NonNegativeStep", ValidationType.CARDINALITY, "NonNegativeStep", path, "", res.getError());
				}
				return success("NonNegativeStep", ValidationType.CARDINALITY, "NonNegativeStep", path, "");
			})
			.collect(toList());
	}

}
