package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.WeightedAveragingObservation;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class WeightedAveragingObservationValidator implements Validator<WeightedAveragingObservation> {

	private List<ComparisonResult> getComparisonResults(WeightedAveragingObservation o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("dateTime", (ZonedDateTime) o.getDateTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("observationNumber", (Integer) o.getObservationNumber() != null ? 1 : 0, 0, 1), 
				checkCardinality("weight", (BigDecimal) o.getWeight() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<WeightedAveragingObservation> validate(RosettaPath path, WeightedAveragingObservation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("WeightedAveragingObservation", ValidationType.CARDINALITY, "WeightedAveragingObservation", path, "", error);
		}
		return success("WeightedAveragingObservation", ValidationType.CARDINALITY, "WeightedAveragingObservation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, WeightedAveragingObservation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("WeightedAveragingObservation", ValidationType.CARDINALITY, "WeightedAveragingObservation", path, "", res.getError());
				}
				return success("WeightedAveragingObservation", ValidationType.CARDINALITY, "WeightedAveragingObservation", path, "");
			})
			.collect(toList());
	}

}
