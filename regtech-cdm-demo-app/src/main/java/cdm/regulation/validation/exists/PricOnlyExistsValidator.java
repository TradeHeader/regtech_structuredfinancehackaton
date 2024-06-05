package cdm.regulation.validation.exists;

import cdm.regulation.Pric;
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

public class PricOnlyExistsValidator implements ValidatorWithArg<Pric, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Pric> ValidationResult<Pric> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("pric", ExistenceChecker.isSet((Pric) o.getPric()))
				.put("bsisPts", ExistenceChecker.isSet((String) o.getBsisPts()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Pric", ValidationType.ONLY_EXISTS, "Pric", path, "");
		}
		return failure("Pric", ValidationType.ONLY_EXISTS, "Pric", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
