package cdm.event.common.validation.exists;

import cdm.event.common.CallTypeEnum;
import cdm.event.common.MarginCallInstructionType;
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

public class MarginCallInstructionTypeOnlyExistsValidator implements ValidatorWithArg<MarginCallInstructionType, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends MarginCallInstructionType> ValidationResult<MarginCallInstructionType> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("callType", ExistenceChecker.isSet((CallTypeEnum) o.getCallType()))
				.put("visibilityIndicator", ExistenceChecker.isSet((Boolean) o.getVisibilityIndicator()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("MarginCallInstructionType", ValidationType.ONLY_EXISTS, "MarginCallInstructionType", path, "");
		}
		return failure("MarginCallInstructionType", ValidationType.ONLY_EXISTS, "MarginCallInstructionType", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
