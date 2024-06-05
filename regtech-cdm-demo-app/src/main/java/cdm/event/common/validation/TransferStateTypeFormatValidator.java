package cdm.event.common.validation;

import cdm.event.common.TransferState;
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

public class TransferStateTypeFormatValidator implements Validator<TransferState> {

	private List<ComparisonResult> getComparisonResults(TransferState o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<TransferState> validate(RosettaPath path, TransferState o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TransferState", ValidationType.TYPE_FORMAT, "TransferState", path, "", error);
		}
		return success("TransferState", ValidationType.TYPE_FORMAT, "TransferState", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TransferState o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TransferState", ValidationType.TYPE_FORMAT, "TransferState", path, "", res.getError());
				}
				return success("TransferState", ValidationType.TYPE_FORMAT, "TransferState", path, "");
			})
			.collect(toList());
	}

}
