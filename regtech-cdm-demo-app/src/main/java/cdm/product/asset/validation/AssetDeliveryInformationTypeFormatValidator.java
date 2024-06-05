package cdm.product.asset.validation;

import cdm.product.asset.AssetDeliveryInformation;
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

public class AssetDeliveryInformationTypeFormatValidator implements Validator<AssetDeliveryInformation> {

	private List<ComparisonResult> getComparisonResults(AssetDeliveryInformation o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AssetDeliveryInformation> validate(RosettaPath path, AssetDeliveryInformation o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AssetDeliveryInformation", ValidationType.TYPE_FORMAT, "AssetDeliveryInformation", path, "", error);
		}
		return success("AssetDeliveryInformation", ValidationType.TYPE_FORMAT, "AssetDeliveryInformation", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AssetDeliveryInformation o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AssetDeliveryInformation", ValidationType.TYPE_FORMAT, "AssetDeliveryInformation", path, "", res.getError());
				}
				return success("AssetDeliveryInformation", ValidationType.TYPE_FORMAT, "AssetDeliveryInformation", path, "");
			})
			.collect(toList());
	}

}
