package cdm.observable.event.validation;

import cdm.observable.event.FailureToPay;
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

public class FailureToPayTypeFormatValidator implements Validator<FailureToPay> {

	private List<ComparisonResult> getComparisonResults(FailureToPay o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FailureToPay> validate(RosettaPath path, FailureToPay o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FailureToPay", ValidationType.TYPE_FORMAT, "FailureToPay", path, "", error);
		}
		return success("FailureToPay", ValidationType.TYPE_FORMAT, "FailureToPay", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FailureToPay o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FailureToPay", ValidationType.TYPE_FORMAT, "FailureToPay", path, "", res.getError());
				}
				return success("FailureToPay", ValidationType.TYPE_FORMAT, "FailureToPay", path, "");
			})
			.collect(toList());
	}

}
