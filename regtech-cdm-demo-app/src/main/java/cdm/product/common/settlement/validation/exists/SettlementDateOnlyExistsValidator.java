package cdm.product.common.settlement.validation.exists;

import cdm.base.datetime.AdjustableDates;
import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import cdm.base.datetime.BusinessDateRange;
import cdm.product.common.settlement.SettlementDate;
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

public class SettlementDateOnlyExistsValidator implements ValidatorWithArg<SettlementDate, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SettlementDate> ValidationResult<SettlementDate> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("adjustableOrRelativeDate", ExistenceChecker.isSet((AdjustableOrAdjustedOrRelativeDate) o.getAdjustableOrRelativeDate()))
				.put("valueDate", ExistenceChecker.isSet((Date) o.getValueDate()))
				.put("adjustableDates", ExistenceChecker.isSet((AdjustableDates) o.getAdjustableDates()))
				.put("businessDateRange", ExistenceChecker.isSet((BusinessDateRange) o.getBusinessDateRange()))
				.put("cashSettlementBusinessDays", ExistenceChecker.isSet((Integer) o.getCashSettlementBusinessDays()))
				.put("paymentDelay", ExistenceChecker.isSet((Boolean) o.getPaymentDelay()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SettlementDate", ValidationType.ONLY_EXISTS, "SettlementDate", path, "");
		}
		return failure("SettlementDate", ValidationType.ONLY_EXISTS, "SettlementDate", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
