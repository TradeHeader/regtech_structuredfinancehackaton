package cdm.event.common.validation;

import cdm.event.common.CounterpartyPositionBusinessEvent;
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

public class CounterpartyPositionBusinessEventTypeFormatValidator implements Validator<CounterpartyPositionBusinessEvent> {

	private List<ComparisonResult> getComparisonResults(CounterpartyPositionBusinessEvent o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CounterpartyPositionBusinessEvent> validate(RosettaPath path, CounterpartyPositionBusinessEvent o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CounterpartyPositionBusinessEvent", ValidationType.TYPE_FORMAT, "CounterpartyPositionBusinessEvent", path, "", error);
		}
		return success("CounterpartyPositionBusinessEvent", ValidationType.TYPE_FORMAT, "CounterpartyPositionBusinessEvent", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CounterpartyPositionBusinessEvent o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CounterpartyPositionBusinessEvent", ValidationType.TYPE_FORMAT, "CounterpartyPositionBusinessEvent", path, "", res.getError());
				}
				return success("CounterpartyPositionBusinessEvent", ValidationType.TYPE_FORMAT, "CounterpartyPositionBusinessEvent", path, "");
			})
			.collect(toList());
	}

}
