package cdm.product.common.settlement.validation.exists;

import cdm.base.datetime.Period;
import cdm.base.staticdata.asset.credit.NotDomesticCurrency;
import cdm.base.staticdata.asset.credit.ObligationCategoryEnum;
import cdm.base.staticdata.asset.credit.SpecifiedCurrency;
import cdm.product.common.settlement.DeliverableObligations;
import cdm.product.common.settlement.LoanParticipation;
import cdm.product.common.settlement.PCDeliverableObligationCharac;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class DeliverableObligationsOnlyExistsValidator implements ValidatorWithArg<DeliverableObligations, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DeliverableObligations> ValidationResult<DeliverableObligations> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("accruedInterest", ExistenceChecker.isSet((Boolean) o.getAccruedInterest()))
				.put("category", ExistenceChecker.isSet((ObligationCategoryEnum) o.getCategory()))
				.put("notSubordinated", ExistenceChecker.isSet((Boolean) o.getNotSubordinated()))
				.put("specifiedCurrency", ExistenceChecker.isSet((SpecifiedCurrency) o.getSpecifiedCurrency()))
				.put("notSovereignLender", ExistenceChecker.isSet((Boolean) o.getNotSovereignLender()))
				.put("notDomesticCurrency", ExistenceChecker.isSet((NotDomesticCurrency) o.getNotDomesticCurrency()))
				.put("notDomesticLaw", ExistenceChecker.isSet((Boolean) o.getNotDomesticLaw()))
				.put("listed", ExistenceChecker.isSet((Boolean) o.getListed()))
				.put("notContingent", ExistenceChecker.isSet((Boolean) o.getNotContingent()))
				.put("notDomesticIssuance", ExistenceChecker.isSet((Boolean) o.getNotDomesticIssuance()))
				.put("assignableLoan", ExistenceChecker.isSet((PCDeliverableObligationCharac) o.getAssignableLoan()))
				.put("consentRequiredLoan", ExistenceChecker.isSet((PCDeliverableObligationCharac) o.getConsentRequiredLoan()))
				.put("directLoanParticipation", ExistenceChecker.isSet((LoanParticipation) o.getDirectLoanParticipation()))
				.put("transferable", ExistenceChecker.isSet((Boolean) o.getTransferable()))
				.put("maximumMaturity", ExistenceChecker.isSet((Period) o.getMaximumMaturity()))
				.put("acceleratedOrMatured", ExistenceChecker.isSet((Boolean) o.getAcceleratedOrMatured()))
				.put("notBearer", ExistenceChecker.isSet((Boolean) o.getNotBearer()))
				.put("fullFaithAndCreditObLiability", ExistenceChecker.isSet((Boolean) o.getFullFaithAndCreditObLiability()))
				.put("generalFundObligationLiability", ExistenceChecker.isSet((Boolean) o.getGeneralFundObligationLiability()))
				.put("revenueObligationLiability", ExistenceChecker.isSet((Boolean) o.getRevenueObligationLiability()))
				.put("indirectLoanParticipation", ExistenceChecker.isSet((LoanParticipation) o.getIndirectLoanParticipation()))
				.put("excluded", ExistenceChecker.isSet((String) o.getExcluded()))
				.put("othReferenceEntityObligations", ExistenceChecker.isSet((String) o.getOthReferenceEntityObligations()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DeliverableObligations", ValidationType.ONLY_EXISTS, "DeliverableObligations", path, "");
		}
		return failure("DeliverableObligations", ValidationType.ONLY_EXISTS, "DeliverableObligations", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
