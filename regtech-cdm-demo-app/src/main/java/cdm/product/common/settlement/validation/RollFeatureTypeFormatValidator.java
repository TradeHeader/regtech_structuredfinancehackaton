package cdm.product.common.settlement.validation;

import cdm.product.common.settlement.RollFeature;
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

public class RollFeatureTypeFormatValidator implements Validator<RollFeature> {

	private List<ComparisonResult> getComparisonResults(RollFeature o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<RollFeature> validate(RosettaPath path, RollFeature o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("RollFeature", ValidationType.TYPE_FORMAT, "RollFeature", path, "", error);
		}
		return success("RollFeature", ValidationType.TYPE_FORMAT, "RollFeature", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, RollFeature o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("RollFeature", ValidationType.TYPE_FORMAT, "RollFeature", path, "", res.getError());
				}
				return success("RollFeature", ValidationType.TYPE_FORMAT, "RollFeature", path, "");
			})
			.collect(toList());
	}

}
