package cdm.base.math.validation.exists;

import cdm.base.math.MoneyBound;
import cdm.observable.asset.Money;
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

public class MoneyBoundOnlyExistsValidator implements ValidatorWithArg<MoneyBound, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends MoneyBound> ValidationResult<MoneyBound> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("money", ExistenceChecker.isSet((Money) o.getMoney()))
				.put("inclusive", ExistenceChecker.isSet((Boolean) o.getInclusive()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("MoneyBound", ValidationType.ONLY_EXISTS, "MoneyBound", path, "");
		}
		return failure("MoneyBound", ValidationType.ONLY_EXISTS, "MoneyBound", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
