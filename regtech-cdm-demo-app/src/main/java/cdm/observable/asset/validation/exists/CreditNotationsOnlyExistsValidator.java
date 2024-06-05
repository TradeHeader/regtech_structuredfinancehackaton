package cdm.observable.asset.validation.exists;

import cdm.observable.asset.CreditNotation;
import cdm.observable.asset.CreditNotations;
import cdm.observable.asset.MultipleCreditNotations;
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

public class CreditNotationsOnlyExistsValidator implements ValidatorWithArg<CreditNotations, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CreditNotations> ValidationResult<CreditNotations> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("creditNotation", ExistenceChecker.isSet((CreditNotation) o.getCreditNotation()))
				.put("creditNotations", ExistenceChecker.isSet((MultipleCreditNotations) o.getCreditNotations()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CreditNotations", ValidationType.ONLY_EXISTS, "CreditNotations", path, "");
		}
		return failure("CreditNotations", ValidationType.ONLY_EXISTS, "CreditNotations", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
