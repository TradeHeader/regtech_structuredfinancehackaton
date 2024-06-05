package cdm.event.common.validation;

import cdm.event.common.State;
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

public class StateTypeFormatValidator implements Validator<State> {

	private List<ComparisonResult> getComparisonResults(State o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<State> validate(RosettaPath path, State o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("State", ValidationType.TYPE_FORMAT, "State", path, "", error);
		}
		return success("State", ValidationType.TYPE_FORMAT, "State", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, State o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("State", ValidationType.TYPE_FORMAT, "State", path, "", res.getError());
				}
				return success("State", ValidationType.TYPE_FORMAT, "State", path, "");
			})
			.collect(toList());
	}

}
