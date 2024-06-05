package cdm.product.asset.validation;

import cdm.base.datetime.DayOfWeekEnum;
import cdm.base.math.Quantity;
import cdm.observable.asset.Price;
import cdm.product.asset.AssetDeliveryProfileBlock;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.time.LocalTime;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AssetDeliveryProfileBlockValidator implements Validator<AssetDeliveryProfileBlock> {

	private List<ComparisonResult> getComparisonResults(AssetDeliveryProfileBlock o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("startTime", (LocalTime) o.getStartTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("endTime", (LocalTime) o.getEndTime() != null ? 1 : 0, 0, 1), 
				checkCardinality("dayOfWeek", (List<DayOfWeekEnum>) o.getDayOfWeek() == null ? 0 : ((List<DayOfWeekEnum>) o.getDayOfWeek()).size(), 0, 7), 
				checkCardinality("deliveryCapacity", (Quantity) o.getDeliveryCapacity() != null ? 1 : 0, 0, 1), 
				checkCardinality("priceTimeIntervalQuantity", (Price) o.getPriceTimeIntervalQuantity() != null ? 1 : 0, 0, 1)
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
			return failure("AssetDeliveryProfileBlock", ValidationType.CARDINALITY, "AssetDeliveryProfileBlock", path, "", error);
		}
		return success("AssetDeliveryProfileBlock", ValidationType.CARDINALITY, "AssetDeliveryProfileBlock", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AssetDeliveryProfileBlock o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AssetDeliveryProfileBlock", ValidationType.CARDINALITY, "AssetDeliveryProfileBlock", path, "", res.getError());
				}
				return success("AssetDeliveryProfileBlock", ValidationType.CARDINALITY, "AssetDeliveryProfileBlock", path, "");
			})
			.collect(toList());
	}

}
