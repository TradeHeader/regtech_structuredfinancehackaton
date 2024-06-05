package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.staticdata.asset.common.RegionalGovernmentIssuerType;
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

public class RegionalGovernmentIssuerTypeOnlyExistsValidator implements ValidatorWithArg<RegionalGovernmentIssuerType, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends RegionalGovernmentIssuerType> ValidationResult<RegionalGovernmentIssuerType> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("sovereignRecourse", ExistenceChecker.isSet((Boolean) o.getSovereignRecourse()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("RegionalGovernmentIssuerType", ValidationType.ONLY_EXISTS, "RegionalGovernmentIssuerType", path, "");
		}
		return failure("RegionalGovernmentIssuerType", ValidationType.ONLY_EXISTS, "RegionalGovernmentIssuerType", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
