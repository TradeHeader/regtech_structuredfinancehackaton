package cdm.observable.asset.calculatedrate.validation;

import cdm.observable.asset.calculatedrate.CalculatedRateObservationDatesAndWeights;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CalculatedRateObservationDatesAndWeightsTypeFormatValidator implements Validator<CalculatedRateObservationDatesAndWeights> {

	private List<ComparisonResult> getComparisonResults(CalculatedRateObservationDatesAndWeights o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CalculatedRateObservationDatesAndWeights> validate(RosettaPath path, CalculatedRateObservationDatesAndWeights o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CalculatedRateObservationDatesAndWeights", ValidationType.TYPE_FORMAT, "CalculatedRateObservationDatesAndWeights", path, "", error);
		}
		return success("CalculatedRateObservationDatesAndWeights", ValidationType.TYPE_FORMAT, "CalculatedRateObservationDatesAndWeights", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CalculatedRateObservationDatesAndWeights o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CalculatedRateObservationDatesAndWeights", ValidationType.TYPE_FORMAT, "CalculatedRateObservationDatesAndWeights", path, "", res.getError());
				}
				return success("CalculatedRateObservationDatesAndWeights", ValidationType.TYPE_FORMAT, "CalculatedRateObservationDatesAndWeights", path, "");
			})
			.collect(toList());
	}

}
