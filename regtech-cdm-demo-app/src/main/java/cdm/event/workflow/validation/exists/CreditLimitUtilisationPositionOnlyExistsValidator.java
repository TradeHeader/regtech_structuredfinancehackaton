package cdm.event.workflow.validation.exists;

import cdm.event.workflow.CreditLimitUtilisationPosition;
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

public class CreditLimitUtilisationPositionOnlyExistsValidator implements ValidatorWithArg<CreditLimitUtilisationPosition, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CreditLimitUtilisationPosition> ValidationResult<CreditLimitUtilisationPosition> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("shortPosition", ExistenceChecker.isSet((BigDecimal) o.getShortPosition()))
				.put("longPosition", ExistenceChecker.isSet((BigDecimal) o.getLongPosition()))
				.put("global", ExistenceChecker.isSet((BigDecimal) o.getGlobal()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CreditLimitUtilisationPosition", ValidationType.ONLY_EXISTS, "CreditLimitUtilisationPosition", path, "");
		}
		return failure("CreditLimitUtilisationPosition", ValidationType.ONLY_EXISTS, "CreditLimitUtilisationPosition", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
