package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.Commodity;
import cdm.base.staticdata.asset.common.CommodityProductDefinition;
import cdm.base.staticdata.asset.common.DeliveryDateParameters;
import cdm.observable.asset.QuotationSideEnum;
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

public class CommodityValidator implements Validator<Commodity> {

	private List<ComparisonResult> getComparisonResults(Commodity o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("commodityProductDefinition", (CommodityProductDefinition) o.getCommodityProductDefinition() != null ? 1 : 0, 0, 1), 
				checkCardinality("priceQuoteType", (QuotationSideEnum) o.getPriceQuoteType() != null ? 1 : 0, 1, 1), 
				checkCardinality("deliveryDateReference", (DeliveryDateParameters) o.getDeliveryDateReference() != null ? 1 : 0, 0, 1), 
				checkCardinality("description", (String) o.getDescription() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Commodity> validate(RosettaPath path, Commodity o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Commodity", ValidationType.CARDINALITY, "Commodity", path, "", error);
		}
		return success("Commodity", ValidationType.CARDINALITY, "Commodity", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Commodity o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Commodity", ValidationType.CARDINALITY, "Commodity", path, "", res.getError());
				}
				return success("Commodity", ValidationType.CARDINALITY, "Commodity", path, "");
			})
			.collect(toList());
	}

}
