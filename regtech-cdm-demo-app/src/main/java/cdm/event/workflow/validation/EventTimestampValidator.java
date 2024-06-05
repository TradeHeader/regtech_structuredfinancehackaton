package cdm.event.workflow.validation;

import cdm.event.workflow.EventTimestamp;
import cdm.event.workflow.EventTimestampQualificationEnum;
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

public class EventTimestampValidator implements Validator<EventTimestamp> {

	private List<ComparisonResult> getComparisonResults(EventTimestamp o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("dateTime", (ZonedDateTime) o.getDateTime() != null ? 1 : 0, 1, 1), 
				checkCardinality("qualification", (EventTimestampQualificationEnum) o.getQualification() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<EventTimestamp> validate(RosettaPath path, EventTimestamp o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EventTimestamp", ValidationType.CARDINALITY, "EventTimestamp", path, "", error);
		}
		return success("EventTimestamp", ValidationType.CARDINALITY, "EventTimestamp", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EventTimestamp o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EventTimestamp", ValidationType.CARDINALITY, "EventTimestamp", path, "", res.getError());
				}
				return success("EventTimestamp", ValidationType.CARDINALITY, "EventTimestamp", path, "");
			})
			.collect(toList());
	}

}
