package cdm.event.common.validation.exists;

import cdm.event.common.State;
import cdm.event.position.PositionStatusEnum;
import cdm.legaldocumentation.common.ClosedState;
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

public class StateOnlyExistsValidator implements ValidatorWithArg<State, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends State> ValidationResult<State> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("closedState", ExistenceChecker.isSet((ClosedState) o.getClosedState()))
				.put("positionState", ExistenceChecker.isSet((PositionStatusEnum) o.getPositionState()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("State", ValidationType.ONLY_EXISTS, "State", path, "");
		}
		return failure("State", ValidationType.ONLY_EXISTS, "State", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
