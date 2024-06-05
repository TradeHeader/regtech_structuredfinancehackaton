package cdm.observable.asset.validation.exists;

import cdm.observable.asset.DividendApplicability;
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

public class DividendApplicabilityOnlyExistsValidator implements ValidatorWithArg<DividendApplicability, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DividendApplicability> ValidationResult<DividendApplicability> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("optionsExchangeDividends", ExistenceChecker.isSet((Boolean) o.getOptionsExchangeDividends()))
				.put("additionalDividends", ExistenceChecker.isSet((Boolean) o.getAdditionalDividends()))
				.put("allDividends", ExistenceChecker.isSet((Boolean) o.getAllDividends()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DividendApplicability", ValidationType.ONLY_EXISTS, "DividendApplicability", path, "");
		}
		return failure("DividendApplicability", ValidationType.ONLY_EXISTS, "DividendApplicability", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
