package cdm.legaldocumentation.contract.validation.exists;

import cdm.legaldocumentation.contract.Agreement;
import cdm.legaldocumentation.csa.CollateralTransferAgreementElections;
import cdm.legaldocumentation.csa.CreditSupportAgreementElections;
import cdm.legaldocumentation.csa.SecurityAgreementElections;
import cdm.legaldocumentation.master.MasterAgreementSchedule;
import cdm.legaldocumentation.master.TransactionAdditionalTerms;
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

public class AgreementOnlyExistsValidator implements ValidatorWithArg<Agreement, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Agreement> ValidationResult<Agreement> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("creditSupportAgreementElections", ExistenceChecker.isSet((CreditSupportAgreementElections) o.getCreditSupportAgreementElections()))
				.put("collateralTransferAgreementElections", ExistenceChecker.isSet((CollateralTransferAgreementElections) o.getCollateralTransferAgreementElections()))
				.put("securityAgreementElections", ExistenceChecker.isSet((SecurityAgreementElections) o.getSecurityAgreementElections()))
				.put("masterAgreementSchedule", ExistenceChecker.isSet((MasterAgreementSchedule) o.getMasterAgreementSchedule()))
				.put("transactionAdditionalTerms", ExistenceChecker.isSet((TransactionAdditionalTerms) o.getTransactionAdditionalTerms()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Agreement", ValidationType.ONLY_EXISTS, "Agreement", path, "");
		}
		return failure("Agreement", ValidationType.ONLY_EXISTS, "Agreement", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
