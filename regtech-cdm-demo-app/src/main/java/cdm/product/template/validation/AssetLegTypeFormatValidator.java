package cdm.product.template.validation;

import cdm.product.template.AssetLeg;
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

public class AssetLegTypeFormatValidator implements Validator<AssetLeg> {

	private List<ComparisonResult> getComparisonResults(AssetLeg o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AssetLeg> validate(RosettaPath path, AssetLeg o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AssetLeg", ValidationType.TYPE_FORMAT, "AssetLeg", path, "", error);
		}
		return success("AssetLeg", ValidationType.TYPE_FORMAT, "AssetLeg", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AssetLeg o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AssetLeg", ValidationType.TYPE_FORMAT, "AssetLeg", path, "", res.getError());
				}
				return success("AssetLeg", ValidationType.TYPE_FORMAT, "AssetLeg", path, "");
			})
			.collect(toList());
	}

}
