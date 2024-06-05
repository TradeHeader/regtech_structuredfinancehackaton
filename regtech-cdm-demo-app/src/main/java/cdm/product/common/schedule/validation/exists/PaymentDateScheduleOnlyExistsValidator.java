package cdm.product.common.schedule.validation.exists;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.product.common.schedule.PaymentDateSchedule;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class PaymentDateScheduleOnlyExistsValidator implements ValidatorWithArg<PaymentDateSchedule, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PaymentDateSchedule> ValidationResult<PaymentDateSchedule> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("interimPaymentDates", ExistenceChecker.isSet((List<? extends AdjustableRelativeOrPeriodicDates>) o.getInterimPaymentDates()))
				.put("finalPaymentDate", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getFinalPaymentDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PaymentDateSchedule", ValidationType.ONLY_EXISTS, "PaymentDateSchedule", path, "");
		}
		return failure("PaymentDateSchedule", ValidationType.ONLY_EXISTS, "PaymentDateSchedule", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
