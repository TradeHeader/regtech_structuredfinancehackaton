package cdm.event.workflow.validation;

import cdm.event.workflow.MessageInformation;
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

public class MessageInformationTypeFormatValidator implements Validator<MessageInformation> {

	private List<ComparisonResult> getComparisonResults(MessageInformation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<MessageInformation> validate(RosettaPath path, MessageInformation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MessageInformation", ValidationType.TYPE_FORMAT, "MessageInformation", path, "", error);
		}
		return success("MessageInformation", ValidationType.TYPE_FORMAT, "MessageInformation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MessageInformation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MessageInformation", ValidationType.TYPE_FORMAT, "MessageInformation", path, "", res.getError());
				}
				return success("MessageInformation", ValidationType.TYPE_FORMAT, "MessageInformation", path, "");
			})
			.collect(toList());
	}

}
