package cdm.event.common.validation.exists;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.PartyChangeInstruction;
import cdm.event.common.TradeIdentifier;
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

public class PartyChangeInstructionOnlyExistsValidator implements ValidatorWithArg<PartyChangeInstruction, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PartyChangeInstruction> ValidationResult<PartyChangeInstruction> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("counterparty", ExistenceChecker.isSet((Counterparty) o.getCounterparty()))
				.put("ancillaryParty", ExistenceChecker.isSet((AncillaryParty) o.getAncillaryParty()))
				.put("partyRole", ExistenceChecker.isSet((PartyRole) o.getPartyRole()))
				.put("tradeId", ExistenceChecker.isSet((List<? extends TradeIdentifier>) o.getTradeId()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PartyChangeInstruction", ValidationType.ONLY_EXISTS, "PartyChangeInstruction", path, "");
		}
		return failure("PartyChangeInstruction", ValidationType.ONLY_EXISTS, "PartyChangeInstruction", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
