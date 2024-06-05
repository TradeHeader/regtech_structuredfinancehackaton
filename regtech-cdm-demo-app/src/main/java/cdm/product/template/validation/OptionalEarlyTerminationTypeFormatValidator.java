package cdm.product.template.validation;

import cdm.product.template.OptionalEarlyTermination;
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

public class OptionalEarlyTerminationTypeFormatValidator implements Validator<OptionalEarlyTermination> {

	private List<ComparisonResult> getComparisonResults(OptionalEarlyTermination o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<OptionalEarlyTermination> validate(RosettaPath path, OptionalEarlyTermination o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("OptionalEarlyTermination", ValidationType.TYPE_FORMAT, "OptionalEarlyTermination", path, "", error);
		}
		return success("OptionalEarlyTermination", ValidationType.TYPE_FORMAT, "OptionalEarlyTermination", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, OptionalEarlyTermination o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("OptionalEarlyTermination", ValidationType.TYPE_FORMAT, "OptionalEarlyTermination", path, "", res.getError());
				}
				return success("OptionalEarlyTermination", ValidationType.TYPE_FORMAT, "OptionalEarlyTermination", path, "");
			})
			.collect(toList());
	}

}
