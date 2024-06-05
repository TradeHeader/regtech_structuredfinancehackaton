package cdm.product.asset.validation;

import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.product.asset.FPVFinalPriceElectionFallbackEnum;
import cdm.product.asset.ValuationTerms;
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

public class ValuationTermsValidator implements Validator<ValuationTerms> {

	private List<ComparisonResult> getComparisonResults(ValuationTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("futuresPriceValuation", (Boolean) o.getFuturesPriceValuation() != null ? 1 : 0, 0, 1), 
				checkCardinality("optionsPriceValuation", (Boolean) o.getOptionsPriceValuation() != null ? 1 : 0, 0, 1), 
				checkCardinality("numberOfValuationDates", (Integer) o.getNumberOfValuationDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("dividendValuationDates", (AdjustableRelativeOrPeriodicDates) o.getDividendValuationDates() != null ? 1 : 0, 0, 1), 
				checkCardinality("fPVFinalPriceElectionFallback", (FPVFinalPriceElectionFallbackEnum) o.getFPVFinalPriceElectionFallback() != null ? 1 : 0, 0, 1), 
				checkCardinality("multipleExchangeIndexAnnexFallback", (Boolean) o.getMultipleExchangeIndexAnnexFallback() != null ? 1 : 0, 0, 1), 
				checkCardinality("componentSecurityIndexAnnexFallback", (Boolean) o.getComponentSecurityIndexAnnexFallback() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<ValuationTerms> validate(RosettaPath path, ValuationTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("ValuationTerms", ValidationType.CARDINALITY, "ValuationTerms", path, "", error);
		}
		return success("ValuationTerms", ValidationType.CARDINALITY, "ValuationTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, ValuationTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("ValuationTerms", ValidationType.CARDINALITY, "ValuationTerms", path, "", res.getError());
				}
				return success("ValuationTerms", ValidationType.CARDINALITY, "ValuationTerms", path, "");
			})
			.collect(toList());
	}

}
