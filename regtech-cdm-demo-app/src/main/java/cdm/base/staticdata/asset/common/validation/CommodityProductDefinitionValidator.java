package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.CommodityInformationPublisherEnum;
import cdm.base.staticdata.asset.common.CommodityProductDefinition;
import cdm.base.staticdata.asset.common.CommodityReferenceFramework;
import cdm.base.staticdata.asset.common.PriceSource;
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

public class CommodityProductDefinitionValidator implements Validator<CommodityProductDefinition> {

	private List<ComparisonResult> getComparisonResults(CommodityProductDefinition o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("referenceFramework", (CommodityReferenceFramework) o.getReferenceFramework() != null ? 1 : 0, 1, 1), 
				checkCardinality("priceSource", (PriceSource) o.getPriceSource() != null ? 1 : 0, 0, 1), 
				checkCardinality("commodityInfoPublisher", (CommodityInformationPublisherEnum) o.getCommodityInfoPublisher() != null ? 1 : 0, 0, 1), 
				checkCardinality("exchangeId", (FieldWithMetaString) o.getExchangeId() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<CommodityProductDefinition> validate(RosettaPath path, CommodityProductDefinition o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CommodityProductDefinition", ValidationType.CARDINALITY, "CommodityProductDefinition", path, "", error);
		}
		return success("CommodityProductDefinition", ValidationType.CARDINALITY, "CommodityProductDefinition", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CommodityProductDefinition o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CommodityProductDefinition", ValidationType.CARDINALITY, "CommodityProductDefinition", path, "", res.getError());
				}
				return success("CommodityProductDefinition", ValidationType.CARDINALITY, "CommodityProductDefinition", path, "");
			})
			.collect(toList());
	}

}
