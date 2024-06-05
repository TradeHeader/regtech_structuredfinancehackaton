package cdm.base.staticdata.party.validation.exists;

import cdm.base.staticdata.party.Account;
import cdm.base.staticdata.party.BusinessUnit;
import cdm.base.staticdata.party.ContactInformation;
import cdm.base.staticdata.party.NaturalPerson;
import cdm.base.staticdata.party.NaturalPersonRole;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyIdentifier;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class PartyOnlyExistsValidator implements ValidatorWithArg<Party, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Party> ValidationResult<Party> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("partyId", ExistenceChecker.isSet((List<? extends PartyIdentifier>) o.getPartyId()))
				.put("name", ExistenceChecker.isSet((FieldWithMetaString) o.getName()))
				.put("businessUnit", ExistenceChecker.isSet((List<? extends BusinessUnit>) o.getBusinessUnit()))
				.put("person", ExistenceChecker.isSet((List<? extends NaturalPerson>) o.getPerson()))
				.put("personRole", ExistenceChecker.isSet((List<? extends NaturalPersonRole>) o.getPersonRole()))
				.put("account", ExistenceChecker.isSet((Account) o.getAccount()))
				.put("contactInformation", ExistenceChecker.isSet((ContactInformation) o.getContactInformation()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Party", ValidationType.ONLY_EXISTS, "Party", path, "");
		}
		return failure("Party", ValidationType.ONLY_EXISTS, "Party", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
