package cdm.product.common.schedule.validation.exists;

import cdm.observable.asset.Money;
import cdm.product.common.schedule.CalculationPeriod;
import cdm.product.common.schedule.PaymentCalculationPeriod;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class PaymentCalculationPeriodOnlyExistsValidator implements ValidatorWithArg<PaymentCalculationPeriod, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PaymentCalculationPeriod> ValidationResult<PaymentCalculationPeriod> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("unadjustedPaymentDate", ExistenceChecker.isSet((Date) o.getUnadjustedPaymentDate()))
				.put("adjustedPaymentDate", ExistenceChecker.isSet((Date) o.getAdjustedPaymentDate()))
				.put("calculationPeriod", ExistenceChecker.isSet((List<? extends CalculationPeriod>) o.getCalculationPeriod()))
				.put("fixedPaymentAmount", ExistenceChecker.isSet((Money) o.getFixedPaymentAmount()))
				.put("discountFactor", ExistenceChecker.isSet((BigDecimal) o.getDiscountFactor()))
				.put("forecastPaymentAmount", ExistenceChecker.isSet((Money) o.getForecastPaymentAmount()))
				.put("presentValueAmount", ExistenceChecker.isSet((Money) o.getPresentValueAmount()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PaymentCalculationPeriod", ValidationType.ONLY_EXISTS, "PaymentCalculationPeriod", path, "");
		}
		return failure("PaymentCalculationPeriod", ValidationType.ONLY_EXISTS, "PaymentCalculationPeriod", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
