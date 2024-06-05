package cdm.product.template.validation;

import cdm.product.template.EarlyTerminationEvent;
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

public class EarlyTerminationEventValidator implements Validator<EarlyTerminationEvent> {

	private List<ComparisonResult> getComparisonResults(EarlyTerminationEvent o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("adjustedExerciseDate", (Date) o.getAdjustedExerciseDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("adjustedEarlyTerminationDate", (Date) o.getAdjustedEarlyTerminationDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("adjustedCashSettlementValuationDate", (Date) o.getAdjustedCashSettlementValuationDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("adjustedCashSettlementPaymentDate", (Date) o.getAdjustedCashSettlementPaymentDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("adjustedExerciseFeePaymentDate", (Date) o.getAdjustedExerciseFeePaymentDate() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<EarlyTerminationEvent> validate(RosettaPath path, EarlyTerminationEvent o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EarlyTerminationEvent", ValidationType.CARDINALITY, "EarlyTerminationEvent", path, "", error);
		}
		return success("EarlyTerminationEvent", ValidationType.CARDINALITY, "EarlyTerminationEvent", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EarlyTerminationEvent o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EarlyTerminationEvent", ValidationType.CARDINALITY, "EarlyTerminationEvent", path, "", res.getError());
				}
				return success("EarlyTerminationEvent", ValidationType.CARDINALITY, "EarlyTerminationEvent", path, "");
			})
			.collect(toList());
	}

}
