package cdm.event.common.validation;

import cdm.event.common.PositionIdentifier;
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

public class PositionIdentifierTypeFormatValidator implements Validator<PositionIdentifier> {

	private List<ComparisonResult> getComparisonResults(PositionIdentifier o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PositionIdentifier> validate(RosettaPath path, PositionIdentifier o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PositionIdentifier", ValidationType.TYPE_FORMAT, "PositionIdentifier", path, "", error);
		}
		return success("PositionIdentifier", ValidationType.TYPE_FORMAT, "PositionIdentifier", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PositionIdentifier o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PositionIdentifier", ValidationType.TYPE_FORMAT, "PositionIdentifier", path, "", res.getError());
				}
				return success("PositionIdentifier", ValidationType.TYPE_FORMAT, "PositionIdentifier", path, "");
			})
			.collect(toList());
	}

}
