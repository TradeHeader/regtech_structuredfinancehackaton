package cdm.event.common.validation.exists;

import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
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

public class InstructionOnlyExistsValidator implements ValidatorWithArg<Instruction, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Instruction> ValidationResult<Instruction> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("primitiveInstruction", ExistenceChecker.isSet((PrimitiveInstruction) o.getPrimitiveInstruction()))
				.put("before", ExistenceChecker.isSet((ReferenceWithMetaTradeState) o.getBefore()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Instruction", ValidationType.ONLY_EXISTS, "Instruction", path, "");
		}
		return failure("Instruction", ValidationType.ONLY_EXISTS, "Instruction", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
