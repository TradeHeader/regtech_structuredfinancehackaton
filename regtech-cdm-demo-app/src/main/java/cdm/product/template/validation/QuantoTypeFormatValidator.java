package cdm.product.template.validation;

import cdm.product.template.Quanto;
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

public class QuantoTypeFormatValidator implements Validator<Quanto> {

	private List<ComparisonResult> getComparisonResults(Quanto o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Quanto> validate(RosettaPath path, Quanto o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Quanto", ValidationType.TYPE_FORMAT, "Quanto", path, "", error);
		}
		return success("Quanto", ValidationType.TYPE_FORMAT, "Quanto", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Quanto o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Quanto", ValidationType.TYPE_FORMAT, "Quanto", path, "", res.getError());
				}
				return success("Quanto", ValidationType.TYPE_FORMAT, "Quanto", path, "");
			})
			.collect(toList());
	}

}
