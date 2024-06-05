package cdm.product.template.validation;

import cdm.product.template.Asian;
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

public class AsianTypeFormatValidator implements Validator<Asian> {

	private List<ComparisonResult> getComparisonResults(Asian o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<Asian> validate(RosettaPath path, Asian o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Asian", ValidationType.TYPE_FORMAT, "Asian", path, "", error);
		}
		return success("Asian", ValidationType.TYPE_FORMAT, "Asian", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Asian o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Asian", ValidationType.TYPE_FORMAT, "Asian", path, "", res.getError());
				}
				return success("Asian", ValidationType.TYPE_FORMAT, "Asian", path, "");
			})
			.collect(toList());
	}

}
