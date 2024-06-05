package cdm.base.staticdata.asset.common.validation;

import cdm.base.math.CapacityUnitEnum;
import cdm.base.math.WeatherUnitEnum;
import cdm.base.staticdata.asset.common.CommodityReferenceFramework;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CommodityReferenceFrameworkValidator implements Validator<CommodityReferenceFramework> {

	private List<ComparisonResult> getComparisonResults(CommodityReferenceFramework o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("commodityName", (String) o.getCommodityName() != null ? 1 : 0, 1, 1), 
				checkCardinality("capacityUnit", (CapacityUnitEnum) o.getCapacityUnit() != null ? 1 : 0, 0, 1), 
				checkCardinality("weatherUnit", (WeatherUnitEnum) o.getWeatherUnit() != null ? 1 : 0, 0, 1), 
				checkCardinality("currency", (FieldWithMetaString) o.getCurrency() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<CommodityReferenceFramework> validate(RosettaPath path, CommodityReferenceFramework o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CommodityReferenceFramework", ValidationType.CARDINALITY, "CommodityReferenceFramework", path, "", error);
		}
		return success("CommodityReferenceFramework", ValidationType.CARDINALITY, "CommodityReferenceFramework", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CommodityReferenceFramework o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CommodityReferenceFramework", ValidationType.CARDINALITY, "CommodityReferenceFramework", path, "", res.getError());
				}
				return success("CommodityReferenceFramework", ValidationType.CARDINALITY, "CommodityReferenceFramework", path, "");
			})
			.collect(toList());
	}

}
