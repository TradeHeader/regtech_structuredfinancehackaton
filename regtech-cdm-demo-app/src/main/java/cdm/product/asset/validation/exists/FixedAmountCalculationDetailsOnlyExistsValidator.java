package cdm.product.asset.validation.exists;

import cdm.observable.asset.Money;
import cdm.product.asset.FixedAmountCalculationDetails;
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

public class FixedAmountCalculationDetailsOnlyExistsValidator implements ValidatorWithArg<FixedAmountCalculationDetails, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FixedAmountCalculationDetails> ValidationResult<FixedAmountCalculationDetails> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("calculationPeriod", ExistenceChecker.isSet((CalculationPeriodBase) o.getCalculationPeriod()))
				.put("calculationPeriodNotionalAmount", ExistenceChecker.isSet((Money) o.getCalculationPeriodNotionalAmount()))
				.put("fixedRate", ExistenceChecker.isSet((BigDecimal) o.getFixedRate()))
				.put("yearFraction", ExistenceChecker.isSet((BigDecimal) o.getYearFraction()))
				.put("calculatedAmount", ExistenceChecker.isSet((BigDecimal) o.getCalculatedAmount()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FixedAmountCalculationDetails", ValidationType.ONLY_EXISTS, "FixedAmountCalculationDetails", path, "");
		}
		return failure("FixedAmountCalculationDetails", ValidationType.ONLY_EXISTS, "FixedAmountCalculationDetails", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
