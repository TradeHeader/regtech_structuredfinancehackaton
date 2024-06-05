package cdm.product.common.schedule.validation.exists;

import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.Frequency;
import cdm.base.datetime.Offset;
import cdm.product.common.schedule.PayRelativeToEnum;
import cdm.product.common.schedule.PaymentDateSchedule;
import cdm.product.common.schedule.PaymentDates;
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

public class PaymentDatesOnlyExistsValidator implements ValidatorWithArg<PaymentDates, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PaymentDates> ValidationResult<PaymentDates> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("paymentFrequency", ExistenceChecker.isSet((Frequency) o.getPaymentFrequency()))
				.put("firstPaymentDate", ExistenceChecker.isSet((Date) o.getFirstPaymentDate()))
				.put("lastRegularPaymentDate", ExistenceChecker.isSet((Date) o.getLastRegularPaymentDate()))
				.put("paymentDateSchedule", ExistenceChecker.isSet((PaymentDateSchedule) o.getPaymentDateSchedule()))
				.put("payRelativeTo", ExistenceChecker.isSet((PayRelativeToEnum) o.getPayRelativeTo()))
				.put("paymentDaysOffset", ExistenceChecker.isSet((Offset) o.getPaymentDaysOffset()))
				.put("paymentDatesAdjustments", ExistenceChecker.isSet((BusinessDayAdjustments) o.getPaymentDatesAdjustments()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PaymentDates", ValidationType.ONLY_EXISTS, "PaymentDates", path, "");
		}
		return failure("PaymentDates", ValidationType.ONLY_EXISTS, "PaymentDates", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
