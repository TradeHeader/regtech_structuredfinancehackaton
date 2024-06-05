package cdm.base.staticdata.party.validation.exists;

import cdm.base.staticdata.party.Address;
import cdm.base.staticdata.party.ContactInformation;
import cdm.base.staticdata.party.TelephoneNumber;
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

public class ContactInformationOnlyExistsValidator implements ValidatorWithArg<ContactInformation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ContactInformation> ValidationResult<ContactInformation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("telephone", ExistenceChecker.isSet((List<? extends TelephoneNumber>) o.getTelephone()))
				.put("address", ExistenceChecker.isSet((List<? extends Address>) o.getAddress()))
				.put("email", ExistenceChecker.isSet((List<String>) o.getEmail()))
				.put("webPage", ExistenceChecker.isSet((List<String>) o.getWebPage()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ContactInformation", ValidationType.ONLY_EXISTS, "ContactInformation", path, "");
		}
		return failure("ContactInformation", ValidationType.ONLY_EXISTS, "ContactInformation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
