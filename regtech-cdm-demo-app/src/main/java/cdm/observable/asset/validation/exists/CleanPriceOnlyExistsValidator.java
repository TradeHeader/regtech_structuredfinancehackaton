package cdm.observable.asset.validation.exists;

import cdm.observable.asset.CleanPrice;
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

public class CleanPriceOnlyExistsValidator implements ValidatorWithArg<CleanPrice, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CleanPrice> ValidationResult<CleanPrice> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("cleanPrice", ExistenceChecker.isSet((BigDecimal) o.getCleanPrice()))
				.put("accruals", ExistenceChecker.isSet((BigDecimal) o.getAccruals()))
				.put("dirtyPrice", ExistenceChecker.isSet((String) o.getDirtyPrice()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CleanPrice", ValidationType.ONLY_EXISTS, "CleanPrice", path, "");
		}
		return failure("CleanPrice", ValidationType.ONLY_EXISTS, "CleanPrice", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
