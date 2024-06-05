package cdm.product.template.validation;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.observable.asset.CalculationAgent;
import cdm.product.collateral.Collateral;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
import cdm.product.template.TerminationProvision;
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

public class EconomicTermsValidator implements Validator<EconomicTerms> {

	private List<ComparisonResult> getComparisonResults(EconomicTerms o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("effectiveDate", (AdjustableOrRelativeDate) o.getEffectiveDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("terminationDate", (AdjustableOrRelativeDate) o.getTerminationDate() != null ? 1 : 0, 0, 1), 
				checkCardinality("dateAdjustments", (BusinessDayAdjustments) o.getDateAdjustments() != null ? 1 : 0, 0, 1), 
				checkCardinality("payout", (Payout) o.getPayout() != null ? 1 : 0, 1, 1), 
				checkCardinality("terminationProvision", (TerminationProvision) o.getTerminationProvision() != null ? 1 : 0, 0, 1), 
				checkCardinality("calculationAgent", (CalculationAgent) o.getCalculationAgent() != null ? 1 : 0, 0, 1), 
				checkCardinality("nonStandardisedTerms", (Boolean) o.getNonStandardisedTerms() != null ? 1 : 0, 0, 1), 
				checkCardinality("collateral", (Collateral) o.getCollateral() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<EconomicTerms> validate(RosettaPath path, EconomicTerms o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EconomicTerms", ValidationType.CARDINALITY, "EconomicTerms", path, "", error);
		}
		return success("EconomicTerms", ValidationType.CARDINALITY, "EconomicTerms", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EconomicTerms o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EconomicTerms", ValidationType.CARDINALITY, "EconomicTerms", path, "", res.getError());
				}
				return success("EconomicTerms", ValidationType.CARDINALITY, "EconomicTerms", path, "");
			})
			.collect(toList());
	}

}
