package cdm.observable.event.validation;

import cdm.observable.event.CreditEvents;
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

public class CreditEventsTypeFormatValidator implements Validator<CreditEvents> {

	private List<ComparisonResult> getComparisonResults(CreditEvents o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CreditEvents> validate(RosettaPath path, CreditEvents o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CreditEvents", ValidationType.TYPE_FORMAT, "CreditEvents", path, "", error);
		}
		return success("CreditEvents", ValidationType.TYPE_FORMAT, "CreditEvents", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CreditEvents o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CreditEvents", ValidationType.TYPE_FORMAT, "CreditEvents", path, "", res.getError());
				}
				return success("CreditEvents", ValidationType.TYPE_FORMAT, "CreditEvents", path, "");
			})
			.collect(toList());
	}

}
