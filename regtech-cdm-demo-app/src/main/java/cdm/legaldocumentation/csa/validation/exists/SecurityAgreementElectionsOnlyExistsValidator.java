package cdm.legaldocumentation.csa.validation.exists;

import cdm.legaldocumentation.csa.SecurityAgreementElections;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class SecurityAgreementElectionsOnlyExistsValidator implements ValidatorWithArg<SecurityAgreementElections, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SecurityAgreementElections> ValidationResult<SecurityAgreementElections> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SecurityAgreementElections", ValidationType.ONLY_EXISTS, "SecurityAgreementElections", path, "");
		}
		return failure("SecurityAgreementElections", ValidationType.ONLY_EXISTS, "SecurityAgreementElections", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
