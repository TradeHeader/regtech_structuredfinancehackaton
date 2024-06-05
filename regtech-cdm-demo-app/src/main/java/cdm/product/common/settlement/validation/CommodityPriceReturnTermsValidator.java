package cdm.product.common.settlement.validation;

import cdm.base.math.Rounding;
import cdm.product.asset.SpreadSchedule;
import cdm.product.common.settlement.CommodityPriceReturnTerms;
import cdm.product.common.settlement.RollFeature;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CommodityPriceReturnTermsValidator implements Validator<CommodityPriceReturnTerms> {

	private List<ComparisonResult> getComparisonResults(CommodityPriceReturnTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("rounding", (Rounding) o.getRounding() != null ? 1 : 0, 0, 1), 
				checkCardinality("spread", (SpreadSchedule) o.getSpread() != null ? 1 : 0, 0, 1), 
				checkCardinality("rollFeature", (RollFeature) o.getRollFeature() != null ? 1 : 0, 0, 1), 
				checkCardinality("conversionFactor", (BigDecimal) o.getConversionFactor() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<CommodityPriceReturnTerms> validate(RosettaPath path, CommodityPriceReturnTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CommodityPriceReturnTerms", ValidationType.CARDINALITY, "CommodityPriceReturnTerms", path, "", error);
		}
		return success("CommodityPriceReturnTerms", ValidationType.CARDINALITY, "CommodityPriceReturnTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CommodityPriceReturnTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CommodityPriceReturnTerms", ValidationType.CARDINALITY, "CommodityPriceReturnTerms", path, "", res.getError());
				}
				return success("CommodityPriceReturnTerms", ValidationType.CARDINALITY, "CommodityPriceReturnTerms", path, "");
			})
			.collect(toList());
	}

}
