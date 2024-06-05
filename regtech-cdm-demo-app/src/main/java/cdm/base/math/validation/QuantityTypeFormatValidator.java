package cdm.base.math.validation;

import cdm.base.math.Quantity;
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

public class QuantityTypeFormatValidator implements Validator<Quantity> {

	private List<ComparisonResult> getComparisonResults(Quantity o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Quantity> validate(RosettaPath path, Quantity o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Quantity", ValidationType.TYPE_FORMAT, "Quantity", path, "", error);
		}
		return success("Quantity", ValidationType.TYPE_FORMAT, "Quantity", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Quantity o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Quantity", ValidationType.TYPE_FORMAT, "Quantity", path, "", res.getError());
				}
				return success("Quantity", ValidationType.TYPE_FORMAT, "Quantity", path, "");
			})
			.collect(toList());
	}

}
