package cdm.regulation.validation.exists;

import cdm.regulation.RefRate;
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

public class RefRateOnlyExistsValidator implements ValidatorWithArg<RefRate, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends RefRate> ValidationResult<RefRate> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("indx", ExistenceChecker.isSet((String) o.getIndx()))
				.put("nm", ExistenceChecker.isSet((String) o.getNm()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("RefRate", ValidationType.ONLY_EXISTS, "RefRate", path, "");
		}
		return failure("RefRate", ValidationType.ONLY_EXISTS, "RefRate", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
