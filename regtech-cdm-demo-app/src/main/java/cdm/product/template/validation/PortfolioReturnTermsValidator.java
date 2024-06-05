package cdm.product.template.validation;

import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.product.asset.CorrelationReturnTerms;
import cdm.product.asset.DividendReturnTerms;
import cdm.product.asset.PriceReturnTerms;
import cdm.product.asset.VarianceReturnTerms;
import cdm.product.asset.VolatilityReturnTerms;
import cdm.product.template.PortfolioReturnTerms;
import cdm.product.template.Product;
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

public class PortfolioReturnTermsValidator implements Validator<PortfolioReturnTerms> {

	private List<ComparisonResult> getComparisonResults(PortfolioReturnTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("priceReturnTerms", (PriceReturnTerms) o.getPriceReturnTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("dividendReturnTerms", (DividendReturnTerms) o.getDividendReturnTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("varianceReturnTerms", (VarianceReturnTerms) o.getVarianceReturnTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("volatilityReturnTerms", (VolatilityReturnTerms) o.getVolatilityReturnTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("correlationReturnTerms", (CorrelationReturnTerms) o.getCorrelationReturnTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("payerReceiver", (PayerReceiver) o.getPayerReceiver() != null ? 1 : 0, 1, 1), 
				checkCardinality("underlier", (Product) o.getUnderlier() != null ? 1 : 0, 1, 1), 
				checkCardinality("quantity", (ReferenceWithMetaNonNegativeQuantitySchedule) o.getQuantity() != null ? 1 : 0, 0, 1), 
				checkCardinality("initialValuationPrice", (ReferenceWithMetaPriceSchedule) o.getInitialValuationPrice() != null ? 1 : 0, 0, 1), 
				checkCardinality("interimValuationPrice", (ReferenceWithMetaPriceSchedule) o.getInterimValuationPrice() != null ? 1 : 0, 0, 1), 
				checkCardinality("finalValuationPrice", (ReferenceWithMetaPriceSchedule) o.getFinalValuationPrice() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<PortfolioReturnTerms> validate(RosettaPath path, PortfolioReturnTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PortfolioReturnTerms", ValidationType.CARDINALITY, "PortfolioReturnTerms", path, "", error);
		}
		return success("PortfolioReturnTerms", ValidationType.CARDINALITY, "PortfolioReturnTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PortfolioReturnTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PortfolioReturnTerms", ValidationType.CARDINALITY, "PortfolioReturnTerms", path, "", res.getError());
				}
				return success("PortfolioReturnTerms", ValidationType.CARDINALITY, "PortfolioReturnTerms", path, "");
			})
			.collect(toList());
	}

}
