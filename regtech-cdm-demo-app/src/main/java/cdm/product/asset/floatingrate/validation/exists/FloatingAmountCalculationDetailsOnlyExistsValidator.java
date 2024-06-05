package cdm.product.asset.floatingrate.validation.exists;

import cdm.observable.asset.Money;
import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails;
import cdm.product.asset.floatingrate.FloatingRateProcessingDetails;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails;
import cdm.product.common.schedule.CalculationPeriodBase;
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

public class FloatingAmountCalculationDetailsOnlyExistsValidator implements ValidatorWithArg<FloatingAmountCalculationDetails, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FloatingAmountCalculationDetails> ValidationResult<FloatingAmountCalculationDetails> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("calculationPeriod", ExistenceChecker.isSet((CalculationPeriodBase) o.getCalculationPeriod()))
				.put("calculationPeriodNotionalAmount", ExistenceChecker.isSet((Money) o.getCalculationPeriodNotionalAmount()))
				.put("floatingRate", ExistenceChecker.isSet((FloatingRateSettingDetails) o.getFloatingRate()))
				.put("processingDetails", ExistenceChecker.isSet((FloatingRateProcessingDetails) o.getProcessingDetails()))
				.put("appliedRate", ExistenceChecker.isSet((BigDecimal) o.getAppliedRate()))
				.put("yearFraction", ExistenceChecker.isSet((BigDecimal) o.getYearFraction()))
				.put("calculatedAmount", ExistenceChecker.isSet((BigDecimal) o.getCalculatedAmount()))
				.put("spreadExclusiveCalculatedAMount", ExistenceChecker.isSet((BigDecimal) o.getSpreadExclusiveCalculatedAMount()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FloatingAmountCalculationDetails", ValidationType.ONLY_EXISTS, "FloatingAmountCalculationDetails", path, "");
		}
		return failure("FloatingAmountCalculationDetails", ValidationType.ONLY_EXISTS, "FloatingAmountCalculationDetails", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
