package cdm.base.staticdata.party.validation.exists;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.BusinessUnit;
import cdm.base.staticdata.party.ContactInformation;
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

public class BusinessUnitOnlyExistsValidator implements ValidatorWithArg<BusinessUnit, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends BusinessUnit> ValidationResult<BusinessUnit> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("name", ExistenceChecker.isSet((String) o.getName()))
				.put("identifier", ExistenceChecker.isSet((Identifier) o.getIdentifier()))
				.put("contactInformation", ExistenceChecker.isSet((ContactInformation) o.getContactInformation()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("BusinessUnit", ValidationType.ONLY_EXISTS, "BusinessUnit", path, "");
		}
		return failure("BusinessUnit", ValidationType.ONLY_EXISTS, "BusinessUnit", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
