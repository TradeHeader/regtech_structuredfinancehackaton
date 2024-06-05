package cdm.event.workflow.validation.exists;

import cdm.event.workflow.CreditLimitUtilisation;
import cdm.event.workflow.CreditLimitUtilisationPosition;
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

public class CreditLimitUtilisationOnlyExistsValidator implements ValidatorWithArg<CreditLimitUtilisation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CreditLimitUtilisation> ValidationResult<CreditLimitUtilisation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("executed", ExistenceChecker.isSet((CreditLimitUtilisationPosition) o.getExecuted()))
				.put("pending", ExistenceChecker.isSet((CreditLimitUtilisationPosition) o.getPending()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CreditLimitUtilisation", ValidationType.ONLY_EXISTS, "CreditLimitUtilisation", path, "");
		}
		return failure("CreditLimitUtilisation", ValidationType.ONLY_EXISTS, "CreditLimitUtilisation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
