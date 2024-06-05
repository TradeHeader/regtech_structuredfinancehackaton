package cdm.product.asset.validation.exists;

import cdm.observable.asset.Money;
import cdm.product.asset.StubFloatingRate;
import cdm.product.asset.StubValue;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class StubValueOnlyExistsValidator implements ValidatorWithArg<StubValue, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends StubValue> ValidationResult<StubValue> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("floatingRate", ExistenceChecker.isSet((List<? extends StubFloatingRate>) o.getFloatingRate()))
				.put("stubRate", ExistenceChecker.isSet((BigDecimal) o.getStubRate()))
				.put("stubAmount", ExistenceChecker.isSet((Money) o.getStubAmount()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("StubValue", ValidationType.ONLY_EXISTS, "StubValue", path, "");
		}
		return failure("StubValue", ValidationType.ONLY_EXISTS, "StubValue", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
