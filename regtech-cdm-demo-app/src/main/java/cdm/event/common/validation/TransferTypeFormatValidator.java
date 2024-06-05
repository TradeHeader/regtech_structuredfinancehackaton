package cdm.event.common.validation;

import cdm.event.common.Transfer;
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

public class TransferTypeFormatValidator implements Validator<Transfer> {

	private List<ComparisonResult> getComparisonResults(Transfer o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Transfer> validate(RosettaPath path, Transfer o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Transfer", ValidationType.TYPE_FORMAT, "Transfer", path, "", error);
		}
		return success("Transfer", ValidationType.TYPE_FORMAT, "Transfer", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Transfer o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Transfer", ValidationType.TYPE_FORMAT, "Transfer", path, "", res.getError());
				}
				return success("Transfer", ValidationType.TYPE_FORMAT, "Transfer", path, "");
			})
			.collect(toList());
	}

}
