package cdm.observable.event.validation;

import cdm.base.datetime.DateList;
import cdm.observable.event.FeaturePayment;
import cdm.observable.event.Trigger;
import cdm.observable.event.TriggerEvent;
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

public class TriggerEventValidator implements Validator<TriggerEvent> {

	private List<ComparisonResult> getComparisonResults(TriggerEvent o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("triggerDates", (DateList) o.getTriggerDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("trigger", (Trigger) o.getTrigger() != null ? 1 : 0, 1, 1), 
				checkCardinality("featurePayment", (FeaturePayment) o.getFeaturePayment() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<TriggerEvent> validate(RosettaPath path, TriggerEvent o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TriggerEvent", ValidationType.CARDINALITY, "TriggerEvent", path, "", error);
		}
		return success("TriggerEvent", ValidationType.CARDINALITY, "TriggerEvent", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TriggerEvent o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TriggerEvent", ValidationType.CARDINALITY, "TriggerEvent", path, "", res.getError());
				}
				return success("TriggerEvent", ValidationType.CARDINALITY, "TriggerEvent", path, "");
			})
			.collect(toList());
	}

}
