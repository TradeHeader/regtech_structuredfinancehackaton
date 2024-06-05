package cdm.product.asset.validation;

import cdm.product.asset.ReferencePool;
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

public class ReferencePoolTypeFormatValidator implements Validator<ReferencePool> {

	private List<ComparisonResult> getComparisonResults(ReferencePool o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<ReferencePool> validate(RosettaPath path, ReferencePool o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ReferencePool", ValidationType.TYPE_FORMAT, "ReferencePool", path, "", error);
		}
		return success("ReferencePool", ValidationType.TYPE_FORMAT, "ReferencePool", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ReferencePool o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ReferencePool", ValidationType.TYPE_FORMAT, "ReferencePool", path, "", res.getError());
				}
				return success("ReferencePool", ValidationType.TYPE_FORMAT, "ReferencePool", path, "");
			})
			.collect(toList());
	}

}
