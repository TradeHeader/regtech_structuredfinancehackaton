package cdm.product.common.schedule.validation.exists;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.CalculationPeriodFrequency;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.StubPeriodTypeEnum;
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

public class CalculationPeriodDatesOnlyExistsValidator implements ValidatorWithArg<CalculationPeriodDates, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CalculationPeriodDates> ValidationResult<CalculationPeriodDates> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("effectiveDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getEffectiveDate()))
				.put("terminationDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getTerminationDate()))
				.put("calculationPeriodDatesAdjustments", ExistenceChecker.isSet((BusinessDayAdjustments) o.getCalculationPeriodDatesAdjustments()))
				.put("firstPeriodStartDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getFirstPeriodStartDate()))
				.put("firstRegularPeriodStartDate", ExistenceChecker.isSet((Date) o.getFirstRegularPeriodStartDate()))
				.put("firstCompoundingPeriodEndDate", ExistenceChecker.isSet((Date) o.getFirstCompoundingPeriodEndDate()))
				.put("lastRegularPeriodEndDate", ExistenceChecker.isSet((Date) o.getLastRegularPeriodEndDate()))
				.put("stubPeriodType", ExistenceChecker.isSet((StubPeriodTypeEnum) o.getStubPeriodType()))
				.put("calculationPeriodFrequency", ExistenceChecker.isSet((CalculationPeriodFrequency) o.getCalculationPeriodFrequency()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CalculationPeriodDates", ValidationType.ONLY_EXISTS, "CalculationPeriodDates", path, "");
		}
		return failure("CalculationPeriodDates", ValidationType.ONLY_EXISTS, "CalculationPeriodDates", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
