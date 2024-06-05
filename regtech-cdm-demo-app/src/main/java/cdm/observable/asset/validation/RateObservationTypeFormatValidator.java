package cdm.observable.asset.validation;

import cdm.observable.asset.RateObservation;
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

public class RateObservationTypeFormatValidator implements Validator<RateObservation> {

	private List<ComparisonResult> getComparisonResults(RateObservation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkNumber("observationWeight", o.getObservationWeight(), empty(), of(0), empty(), empty())
			);
	}

	@Override
	public ValidationResult<RateObservation> validate(RosettaPath path, RateObservation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("RateObservation", ValidationType.TYPE_FORMAT, "RateObservation", path, "", error);
		}
		return success("RateObservation", ValidationType.TYPE_FORMAT, "RateObservation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, RateObservation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("RateObservation", ValidationType.TYPE_FORMAT, "RateObservation", path, "", res.getError());
				}
				return success("RateObservation", ValidationType.TYPE_FORMAT, "RateObservation", path, "");
			})
			.collect(toList());
	}

}
