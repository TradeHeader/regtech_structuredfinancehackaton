package cdm.event.common.validation.exists;

import cdm.event.common.BillingRecord;
import cdm.event.common.Transfer;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.observable.asset.Money;
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

public class BillingRecordOnlyExistsValidator implements ValidatorWithArg<BillingRecord, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends BillingRecord> ValidationResult<BillingRecord> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("tradeState", ExistenceChecker.isSet((ReferenceWithMetaTradeState) o.getTradeState()))
				.put("recordTransfer", ExistenceChecker.isSet((Transfer) o.getRecordTransfer()))
				.put("recordStartDate", ExistenceChecker.isSet((Date) o.getRecordStartDate()))
				.put("recordEndDate", ExistenceChecker.isSet((Date) o.getRecordEndDate()))
				.put("minimumFee", ExistenceChecker.isSet((Money) o.getMinimumFee()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("BillingRecord", ValidationType.ONLY_EXISTS, "BillingRecord", path, "");
		}
		return failure("BillingRecord", ValidationType.ONLY_EXISTS, "BillingRecord", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
