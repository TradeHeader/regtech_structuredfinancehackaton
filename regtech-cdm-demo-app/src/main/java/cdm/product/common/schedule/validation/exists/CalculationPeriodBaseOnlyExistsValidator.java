package cdm.product.common.schedule.validation.exists;

import cdm.product.common.schedule.CalculationPeriodBase;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CalculationPeriodBaseOnlyExistsValidator implements ValidatorWithArg<CalculationPeriodBase, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CalculationPeriodBase> ValidationResult<CalculationPeriodBase> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("adjustedStartDate", ExistenceChecker.isSet((Date) o.getAdjustedStartDate()))
				.put("adjustedEndDate", ExistenceChecker.isSet((Date) o.getAdjustedEndDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CalculationPeriodBase", ValidationType.ONLY_EXISTS, "CalculationPeriodBase", path, "");
		}
		return failure("CalculationPeriodBase", ValidationType.ONLY_EXISTS, "CalculationPeriodBase", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
