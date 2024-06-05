package cdm.event.common.validation.exists;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.Confirmation;
import cdm.event.common.ConfirmationStatusEnum;
import cdm.event.common.Lineage;
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

public class ConfirmationOnlyExistsValidator implements ValidatorWithArg<Confirmation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Confirmation> ValidationResult<Confirmation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("identifier", ExistenceChecker.isSet((List<? extends Identifier>) o.getIdentifier()))
				.put("party", ExistenceChecker.isSet((List<? extends Party>) o.getParty()))
				.put("partyRole", ExistenceChecker.isSet((List<? extends PartyRole>) o.getPartyRole()))
				.put("lineage", ExistenceChecker.isSet((Lineage) o.getLineage()))
				.put("status", ExistenceChecker.isSet((ConfirmationStatusEnum) o.getStatus()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Confirmation", ValidationType.ONLY_EXISTS, "Confirmation", path, "");
		}
		return failure("Confirmation", ValidationType.ONLY_EXISTS, "Confirmation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
