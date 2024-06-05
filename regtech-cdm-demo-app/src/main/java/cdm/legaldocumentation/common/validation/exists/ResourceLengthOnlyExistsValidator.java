package cdm.legaldocumentation.common.validation.exists;

import cdm.legaldocumentation.common.LengthUnitEnum;
import cdm.legaldocumentation.common.ResourceLength;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ResourceLengthOnlyExistsValidator implements ValidatorWithArg<ResourceLength, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ResourceLength> ValidationResult<ResourceLength> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("lengthUnit", ExistenceChecker.isSet((LengthUnitEnum) o.getLengthUnit()))
				.put("lengthValue", ExistenceChecker.isSet((BigDecimal) o.getLengthValue()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ResourceLength", ValidationType.ONLY_EXISTS, "ResourceLength", path, "");
		}
		return failure("ResourceLength", ValidationType.ONLY_EXISTS, "ResourceLength", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
