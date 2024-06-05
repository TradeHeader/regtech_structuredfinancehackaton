package cdm.event.common.validation;

import cdm.event.common.ObservationEvent;
import cdm.event.common.ObservationInstruction;
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

public class ObservationInstructionValidator implements Validator<ObservationInstruction> {

	private List<ComparisonResult> getComparisonResults(ObservationInstruction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("observationEvent", (ObservationEvent) o.getObservationEvent() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<ObservationInstruction> validate(RosettaPath path, ObservationInstruction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ObservationInstruction", ValidationType.CARDINALITY, "ObservationInstruction", path, "", error);
		}
		return success("ObservationInstruction", ValidationType.CARDINALITY, "ObservationInstruction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ObservationInstruction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ObservationInstruction", ValidationType.CARDINALITY, "ObservationInstruction", path, "", res.getError());
				}
				return success("ObservationInstruction", ValidationType.CARDINALITY, "ObservationInstruction", path, "");
			})
			.collect(toList());
	}

}
