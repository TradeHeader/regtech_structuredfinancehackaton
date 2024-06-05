package cdm.event.common.validation;

import cdm.event.common.TransferExpression;
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

public class TransferExpressionTypeFormatValidator implements Validator<TransferExpression> {

	private List<ComparisonResult> getComparisonResults(TransferExpression o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<TransferExpression> validate(RosettaPath path, TransferExpression o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("TransferExpression", ValidationType.TYPE_FORMAT, "TransferExpression", path, "", error);
		}
		return success("TransferExpression", ValidationType.TYPE_FORMAT, "TransferExpression", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, TransferExpression o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("TransferExpression", ValidationType.TYPE_FORMAT, "TransferExpression", path, "", res.getError());
				}
				return success("TransferExpression", ValidationType.TYPE_FORMAT, "TransferExpression", path, "");
			})
			.collect(toList());
	}

}
