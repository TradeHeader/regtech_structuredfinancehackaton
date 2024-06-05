package cdm.base.staticdata.party.validation.exists;

import cdm.base.staticdata.party.ContactInformation;
import cdm.base.staticdata.party.NaturalPerson;
import cdm.base.staticdata.party.NaturalPersonRole;
import cdm.base.staticdata.party.metafields.FieldWithMetaPersonIdentifier;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class NaturalPersonOnlyExistsValidator implements ValidatorWithArg<NaturalPerson, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends NaturalPerson> ValidationResult<NaturalPerson> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("personId", ExistenceChecker.isSet((List<? extends FieldWithMetaPersonIdentifier>) o.getPersonId()))
				.put("honorific", ExistenceChecker.isSet((String) o.getHonorific()))
				.put("firstName", ExistenceChecker.isSet((String) o.getFirstName()))
				.put("middleName", ExistenceChecker.isSet((List<String>) o.getMiddleName()))
				.put("initial", ExistenceChecker.isSet((List<String>) o.getInitial()))
				.put("surname", ExistenceChecker.isSet((String) o.getSurname()))
				.put("suffix", ExistenceChecker.isSet((String) o.getSuffix()))
				.put("dateOfBirth", ExistenceChecker.isSet((Date) o.getDateOfBirth()))
				.put("contactInformation", ExistenceChecker.isSet((ContactInformation) o.getContactInformation()))
				.put("personRole", ExistenceChecker.isSet((List<? extends NaturalPersonRole>) o.getPersonRole()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("NaturalPerson", ValidationType.ONLY_EXISTS, "NaturalPerson", path, "");
		}
		return failure("NaturalPerson", ValidationType.ONLY_EXISTS, "NaturalPerson", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
