package cdm.product.template.validation;

import cdm.product.template.Composite;
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

public class CompositeTypeFormatValidator implements Validator<Composite> {

	private List<ComparisonResult> getComparisonResults(Composite o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Composite> validate(RosettaPath path, Composite o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Composite", ValidationType.TYPE_FORMAT, "Composite", path, "", error);
		}
		return success("Composite", ValidationType.TYPE_FORMAT, "Composite", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Composite o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Composite", ValidationType.TYPE_FORMAT, "Composite", path, "", res.getError());
				}
				return success("Composite", ValidationType.TYPE_FORMAT, "Composite", path, "");
			})
			.collect(toList());
	}

}
