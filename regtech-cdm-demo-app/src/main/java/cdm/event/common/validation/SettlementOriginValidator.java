package cdm.event.common.validation;

import cdm.event.common.SettlementOrigin;
import cdm.product.asset.metafields.ReferenceWithMetaCommodityPayout;
import cdm.product.asset.metafields.ReferenceWithMetaCreditDefaultPayout;
import cdm.product.asset.metafields.ReferenceWithMetaInterestRatePayout;
import cdm.product.common.settlement.metafields.ReferenceWithMetaSettlementTerms;
import cdm.product.template.metafields.ReferenceWithMetaAssetPayout;
import cdm.product.template.metafields.ReferenceWithMetaFixedPricePayout;
import cdm.product.template.metafields.ReferenceWithMetaForwardPayout;
import cdm.product.template.metafields.ReferenceWithMetaOptionPayout;
import cdm.product.template.metafields.ReferenceWithMetaPerformancePayout;
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

public class SettlementOriginValidator implements Validator<SettlementOrigin> {

	private List<ComparisonResult> getComparisonResults(SettlementOrigin o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("commodityPayout", (ReferenceWithMetaCommodityPayout) o.getCommodityPayout() != null ? 1 : 0, 0, 1), 
				checkCardinality("creditDefaultPayout", (ReferenceWithMetaCreditDefaultPayout) o.getCreditDefaultPayout() != null ? 1 : 0, 0, 1), 
				checkCardinality("forwardPayout", (ReferenceWithMetaForwardPayout) o.getForwardPayout() != null ? 1 : 0, 0, 1), 
				checkCardinality("interestRatePayout", (ReferenceWithMetaInterestRatePayout) o.getInterestRatePayout() != null ? 1 : 0, 0, 1), 
				checkCardinality("optionPayout", (ReferenceWithMetaOptionPayout) o.getOptionPayout() != null ? 1 : 0, 0, 1), 
				checkCardinality("assetPayout", (ReferenceWithMetaAssetPayout) o.getAssetPayout() != null ? 1 : 0, 0, 1), 
				checkCardinality("settlementTerms", (ReferenceWithMetaSettlementTerms) o.getSettlementTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("performancePayout", (ReferenceWithMetaPerformancePayout) o.getPerformancePayout() != null ? 1 : 0, 0, 1), 
				checkCardinality("fixedPricePayout", (ReferenceWithMetaFixedPricePayout) o.getFixedPricePayout() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<SettlementOrigin> validate(RosettaPath path, SettlementOrigin o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SettlementOrigin", ValidationType.CARDINALITY, "SettlementOrigin", path, "", error);
		}
		return success("SettlementOrigin", ValidationType.CARDINALITY, "SettlementOrigin", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SettlementOrigin o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SettlementOrigin", ValidationType.CARDINALITY, "SettlementOrigin", path, "", res.getError());
				}
				return success("SettlementOrigin", ValidationType.CARDINALITY, "SettlementOrigin", path, "");
			})
			.collect(toList());
	}

}
