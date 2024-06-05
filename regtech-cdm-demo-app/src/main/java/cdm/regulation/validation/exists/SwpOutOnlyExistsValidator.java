package cdm.regulation.validation.exists;

import cdm.regulation.Sngl;
import cdm.regulation.SwpOut;
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

public class SwpOutOnlyExistsValidator implements ValidatorWithArg<SwpOut, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SwpOut> ValidationResult<SwpOut> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("sngl", ExistenceChecker.isSet((Sngl) o.getSngl()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SwpOut", ValidationType.ONLY_EXISTS, "SwpOut", path, "");
		}
		return failure("SwpOut", ValidationType.ONLY_EXISTS, "SwpOut", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
