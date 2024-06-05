package cdm.base.staticdata.identifier.validation.exists;

import cdm.base.staticdata.identifier.IdentifiedList;
import cdm.base.staticdata.identifier.Identifier;
import cdm.observable.asset.Price;
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

public class IdentifiedListOnlyExistsValidator implements ValidatorWithArg<IdentifiedList, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends IdentifiedList> ValidationResult<IdentifiedList> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("listId", ExistenceChecker.isSet((Identifier) o.getListId()))
				.put("componentId", ExistenceChecker.isSet((List<? extends Identifier>) o.getComponentId()))
				.put("price", ExistenceChecker.isSet((Price) o.getPrice()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("IdentifiedList", ValidationType.ONLY_EXISTS, "IdentifiedList", path, "");
		}
		return failure("IdentifiedList", ValidationType.ONLY_EXISTS, "IdentifiedList", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
