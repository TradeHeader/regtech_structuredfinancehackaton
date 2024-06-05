package cdm.product.asset.validation;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.observable.asset.DividendApplicability;
import cdm.observable.asset.Observable;
import cdm.observable.asset.Price;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.product.asset.EquityUnderlierProvisions;
import cdm.product.asset.ValuationTerms;
import cdm.product.asset.VarianceCapFloor;
import cdm.product.asset.VarianceReturnTerms;
import cdm.product.asset.VolatilityCapFloor;
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

public class VarianceReturnTermsValidator implements Validator<VarianceReturnTerms> {

	private List<ComparisonResult> getComparisonResults(VarianceReturnTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("valuationTerms", (ValuationTerms) o.getValuationTerms() != null ? 1 : 0, 1, 1), 
				checkCardinality("annualizationFactor", (Integer) o.getAnnualizationFactor() != null ? 1 : 0, 0, 1), 
				checkCardinality("dividendApplicability", (DividendApplicability) o.getDividendApplicability() != null ? 1 : 0, 0, 1), 
				checkCardinality("equityUnderlierProvisions", (EquityUnderlierProvisions) o.getEquityUnderlierProvisions() != null ? 1 : 0, 0, 1), 
				checkCardinality("sharePriceDividendAdjustment", (Boolean) o.getSharePriceDividendAdjustment() != null ? 1 : 0, 0, 1), 
				checkCardinality("expectedN", (Integer) o.getExpectedN() != null ? 1 : 0, 1, 1), 
				checkCardinality("initialLevel", (BigDecimal) o.getInitialLevel() != null ? 1 : 0, 0, 1), 
				checkCardinality("initialLevelSource", (DeterminationMethodEnum) o.getInitialLevelSource() != null ? 1 : 0, 0, 1), 
				checkCardinality("meanAdjustment", (Boolean) o.getMeanAdjustment() != null ? 1 : 0, 0, 1), 
				checkCardinality("performance", (String) o.getPerformance() != null ? 1 : 0, 0, 1), 
				checkCardinality("varianceStrikePrice", (Price) o.getVarianceStrikePrice() != null ? 1 : 0, 0, 1), 
				checkCardinality("volatilityStrikePrice", (Price) o.getVolatilityStrikePrice() != null ? 1 : 0, 0, 1), 
				checkCardinality("varianceCapFloor", (VarianceCapFloor) o.getVarianceCapFloor() != null ? 1 : 0, 0, 1), 
				checkCardinality("volatilityCapFloor", (VolatilityCapFloor) o.getVolatilityCapFloor() != null ? 1 : 0, 0, 1), 
				checkCardinality("vegaNotionalAmount", (NonNegativeQuantitySchedule) o.getVegaNotionalAmount() != null ? 1 : 0, 0, 1), 
				checkCardinality("exchangeTradedContractNearest", (Observable) o.getExchangeTradedContractNearest() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<VarianceReturnTerms> validate(RosettaPath path, VarianceReturnTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("VarianceReturnTerms", ValidationType.CARDINALITY, "VarianceReturnTerms", path, "", error);
		}
		return success("VarianceReturnTerms", ValidationType.CARDINALITY, "VarianceReturnTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, VarianceReturnTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("VarianceReturnTerms", ValidationType.CARDINALITY, "VarianceReturnTerms", path, "", res.getError());
				}
				return success("VarianceReturnTerms", ValidationType.CARDINALITY, "VarianceReturnTerms", path, "");
			})
			.collect(toList());
	}

}
