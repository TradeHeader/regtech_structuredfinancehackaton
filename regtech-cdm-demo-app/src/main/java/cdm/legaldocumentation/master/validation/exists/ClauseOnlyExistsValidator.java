package cdm.legaldocumentation.master.validation.exists;

import cdm.legaldocumentation.master.Clause;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ClauseOnlyExistsValidator implements ValidatorWithArg<Clause, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Clause> ValidationResult<Clause> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("identifier", ExistenceChecker.isSet((String) o.getIdentifier()))
				.put("terms", ExistenceChecker.isSet((String) o.getTerms()))
				.put("subcomponents", ExistenceChecker.isSet((List<? extends Clause>) o.getSubcomponents()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Clause", ValidationType.ONLY_EXISTS, "Clause", path, "");
		}
		return failure("Clause", ValidationType.ONLY_EXISTS, "Clause", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
