package cdm.base.staticdata.party.validation.exists;

import cdm.base.staticdata.party.Account;
import cdm.base.staticdata.party.metafields.FieldWithMetaAccountTypeEnum;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class AccountOnlyExistsValidator implements ValidatorWithArg<Account, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Account> ValidationResult<Account> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("partyReference", ExistenceChecker.isSet((ReferenceWithMetaParty) o.getPartyReference()))
				.put("accountNumber", ExistenceChecker.isSet((FieldWithMetaString) o.getAccountNumber()))
				.put("accountName", ExistenceChecker.isSet((FieldWithMetaString) o.getAccountName()))
				.put("accountType", ExistenceChecker.isSet((FieldWithMetaAccountTypeEnum) o.getAccountType()))
				.put("accountBeneficiary", ExistenceChecker.isSet((ReferenceWithMetaParty) o.getAccountBeneficiary()))
				.put("servicingParty", ExistenceChecker.isSet((ReferenceWithMetaParty) o.getServicingParty()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Account", ValidationType.ONLY_EXISTS, "Account", path, "");
		}
		return failure("Account", ValidationType.ONLY_EXISTS, "Account", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
