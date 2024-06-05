package cdm.event.position.validation.exists;

import cdm.event.position.Inventory;
import cdm.event.position.InventoryRecord;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class InventoryOnlyExistsValidator implements ValidatorWithArg<Inventory, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Inventory> ValidationResult<Inventory> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("inventoryRecord", ExistenceChecker.isSet((List<? extends InventoryRecord>) o.getInventoryRecord()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Inventory", ValidationType.ONLY_EXISTS, "Inventory", path, "");
		}
		return failure("Inventory", ValidationType.ONLY_EXISTS, "Inventory", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
