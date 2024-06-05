package cdm.base.staticdata.party.validation.exists;

import cdm.base.staticdata.party.TelephoneNumber;
import cdm.base.staticdata.party.TelephoneTypeEnum;
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

public class TelephoneNumberOnlyExistsValidator implements ValidatorWithArg<TelephoneNumber, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends TelephoneNumber> ValidationResult<TelephoneNumber> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("telephoneNumberType", ExistenceChecker.isSet((TelephoneTypeEnum) o.getTelephoneNumberType()))
				.put("number", ExistenceChecker.isSet((String) o.getNumber()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("TelephoneNumber", ValidationType.ONLY_EXISTS, "TelephoneNumber", path, "");
		}
		return failure("TelephoneNumber", ValidationType.ONLY_EXISTS, "TelephoneNumber", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
