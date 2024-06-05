package cdm.observable.event.validation;

import cdm.observable.event.CreditEvents;
import cdm.observable.event.Trigger;
import cdm.observable.event.TriggerTimeTypeEnum;
import cdm.observable.event.TriggerTypeEnum;
import cdm.observable.event.metafields.ReferenceWithMetaCreditEvents;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class TriggerValidator implements Validator<Trigger> {

	private List<ComparisonResult> getComparisonResults(Trigger o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("level", (BigDecimal) o.getLevel() != null ? 1 : 0, 0, 1), 
				checkCardinality("levelPercentage", (BigDecimal) o.getLevelPercentage() != null ? 1 : 0, 0, 1), 
				checkCardinality("creditEvents", (CreditEvents) o.getCreditEvents() != null ? 1 : 0, 0, 1), 
				checkCardinality("creditEventsReference", (ReferenceWithMetaCreditEvents) o.getCreditEventsReference() != null ? 1 : 0, 0, 1), 
				checkCardinality("triggerType", (TriggerTypeEnum) o.getTriggerType() != null ? 1 : 0, 0, 1), 
				checkCardinality("triggerTimeType", (TriggerTimeTypeEnum) o.getTriggerTimeType() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Trigger> validate(RosettaPath path, Trigger o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Trigger", ValidationType.CARDINALITY, "Trigger", path, "", error);
		}
		return success("Trigger", ValidationType.CARDINALITY, "Trigger", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Trigger o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Trigger", ValidationType.CARDINALITY, "Trigger", path, "", res.getError());
				}
				return success("Trigger", ValidationType.CARDINALITY, "Trigger", path, "");
			})
			.collect(toList());
	}

}
