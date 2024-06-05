package cdm.product.template.validation.exists;

import cdm.base.datetime.DateRange;
import cdm.product.asset.CalculationScheduleDeliveryPeriods;
import cdm.product.template.SchedulePeriod;
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

public class SchedulePeriodOnlyExistsValidator implements ValidatorWithArg<SchedulePeriod, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SchedulePeriod> ValidationResult<SchedulePeriod> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("calculationPeriod", ExistenceChecker.isSet((DateRange) o.getCalculationPeriod()))
				.put("paymentDate", ExistenceChecker.isSet((Date) o.getPaymentDate()))
				.put("fixingPeriod", ExistenceChecker.isSet((DateRange) o.getFixingPeriod()))
				.put("deliveryPeriod", ExistenceChecker.isSet((CalculationScheduleDeliveryPeriods) o.getDeliveryPeriod()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SchedulePeriod", ValidationType.ONLY_EXISTS, "SchedulePeriod", path, "");
		}
		return failure("SchedulePeriod", ValidationType.ONLY_EXISTS, "SchedulePeriod", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
