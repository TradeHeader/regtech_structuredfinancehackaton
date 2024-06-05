package cdm.product.common.settlement.validation.exists;

import cdm.product.common.schedule.FxLinkedNotionalSchedule;
import cdm.product.common.settlement.QuantityMultiplier;
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

public class QuantityMultiplierOnlyExistsValidator implements ValidatorWithArg<QuantityMultiplier, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends QuantityMultiplier> ValidationResult<QuantityMultiplier> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("fxLinkedNotionalSchedule", ExistenceChecker.isSet((FxLinkedNotionalSchedule) o.getFxLinkedNotionalSchedule()))
				.put("multiplierValue", ExistenceChecker.isSet((BigDecimal) o.getMultiplierValue()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("QuantityMultiplier", ValidationType.ONLY_EXISTS, "QuantityMultiplier", path, "");
		}
		return failure("QuantityMultiplier", ValidationType.ONLY_EXISTS, "QuantityMultiplier", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
