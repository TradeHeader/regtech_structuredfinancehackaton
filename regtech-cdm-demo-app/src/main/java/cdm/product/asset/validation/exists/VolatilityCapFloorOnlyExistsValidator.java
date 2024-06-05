package cdm.product.asset.validation.exists;

import cdm.product.asset.VolatilityCapFloor;
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

public class VolatilityCapFloorOnlyExistsValidator implements ValidatorWithArg<VolatilityCapFloor, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends VolatilityCapFloor> ValidationResult<VolatilityCapFloor> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("applicable", ExistenceChecker.isSet((Boolean) o.getApplicable()))
				.put("totalVolatilityCap", ExistenceChecker.isSet((BigDecimal) o.getTotalVolatilityCap()))
				.put("volatilityCapFactor", ExistenceChecker.isSet((BigDecimal) o.getVolatilityCapFactor()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("VolatilityCapFloor", ValidationType.ONLY_EXISTS, "VolatilityCapFloor", path, "");
		}
		return failure("VolatilityCapFloor", ValidationType.ONLY_EXISTS, "VolatilityCapFloor", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
