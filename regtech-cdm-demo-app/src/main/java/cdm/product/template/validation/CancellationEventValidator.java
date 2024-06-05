package cdm.product.template.validation;

import cdm.product.template.CancellationEvent;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class CancellationEventValidator implements Validator<CancellationEvent> {

	private List<ComparisonResult> getComparisonResults(CancellationEvent o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("adjustedExerciseDate", (Date) o.getAdjustedExerciseDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("adjustedEarlyTerminationDate", (Date) o.getAdjustedEarlyTerminationDate() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<CancellationEvent> validate(RosettaPath path, CancellationEvent o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CancellationEvent", ValidationType.CARDINALITY, "CancellationEvent", path, "", error);
		}
		return success("CancellationEvent", ValidationType.CARDINALITY, "CancellationEvent", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CancellationEvent o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CancellationEvent", ValidationType.CARDINALITY, "CancellationEvent", path, "", res.getError());
				}
				return success("CancellationEvent", ValidationType.CARDINALITY, "CancellationEvent", path, "");
			})
			.collect(toList());
	}

}
