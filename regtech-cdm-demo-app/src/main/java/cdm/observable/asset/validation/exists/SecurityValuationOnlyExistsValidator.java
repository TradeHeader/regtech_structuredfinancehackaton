package cdm.observable.asset.validation.exists;

import cdm.base.staticdata.asset.common.Security;
import cdm.observable.asset.SecurityValuation;
import cdm.observable.asset.SecurityValuationModel;
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

public class SecurityValuationOnlyExistsValidator implements ValidatorWithArg<SecurityValuation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SecurityValuation> ValidationResult<SecurityValuation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("securityValuationModel", ExistenceChecker.isSet((SecurityValuationModel) o.getSecurityValuationModel()))
				.put("underlier", ExistenceChecker.isSet((Security) o.getUnderlier()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SecurityValuation", ValidationType.ONLY_EXISTS, "SecurityValuation", path, "");
		}
		return failure("SecurityValuation", ValidationType.ONLY_EXISTS, "SecurityValuation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
