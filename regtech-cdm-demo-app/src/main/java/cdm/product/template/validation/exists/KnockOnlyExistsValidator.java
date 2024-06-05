package cdm.product.template.validation.exists;

import cdm.observable.event.TriggerEvent;
import cdm.product.template.Knock;
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

public class KnockOnlyExistsValidator implements ValidatorWithArg<Knock, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Knock> ValidationResult<Knock> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("knockIn", ExistenceChecker.isSet((TriggerEvent) o.getKnockIn()))
				.put("knockOut", ExistenceChecker.isSet((TriggerEvent) o.getKnockOut()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Knock", ValidationType.ONLY_EXISTS, "Knock", path, "");
		}
		return failure("Knock", ValidationType.ONLY_EXISTS, "Knock", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
