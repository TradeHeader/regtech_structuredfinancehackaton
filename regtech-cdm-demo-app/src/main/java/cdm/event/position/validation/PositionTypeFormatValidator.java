package cdm.event.position.validation;

import cdm.event.position.Position;
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

public class PositionTypeFormatValidator implements Validator<Position> {

	private List<ComparisonResult> getComparisonResults(Position o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Position> validate(RosettaPath path, Position o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Position", ValidationType.TYPE_FORMAT, "Position", path, "", error);
		}
		return success("Position", ValidationType.TYPE_FORMAT, "Position", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Position o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Position", ValidationType.TYPE_FORMAT, "Position", path, "", res.getError());
				}
				return success("Position", ValidationType.TYPE_FORMAT, "Position", path, "");
			})
			.collect(toList());
	}

}
