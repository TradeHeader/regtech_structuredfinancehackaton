package cdm.product.asset.validation;

import cdm.product.asset.AssetDeliveryPeriods;
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

public class AssetDeliveryPeriodsTypeFormatValidator implements Validator<AssetDeliveryPeriods> {

	private List<ComparisonResult> getComparisonResults(AssetDeliveryPeriods o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AssetDeliveryPeriods> validate(RosettaPath path, AssetDeliveryPeriods o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AssetDeliveryPeriods", ValidationType.TYPE_FORMAT, "AssetDeliveryPeriods", path, "", error);
		}
		return success("AssetDeliveryPeriods", ValidationType.TYPE_FORMAT, "AssetDeliveryPeriods", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AssetDeliveryPeriods o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AssetDeliveryPeriods", ValidationType.TYPE_FORMAT, "AssetDeliveryPeriods", path, "", res.getError());
				}
				return success("AssetDeliveryPeriods", ValidationType.TYPE_FORMAT, "AssetDeliveryPeriods", path, "");
			})
			.collect(toList());
	}

}
