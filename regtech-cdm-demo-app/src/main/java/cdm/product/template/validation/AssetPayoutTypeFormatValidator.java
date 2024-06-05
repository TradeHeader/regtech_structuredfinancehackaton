package cdm.product.template.validation;

import cdm.product.template.AssetPayout;
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

public class AssetPayoutTypeFormatValidator implements Validator<AssetPayout> {

	private List<ComparisonResult> getComparisonResults(AssetPayout o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AssetPayout> validate(RosettaPath path, AssetPayout o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AssetPayout", ValidationType.TYPE_FORMAT, "AssetPayout", path, "", error);
		}
		return success("AssetPayout", ValidationType.TYPE_FORMAT, "AssetPayout", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AssetPayout o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AssetPayout", ValidationType.TYPE_FORMAT, "AssetPayout", path, "", res.getError());
				}
				return success("AssetPayout", ValidationType.TYPE_FORMAT, "AssetPayout", path, "");
			})
			.collect(toList());
	}

}
