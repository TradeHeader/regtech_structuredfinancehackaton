package cdm.product.collateral.validation;

import cdm.base.datetime.PeriodRange;
import cdm.base.staticdata.asset.common.MaturityTypeEnum;
import cdm.product.collateral.AssetCriteria;
import cdm.product.collateral.ListingType;
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

public class AssetCriteriaValidator implements Validator<AssetCriteria> {

	private List<ComparisonResult> getComparisonResults(AssetCriteria o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("maturityType", (MaturityTypeEnum) o.getMaturityType() != null ? 1 : 0, 0, 1), 
				checkCardinality("maturityRange", (PeriodRange) o.getMaturityRange() != null ? 1 : 0, 0, 1), 
				checkCardinality("domesticCurrencyIssued", (Boolean) o.getDomesticCurrencyIssued() != null ? 1 : 0, 0, 1), 
				checkCardinality("listing", (ListingType) o.getListing() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AssetCriteria> validate(RosettaPath path, AssetCriteria o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AssetCriteria", ValidationType.CARDINALITY, "AssetCriteria", path, "", error);
		}
		return success("AssetCriteria", ValidationType.CARDINALITY, "AssetCriteria", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AssetCriteria o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AssetCriteria", ValidationType.CARDINALITY, "AssetCriteria", path, "", res.getError());
				}
				return success("AssetCriteria", ValidationType.CARDINALITY, "AssetCriteria", path, "");
			})
			.collect(toList());
	}

}
