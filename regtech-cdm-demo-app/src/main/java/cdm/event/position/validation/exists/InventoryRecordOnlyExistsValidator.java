package cdm.event.position.validation.exists;

import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.identifier.AssignedIdentifier;
import cdm.event.position.InventoryRecord;
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

public class InventoryRecordOnlyExistsValidator implements ValidatorWithArg<InventoryRecord, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends InventoryRecord> ValidationResult<InventoryRecord> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("identifer", ExistenceChecker.isSet((AssignedIdentifier) o.getIdentifer()))
				.put("security", ExistenceChecker.isSet((Security) o.getSecurity()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("InventoryRecord", ValidationType.ONLY_EXISTS, "InventoryRecord", path, "");
		}
		return failure("InventoryRecord", ValidationType.ONLY_EXISTS, "InventoryRecord", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
