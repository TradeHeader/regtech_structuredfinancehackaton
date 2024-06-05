package cdm.event.position.validation.exists;

import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.position.AvailableInventory;
import cdm.event.position.AvailableInventoryRecord;
import cdm.event.workflow.MessageInformation;
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

public class AvailableInventoryOnlyExistsValidator implements ValidatorWithArg<AvailableInventory, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AvailableInventory> ValidationResult<AvailableInventory> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("messageInformation", ExistenceChecker.isSet((MessageInformation) o.getMessageInformation()))
				.put("party", ExistenceChecker.isSet((List<? extends Party>) o.getParty()))
				.put("partyRole", ExistenceChecker.isSet((List<? extends PartyRole>) o.getPartyRole()))
				.put("availableInventoryRecord", ExistenceChecker.isSet((List<? extends AvailableInventoryRecord>) o.getAvailableInventoryRecord()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AvailableInventory", ValidationType.ONLY_EXISTS, "AvailableInventory", path, "");
		}
		return failure("AvailableInventory", ValidationType.ONLY_EXISTS, "AvailableInventory", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
