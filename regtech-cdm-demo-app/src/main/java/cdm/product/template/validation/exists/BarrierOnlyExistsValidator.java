package cdm.product.template.validation.exists;

import cdm.observable.event.TriggerEvent;
import cdm.product.template.Barrier;
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

public class BarrierOnlyExistsValidator implements ValidatorWithArg<Barrier, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Barrier> ValidationResult<Barrier> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("barrierCap", ExistenceChecker.isSet((TriggerEvent) o.getBarrierCap()))
				.put("barrierFloor", ExistenceChecker.isSet((TriggerEvent) o.getBarrierFloor()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Barrier", ValidationType.ONLY_EXISTS, "Barrier", path, "");
		}
		return failure("Barrier", ValidationType.ONLY_EXISTS, "Barrier", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
