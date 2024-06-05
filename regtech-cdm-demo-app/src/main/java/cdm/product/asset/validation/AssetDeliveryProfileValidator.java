package cdm.product.asset.validation;

import cdm.product.asset.AssetDeliveryProfile;
import cdm.product.asset.BankHolidayTreatmentEnum;
import cdm.product.asset.LoadTypeEnum;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AssetDeliveryProfileValidator implements Validator<AssetDeliveryProfile> {

	private List<ComparisonResult> getComparisonResults(AssetDeliveryProfile o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("loadType", (LoadTypeEnum) o.getLoadType() != null ? 1 : 0, 0, 1), 
				checkCardinality("bankHolidaysTreatment", (BankHolidayTreatmentEnum) o.getBankHolidaysTreatment() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AssetDeliveryProfile> validate(RosettaPath path, AssetDeliveryProfile o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AssetDeliveryProfile", ValidationType.CARDINALITY, "AssetDeliveryProfile", path, "", error);
		}
		return success("AssetDeliveryProfile", ValidationType.CARDINALITY, "AssetDeliveryProfile", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AssetDeliveryProfile o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AssetDeliveryProfile", ValidationType.CARDINALITY, "AssetDeliveryProfile", path, "", res.getError());
				}
				return success("AssetDeliveryProfile", ValidationType.CARDINALITY, "AssetDeliveryProfile", path, "");
			})
			.collect(toList());
	}

}
