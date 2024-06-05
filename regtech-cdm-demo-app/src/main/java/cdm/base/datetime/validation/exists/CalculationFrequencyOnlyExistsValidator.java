package cdm.base.datetime.validation.exists;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.CalculationFrequency;
import cdm.base.datetime.DayOfWeekEnum;
import cdm.base.datetime.Period;
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

public class CalculationFrequencyOnlyExistsValidator implements ValidatorWithArg<CalculationFrequency, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CalculationFrequency> ValidationResult<CalculationFrequency> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("period", ExistenceChecker.isSet((Period) o.getPeriod()))
				.put("monthOfYear", ExistenceChecker.isSet((BigDecimal) o.getMonthOfYear()))
				.put("dayOfMonth", ExistenceChecker.isSet((BigDecimal) o.getDayOfMonth()))
				.put("dayOfWeek", ExistenceChecker.isSet((DayOfWeekEnum) o.getDayOfWeek()))
				.put("weekOfMonth", ExistenceChecker.isSet((BigDecimal) o.getWeekOfMonth()))
				.put("offsetDays", ExistenceChecker.isSet((BigDecimal) o.getOffsetDays()))
				.put("dateLocation", ExistenceChecker.isSet((BusinessCenterTime) o.getDateLocation()))
				.put("businessCenter", ExistenceChecker.isSet((List<BusinessCenterEnum>) o.getBusinessCenter()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CalculationFrequency", ValidationType.ONLY_EXISTS, "CalculationFrequency", path, "");
		}
		return failure("CalculationFrequency", ValidationType.ONLY_EXISTS, "CalculationFrequency", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
