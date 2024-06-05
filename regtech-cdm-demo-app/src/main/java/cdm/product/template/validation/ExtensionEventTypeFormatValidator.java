package cdm.product.template.validation;

import cdm.product.template.ExtensionEvent;
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

public class ExtensionEventTypeFormatValidator implements Validator<ExtensionEvent> {

	private List<ComparisonResult> getComparisonResults(ExtensionEvent o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ExtensionEvent> validate(RosettaPath path, ExtensionEvent o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ExtensionEvent", ValidationType.TYPE_FORMAT, "ExtensionEvent", path, "", error);
		}
		return success("ExtensionEvent", ValidationType.TYPE_FORMAT, "ExtensionEvent", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ExtensionEvent o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ExtensionEvent", ValidationType.TYPE_FORMAT, "ExtensionEvent", path, "", res.getError());
				}
				return success("ExtensionEvent", ValidationType.TYPE_FORMAT, "ExtensionEvent", path, "");
			})
			.collect(toList());
	}

}
