package cdm.event.common.validation;

import cdm.event.common.MarginCallResponse;
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

public class MarginCallResponseTypeFormatValidator implements Validator<MarginCallResponse> {

	private List<ComparisonResult> getComparisonResults(MarginCallResponse o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<MarginCallResponse> validate(RosettaPath path, MarginCallResponse o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MarginCallResponse", ValidationType.TYPE_FORMAT, "MarginCallResponse", path, "", error);
		}
		return success("MarginCallResponse", ValidationType.TYPE_FORMAT, "MarginCallResponse", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MarginCallResponse o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MarginCallResponse", ValidationType.TYPE_FORMAT, "MarginCallResponse", path, "", res.getError());
				}
				return success("MarginCallResponse", ValidationType.TYPE_FORMAT, "MarginCallResponse", path, "");
			})
			.collect(toList());
	}

}
