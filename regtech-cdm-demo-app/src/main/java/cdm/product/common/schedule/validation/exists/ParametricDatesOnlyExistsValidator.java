package cdm.product.common.schedule.validation.exists;

import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.DayOfWeekEnum;
import cdm.base.datetime.DayTypeEnum;
import cdm.product.asset.DayDistributionEnum;
import cdm.product.common.schedule.Lag;
import cdm.product.common.schedule.ParametricDates;
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

public class ParametricDatesOnlyExistsValidator implements ValidatorWithArg<ParametricDates, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ParametricDates> ValidationResult<ParametricDates> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("dayType", ExistenceChecker.isSet((DayTypeEnum) o.getDayType()))
				.put("dayDistribution", ExistenceChecker.isSet((DayDistributionEnum) o.getDayDistribution()))
				.put("dayOfWeek", ExistenceChecker.isSet((List<DayOfWeekEnum>) o.getDayOfWeek()))
				.put("dayFrequency", ExistenceChecker.isSet((BigDecimal) o.getDayFrequency()))
				.put("lag", ExistenceChecker.isSet((Lag) o.getLag()))
				.put("businessCenters", ExistenceChecker.isSet((BusinessCenters) o.getBusinessCenters()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ParametricDates", ValidationType.ONLY_EXISTS, "ParametricDates", path, "");
		}
		return failure("ParametricDates", ValidationType.ONLY_EXISTS, "ParametricDates", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
