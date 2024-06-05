package cdm.observable.asset.validation.exists;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.base.datetime.BusinessCenterTime;
import cdm.observable.asset.PerformanceValuationDates;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.observable.common.TimeTypeEnum;
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

public class PerformanceValuationDatesOnlyExistsValidator implements ValidatorWithArg<PerformanceValuationDates, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PerformanceValuationDates> ValidationResult<PerformanceValuationDates> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("determinationMethod", ExistenceChecker.isSet((DeterminationMethodEnum) o.getDeterminationMethod()))
				.put("valuationDates", ExistenceChecker.isSet((AdjustableRelativeOrPeriodicDates) o.getValuationDates()))
				.put("valuationDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getValuationDate()))
				.put("valuationTime", ExistenceChecker.isSet((BusinessCenterTime) o.getValuationTime()))
				.put("valuationTimeType", ExistenceChecker.isSet((TimeTypeEnum) o.getValuationTimeType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PerformanceValuationDates", ValidationType.ONLY_EXISTS, "PerformanceValuationDates", path, "");
		}
		return failure("PerformanceValuationDates", ValidationType.ONLY_EXISTS, "PerformanceValuationDates", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
