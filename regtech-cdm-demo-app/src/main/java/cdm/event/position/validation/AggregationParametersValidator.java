package cdm.event.position.validation;

import cdm.event.position.AggregationParameters;
import cdm.event.position.PositionStatusEnum;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.time.ZonedDateTime;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AggregationParametersValidator implements Validator<AggregationParameters> {

	private List<ComparisonResult> getComparisonResults(AggregationParameters o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("dateTime", (ZonedDateTime) o.getDateTime() != null ? 1 : 0, 1, 1), 
				checkCardinality("totalPosition", (Boolean) o.getTotalPosition() != null ? 1 : 0, 0, 1), 
				checkCardinality("positionStatus", (PositionStatusEnum) o.getPositionStatus() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AggregationParameters> validate(RosettaPath path, AggregationParameters o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AggregationParameters", ValidationType.CARDINALITY, "AggregationParameters", path, "", error);
		}
		return success("AggregationParameters", ValidationType.CARDINALITY, "AggregationParameters", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AggregationParameters o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AggregationParameters", ValidationType.CARDINALITY, "AggregationParameters", path, "", res.getError());
				}
				return success("AggregationParameters", ValidationType.CARDINALITY, "AggregationParameters", path, "");
			})
			.collect(toList());
	}

}
