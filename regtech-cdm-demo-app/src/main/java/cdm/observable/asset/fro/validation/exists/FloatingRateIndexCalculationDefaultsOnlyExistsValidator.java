package cdm.observable.asset.fro.validation.exists;

import cdm.observable.asset.fro.FloatingRateIndexCalculationDefaults;
import cdm.observable.asset.fro.FloatingRateIndexCalculationMethodEnum;
import cdm.observable.asset.fro.FloatingRateIndexCategoryEnum;
import cdm.observable.asset.fro.FloatingRateIndexStyleEnum;
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

public class FloatingRateIndexCalculationDefaultsOnlyExistsValidator implements ValidatorWithArg<FloatingRateIndexCalculationDefaults, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FloatingRateIndexCalculationDefaults> ValidationResult<FloatingRateIndexCalculationDefaults> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("category", ExistenceChecker.isSet((FloatingRateIndexCategoryEnum) o.getCategory()))
				.put("indexStyle", ExistenceChecker.isSet((FloatingRateIndexStyleEnum) o.getIndexStyle()))
				.put("method", ExistenceChecker.isSet((FloatingRateIndexCalculationMethodEnum) o.getMethod()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FloatingRateIndexCalculationDefaults", ValidationType.ONLY_EXISTS, "FloatingRateIndexCalculationDefaults", path, "");
		}
		return failure("FloatingRateIndexCalculationDefaults", ValidationType.ONLY_EXISTS, "FloatingRateIndexCalculationDefaults", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
