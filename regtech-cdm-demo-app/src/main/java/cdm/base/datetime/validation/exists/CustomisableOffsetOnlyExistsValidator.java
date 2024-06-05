package cdm.base.datetime.validation.exists;

import cdm.base.datetime.CustomisableOffset;
import cdm.base.datetime.Offset;
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

public class CustomisableOffsetOnlyExistsValidator implements ValidatorWithArg<CustomisableOffset, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CustomisableOffset> ValidationResult<CustomisableOffset> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("offset", ExistenceChecker.isSet((Offset) o.getOffset()))
				.put("customProvision", ExistenceChecker.isSet((String) o.getCustomProvision()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CustomisableOffset", ValidationType.ONLY_EXISTS, "CustomisableOffset", path, "");
		}
		return failure("CustomisableOffset", ValidationType.ONLY_EXISTS, "CustomisableOffset", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
