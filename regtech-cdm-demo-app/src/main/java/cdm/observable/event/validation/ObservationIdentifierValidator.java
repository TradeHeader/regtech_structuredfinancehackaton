package cdm.observable.event.validation;

import cdm.base.datetime.TimeZone;
import cdm.observable.asset.InformationSource;
import cdm.observable.asset.Observable;
import cdm.observable.event.DeterminationMethodology;
import cdm.observable.event.ObservationIdentifier;
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

public class ObservationIdentifierValidator implements Validator<ObservationIdentifier> {

	private List<ComparisonResult> getComparisonResults(ObservationIdentifier o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("observable", (Observable) o.getObservable() != null ? 1 : 0, 1, 1), 
				checkCardinality("observationDate", (Date) o.getObservationDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("observationTime", (TimeZone) o.getObservationTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("informationSource", (InformationSource) o.getInformationSource() != null ? 1 : 0, 0, 1), 
				checkCardinality("determinationMethodology", (DeterminationMethodology) o.getDeterminationMethodology() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ObservationIdentifier> validate(RosettaPath path, ObservationIdentifier o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ObservationIdentifier", ValidationType.CARDINALITY, "ObservationIdentifier", path, "", error);
		}
		return success("ObservationIdentifier", ValidationType.CARDINALITY, "ObservationIdentifier", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ObservationIdentifier o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ObservationIdentifier", ValidationType.CARDINALITY, "ObservationIdentifier", path, "", res.getError());
				}
				return success("ObservationIdentifier", ValidationType.CARDINALITY, "ObservationIdentifier", path, "");
			})
			.collect(toList());
	}

}
