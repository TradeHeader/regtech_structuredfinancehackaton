package cdm.product.template.validation.exists;

import cdm.product.template.Duration;
import cdm.product.template.DurationTypeEnum;
import cdm.product.template.EvergreenProvision;
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

public class DurationOnlyExistsValidator implements ValidatorWithArg<Duration, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Duration> ValidationResult<Duration> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("durationType", ExistenceChecker.isSet((DurationTypeEnum) o.getDurationType()))
				.put("evergreenProvision", ExistenceChecker.isSet((EvergreenProvision) o.getEvergreenProvision()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Duration", ValidationType.ONLY_EXISTS, "Duration", path, "");
		}
		return failure("Duration", ValidationType.ONLY_EXISTS, "Duration", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
