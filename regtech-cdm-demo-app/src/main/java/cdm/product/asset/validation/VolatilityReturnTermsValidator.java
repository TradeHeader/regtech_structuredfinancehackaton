package cdm.product.asset.validation;

import cdm.observable.asset.DividendApplicability;
import cdm.observable.asset.Observable;
import cdm.observable.asset.Price;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.product.asset.EquityUnderlierProvisions;
import cdm.product.asset.ValuationTerms;
import cdm.product.asset.VolatilityCapFloor;
import cdm.product.asset.VolatilityReturnTerms;
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

public class VolatilityReturnTermsValidator implements Validator<VolatilityReturnTerms> {

	private List<ComparisonResult> getComparisonResults(VolatilityReturnTerms o) {
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
				checkCardinality("volatilityStrikePrice", (Price) o.getVolatilityStrikePrice() != null ? 1 : 0, 1, 1), 
				checkCardinality("volatilityCapFloor", (VolatilityCapFloor) o.getVolatilityCapFloor() != null ? 1 : 0, 0, 1), 
				checkCardinality("exchangeTradedContractNearest", (Observable) o.getExchangeTradedContractNearest() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<VolatilityReturnTerms> validate(RosettaPath path, VolatilityReturnTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("VolatilityReturnTerms", ValidationType.CARDINALITY, "VolatilityReturnTerms", path, "", error);
		}
		return success("VolatilityReturnTerms", ValidationType.CARDINALITY, "VolatilityReturnTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, VolatilityReturnTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("VolatilityReturnTerms", ValidationType.CARDINALITY, "VolatilityReturnTerms", path, "", res.getError());
				}
				return success("VolatilityReturnTerms", ValidationType.CARDINALITY, "VolatilityReturnTerms", path, "");
			})
			.collect(toList());
	}

}
