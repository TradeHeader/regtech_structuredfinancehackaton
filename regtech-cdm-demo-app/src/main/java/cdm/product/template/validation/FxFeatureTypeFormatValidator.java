package cdm.product.template.validation;

import cdm.product.template.FxFeature;
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

public class FxFeatureTypeFormatValidator implements Validator<FxFeature> {

	private List<ComparisonResult> getComparisonResults(FxFeature o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FxFeature> validate(RosettaPath path, FxFeature o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FxFeature", ValidationType.TYPE_FORMAT, "FxFeature", path, "", error);
		}
		return success("FxFeature", ValidationType.TYPE_FORMAT, "FxFeature", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FxFeature o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FxFeature", ValidationType.TYPE_FORMAT, "FxFeature", path, "", res.getError());
				}
				return success("FxFeature", ValidationType.TYPE_FORMAT, "FxFeature", path, "");
			})
			.collect(toList());
	}

}
