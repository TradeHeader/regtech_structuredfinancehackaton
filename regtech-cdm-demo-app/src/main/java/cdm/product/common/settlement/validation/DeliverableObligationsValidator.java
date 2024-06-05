package cdm.product.common.settlement.validation;

import cdm.base.datetime.Period;
import cdm.base.staticdata.asset.credit.NotDomesticCurrency;
import cdm.base.staticdata.asset.credit.ObligationCategoryEnum;
import cdm.base.staticdata.asset.credit.SpecifiedCurrency;
import cdm.product.common.settlement.DeliverableObligations;
import cdm.product.common.settlement.LoanParticipation;
import cdm.product.common.settlement.PCDeliverableObligationCharac;
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

public class DeliverableObligationsValidator implements Validator<DeliverableObligations> {

	private List<ComparisonResult> getComparisonResults(DeliverableObligations o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("accruedInterest", (Boolean) o.getAccruedInterest() != null ? 1 : 0, 0, 1), 
				checkCardinality("category", (ObligationCategoryEnum) o.getCategory() != null ? 1 : 0, 0, 1), 
				checkCardinality("notSubordinated", (Boolean) o.getNotSubordinated() != null ? 1 : 0, 0, 1), 
				checkCardinality("specifiedCurrency", (SpecifiedCurrency) o.getSpecifiedCurrency() != null ? 1 : 0, 0, 1), 
				checkCardinality("notSovereignLender", (Boolean) o.getNotSovereignLender() != null ? 1 : 0, 0, 1), 
				checkCardinality("notDomesticCurrency", (NotDomesticCurrency) o.getNotDomesticCurrency() != null ? 1 : 0, 0, 1), 
				checkCardinality("notDomesticLaw", (Boolean) o.getNotDomesticLaw() != null ? 1 : 0, 0, 1), 
				checkCardinality("listed", (Boolean) o.getListed() != null ? 1 : 0, 0, 1), 
				checkCardinality("notContingent", (Boolean) o.getNotContingent() != null ? 1 : 0, 0, 1), 
				checkCardinality("notDomesticIssuance", (Boolean) o.getNotDomesticIssuance() != null ? 1 : 0, 0, 1), 
				checkCardinality("assignableLoan", (PCDeliverableObligationCharac) o.getAssignableLoan() != null ? 1 : 0, 0, 1), 
				checkCardinality("consentRequiredLoan", (PCDeliverableObligationCharac) o.getConsentRequiredLoan() != null ? 1 : 0, 0, 1), 
				checkCardinality("directLoanParticipation", (LoanParticipation) o.getDirectLoanParticipation() != null ? 1 : 0, 0, 1), 
				checkCardinality("transferable", (Boolean) o.getTransferable() != null ? 1 : 0, 0, 1), 
				checkCardinality("maximumMaturity", (Period) o.getMaximumMaturity() != null ? 1 : 0, 0, 1), 
				checkCardinality("acceleratedOrMatured", (Boolean) o.getAcceleratedOrMatured() != null ? 1 : 0, 0, 1), 
				checkCardinality("notBearer", (Boolean) o.getNotBearer() != null ? 1 : 0, 0, 1), 
				checkCardinality("fullFaithAndCreditObLiability", (Boolean) o.getFullFaithAndCreditObLiability() != null ? 1 : 0, 0, 1), 
				checkCardinality("generalFundObligationLiability", (Boolean) o.getGeneralFundObligationLiability() != null ? 1 : 0, 0, 1), 
				checkCardinality("revenueObligationLiability", (Boolean) o.getRevenueObligationLiability() != null ? 1 : 0, 0, 1), 
				checkCardinality("indirectLoanParticipation", (LoanParticipation) o.getIndirectLoanParticipation() != null ? 1 : 0, 0, 1), 
				checkCardinality("excluded", (String) o.getExcluded() != null ? 1 : 0, 0, 1), 
				checkCardinality("othReferenceEntityObligations", (String) o.getOthReferenceEntityObligations() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<DeliverableObligations> validate(RosettaPath path, DeliverableObligations o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("DeliverableObligations", ValidationType.CARDINALITY, "DeliverableObligations", path, "", error);
		}
		return success("DeliverableObligations", ValidationType.CARDINALITY, "DeliverableObligations", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, DeliverableObligations o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("DeliverableObligations", ValidationType.CARDINALITY, "DeliverableObligations", path, "", res.getError());
				}
				return success("DeliverableObligations", ValidationType.CARDINALITY, "DeliverableObligations", path, "");
			})
			.collect(toList());
	}

}
