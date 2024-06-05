package cdm.legaldocumentation.master.validation.exists;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.base.staticdata.party.PartyRoleEnum;
import cdm.legaldocumentation.master.MasterAgreementClause;
import cdm.legaldocumentation.master.MasterAgreementClauseIdentifierEnum;
import cdm.legaldocumentation.master.MasterAgreementClauseVariant;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class MasterAgreementClauseOnlyExistsValidator implements ValidatorWithArg<MasterAgreementClause, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends MasterAgreementClause> ValidationResult<MasterAgreementClause> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("identifer", ExistenceChecker.isSet((MasterAgreementClauseIdentifierEnum) o.getIdentifer()))
				.put("name", ExistenceChecker.isSet((String) o.getName()))
				.put("counterparty", ExistenceChecker.isSet((List<CounterpartyRoleEnum>) o.getCounterparty()))
				.put("otherParty", ExistenceChecker.isSet((List<PartyRoleEnum>) o.getOtherParty()))
				.put("variant", ExistenceChecker.isSet((List<? extends MasterAgreementClauseVariant>) o.getVariant()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("MasterAgreementClause", ValidationType.ONLY_EXISTS, "MasterAgreementClause", path, "");
		}
		return failure("MasterAgreementClause", ValidationType.ONLY_EXISTS, "MasterAgreementClause", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
