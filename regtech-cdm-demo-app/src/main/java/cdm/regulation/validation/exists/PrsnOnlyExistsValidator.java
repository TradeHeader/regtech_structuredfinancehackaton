package cdm.regulation.validation.exists;

import cdm.regulation.Othr;
import cdm.regulation.Prsn;
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

public class PrsnOnlyExistsValidator implements ValidatorWithArg<Prsn, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Prsn> ValidationResult<Prsn> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("ctryOfBrnch", ExistenceChecker.isSet((String) o.getCtryOfBrnch()))
				.put("othr", ExistenceChecker.isSet((Othr) o.getOthr()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Prsn", ValidationType.ONLY_EXISTS, "Prsn", path, "");
		}
		return failure("Prsn", ValidationType.ONLY_EXISTS, "Prsn", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
