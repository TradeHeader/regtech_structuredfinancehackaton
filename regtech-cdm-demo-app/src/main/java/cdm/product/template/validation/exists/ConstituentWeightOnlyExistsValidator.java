package cdm.product.template.validation.exists;

import cdm.product.template.ConstituentWeight;
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

public class ConstituentWeightOnlyExistsValidator implements ValidatorWithArg<ConstituentWeight, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ConstituentWeight> ValidationResult<ConstituentWeight> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("openUnits", ExistenceChecker.isSet((BigDecimal) o.getOpenUnits()))
				.put("basketPercentage", ExistenceChecker.isSet((BigDecimal) o.getBasketPercentage()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ConstituentWeight", ValidationType.ONLY_EXISTS, "ConstituentWeight", path, "");
		}
		return failure("ConstituentWeight", ValidationType.ONLY_EXISTS, "ConstituentWeight", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
