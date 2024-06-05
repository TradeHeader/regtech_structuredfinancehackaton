package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.staticdata.asset.common.QuasiGovernmentIssuerType;
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

public class QuasiGovernmentIssuerTypeOnlyExistsValidator implements ValidatorWithArg<QuasiGovernmentIssuerType, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends QuasiGovernmentIssuerType> ValidationResult<QuasiGovernmentIssuerType> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("sovereignEntity", ExistenceChecker.isSet((Boolean) o.getSovereignEntity()))
				.put("sovereignRecourse", ExistenceChecker.isSet((Boolean) o.getSovereignRecourse()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("QuasiGovernmentIssuerType", ValidationType.ONLY_EXISTS, "QuasiGovernmentIssuerType", path, "");
		}
		return failure("QuasiGovernmentIssuerType", ValidationType.ONLY_EXISTS, "QuasiGovernmentIssuerType", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}