package cdm.base.math.validation.exists;

import cdm.base.math.MoneyBound;
import cdm.base.math.MoneyRange;
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

public class MoneyRangeOnlyExistsValidator implements ValidatorWithArg<MoneyRange, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends MoneyRange> ValidationResult<MoneyRange> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("lowerBound", ExistenceChecker.isSet((MoneyBound) o.getLowerBound()))
				.put("upperBound", ExistenceChecker.isSet((MoneyBound) o.getUpperBound()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("MoneyRange", ValidationType.ONLY_EXISTS, "MoneyRange", path, "");
		}
		return failure("MoneyRange", ValidationType.ONLY_EXISTS, "MoneyRange", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
