package cdm.product.common.schedule.validation.exists;

import cdm.observable.asset.Money;
import cdm.product.asset.FloatingRateDefinition;
import cdm.product.common.schedule.CalculationPeriod;
import cdm.product.common.schedule.FxLinkedNotionalAmount;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class CalculationPeriodOnlyExistsValidator implements ValidatorWithArg<CalculationPeriod, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CalculationPeriod> ValidationResult<CalculationPeriod> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("adjustedStartDate", ExistenceChecker.isSet((Date) o.getAdjustedStartDate()))
				.put("adjustedEndDate", ExistenceChecker.isSet((Date) o.getAdjustedEndDate()))
				.put("unadjustedStartDate", ExistenceChecker.isSet((Date) o.getUnadjustedStartDate()))
				.put("unadjustedEndDate", ExistenceChecker.isSet((Date) o.getUnadjustedEndDate()))
				.put("calculationPeriodNumberOfDays", ExistenceChecker.isSet((Integer) o.getCalculationPeriodNumberOfDays()))
				.put("notionalAmount", ExistenceChecker.isSet((BigDecimal) o.getNotionalAmount()))
				.put("fxLinkedNotionalAmount", ExistenceChecker.isSet((FxLinkedNotionalAmount) o.getFxLinkedNotionalAmount()))
				.put("floatingRateDefinition", ExistenceChecker.isSet((FloatingRateDefinition) o.getFloatingRateDefinition()))
				.put("fixedRate", ExistenceChecker.isSet((BigDecimal) o.getFixedRate()))
				.put("dayCountYearFraction", ExistenceChecker.isSet((BigDecimal) o.getDayCountYearFraction()))
				.put("forecastAmount", ExistenceChecker.isSet((Money) o.getForecastAmount()))
				.put("forecastRate", ExistenceChecker.isSet((BigDecimal) o.getForecastRate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CalculationPeriod", ValidationType.ONLY_EXISTS, "CalculationPeriod", path, "");
		}
		return failure("CalculationPeriod", ValidationType.ONLY_EXISTS, "CalculationPeriod", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
