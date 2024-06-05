package cdm.base.math.validation.exists;

import cdm.base.datetime.Frequency;
import cdm.base.math.DatedValue;
import cdm.base.math.Measure;
import cdm.base.math.NonNegativeQuantity;
import cdm.base.math.UnitType;
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

public class NonNegativeQuantityOnlyExistsValidator implements ValidatorWithArg<NonNegativeQuantity, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends NonNegativeQuantity> ValidationResult<NonNegativeQuantity> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("value", ExistenceChecker.isSet((BigDecimal) o.getValue()))
				.put("unit", ExistenceChecker.isSet((UnitType) o.getUnit()))
				.put("datedValue", ExistenceChecker.isSet((List<? extends DatedValue>) o.getDatedValue()))
				.put("multiplier", ExistenceChecker.isSet((Measure) o.getMultiplier()))
				.put("frequency", ExistenceChecker.isSet((Frequency) o.getFrequency()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("NonNegativeQuantity", ValidationType.ONLY_EXISTS, "NonNegativeQuantity", path, "");
		}
		return failure("NonNegativeQuantity", ValidationType.ONLY_EXISTS, "NonNegativeQuantity", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
