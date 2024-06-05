package cdm.base.datetime.validation.exists;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.CalculationPeriodFrequency;
import cdm.base.datetime.DayTypeEnum;
import cdm.base.datetime.PeriodicDates;
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

public class PeriodicDatesOnlyExistsValidator implements ValidatorWithArg<PeriodicDates, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PeriodicDates> ValidationResult<PeriodicDates> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("startDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getStartDate()))
				.put("endDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getEndDate()))
				.put("periodFrequency", ExistenceChecker.isSet((CalculationPeriodFrequency) o.getPeriodFrequency()))
				.put("periodDatesAdjustments", ExistenceChecker.isSet((BusinessDayAdjustments) o.getPeriodDatesAdjustments()))
				.put("dayType", ExistenceChecker.isSet((DayTypeEnum) o.getDayType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PeriodicDates", ValidationType.ONLY_EXISTS, "PeriodicDates", path, "");
		}
		return failure("PeriodicDates", ValidationType.ONLY_EXISTS, "PeriodicDates", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
