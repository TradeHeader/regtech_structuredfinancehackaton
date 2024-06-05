package cdm.product.asset.validation;

import cdm.product.asset.ReferenceObligation;
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

public class ReferenceObligationTypeFormatValidator implements Validator<ReferenceObligation> {

	private List<ComparisonResult> getComparisonResults(ReferenceObligation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ReferenceObligation> validate(RosettaPath path, ReferenceObligation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ReferenceObligation", ValidationType.TYPE_FORMAT, "ReferenceObligation", path, "", error);
		}
		return success("ReferenceObligation", ValidationType.TYPE_FORMAT, "ReferenceObligation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ReferenceObligation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ReferenceObligation", ValidationType.TYPE_FORMAT, "ReferenceObligation", path, "", res.getError());
				}
				return success("ReferenceObligation", ValidationType.TYPE_FORMAT, "ReferenceObligation", path, "");
			})
			.collect(toList());
	}

}
