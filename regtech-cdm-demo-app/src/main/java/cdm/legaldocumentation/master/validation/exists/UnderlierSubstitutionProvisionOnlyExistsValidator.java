package cdm.legaldocumentation.master.validation.exists;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.legaldocumentation.master.Clause;
import cdm.legaldocumentation.master.ExtraordinaryEvents;
import cdm.legaldocumentation.master.UnderlierSubstitutionProvision;
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

public class UnderlierSubstitutionProvisionOnlyExistsValidator implements ValidatorWithArg<UnderlierSubstitutionProvision, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends UnderlierSubstitutionProvision> ValidationResult<UnderlierSubstitutionProvision> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("whoMaySubstitute", ExistenceChecker.isSet((List<CounterpartyRoleEnum>) o.getWhoMaySubstitute()))
				.put("substitutionBeSpokeTerms", ExistenceChecker.isSet((List<? extends Clause>) o.getSubstitutionBeSpokeTerms()))
				.put("substitutionTriggerEvents", ExistenceChecker.isSet((List<? extends ExtraordinaryEvents>) o.getSubstitutionTriggerEvents()))
				.put("disputingParty", ExistenceChecker.isSet((CounterpartyRoleEnum) o.getDisputingParty()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("UnderlierSubstitutionProvision", ValidationType.ONLY_EXISTS, "UnderlierSubstitutionProvision", path, "");
		}
		return failure("UnderlierSubstitutionProvision", ValidationType.ONLY_EXISTS, "UnderlierSubstitutionProvision", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
