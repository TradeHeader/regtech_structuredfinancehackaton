package cdm.product.asset.validation;

import cdm.product.asset.AssetDeliveryProfileBlock;
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

public class AssetDeliveryProfileBlockTypeFormatValidator implements Validator<AssetDeliveryProfileBlock> {

	private List<ComparisonResult> getComparisonResults(AssetDeliveryProfileBlock o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AssetDeliveryProfileBlock> validate(RosettaPath path, AssetDeliveryProfileBlock o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AssetDeliveryProfileBlock", ValidationType.TYPE_FORMAT, "AssetDeliveryProfileBlock", path, "", error);
		}
		return success("AssetDeliveryProfileBlock", ValidationType.TYPE_FORMAT, "AssetDeliveryProfileBlock", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AssetDeliveryProfileBlock o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AssetDeliveryProfileBlock", ValidationType.TYPE_FORMAT, "AssetDeliveryProfileBlock", path, "", res.getError());
				}
				return success("AssetDeliveryProfileBlock", ValidationType.TYPE_FORMAT, "AssetDeliveryProfileBlock", path, "");
			})
			.collect(toList());
	}

}
