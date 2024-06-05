package cdm.observable.event.validation;

import cdm.observable.asset.Price;
import cdm.observable.event.Observation;
import cdm.observable.event.ObservationIdentifier;
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

public class ObservationValidator implements Validator<Observation> {

	private List<ComparisonResult> getComparisonResults(Observation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("observedValue", (Price) o.getObservedValue() != null ? 1 : 0, 1, 1), 
				checkCardinality("observationIdentifier", (ObservationIdentifier) o.getObservationIdentifier() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<Observation> validate(RosettaPath path, Observation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Observation", ValidationType.CARDINALITY, "Observation", path, "", error);
		}
		return success("Observation", ValidationType.CARDINALITY, "Observation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Observation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Observation", ValidationType.CARDINALITY, "Observation", path, "", res.getError());
				}
				return success("Observation", ValidationType.CARDINALITY, "Observation", path, "");
			})
			.collect(toList());
	}

}
