package cdm.event.position.validation;

import cdm.event.position.CounterpartyPosition;
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

public class CounterpartyPositionTypeFormatValidator implements Validator<CounterpartyPosition> {

	private List<ComparisonResult> getComparisonResults(CounterpartyPosition o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CounterpartyPosition> validate(RosettaPath path, CounterpartyPosition o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CounterpartyPosition", ValidationType.TYPE_FORMAT, "CounterpartyPosition", path, "", error);
		}
		return success("CounterpartyPosition", ValidationType.TYPE_FORMAT, "CounterpartyPosition", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CounterpartyPosition o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CounterpartyPosition", ValidationType.TYPE_FORMAT, "CounterpartyPosition", path, "", res.getError());
				}
				return success("CounterpartyPosition", ValidationType.TYPE_FORMAT, "CounterpartyPosition", path, "");
			})
			.collect(toList());
	}

}
