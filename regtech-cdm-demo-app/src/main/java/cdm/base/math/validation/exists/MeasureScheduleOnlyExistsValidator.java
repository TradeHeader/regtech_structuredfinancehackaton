package cdm.base.math.validation.exists;

import cdm.base.math.DatedValue;
import cdm.base.math.MeasureSchedule;
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

public class MeasureScheduleOnlyExistsValidator implements ValidatorWithArg<MeasureSchedule, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends MeasureSchedule> ValidationResult<MeasureSchedule> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("value", ExistenceChecker.isSet((BigDecimal) o.getValue()))
				.put("unit", ExistenceChecker.isSet((UnitType) o.getUnit()))
				.put("datedValue", ExistenceChecker.isSet((List<? extends DatedValue>) o.getDatedValue()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("MeasureSchedule", ValidationType.ONLY_EXISTS, "MeasureSchedule", path, "");
		}
		return failure("MeasureSchedule", ValidationType.ONLY_EXISTS, "MeasureSchedule", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
