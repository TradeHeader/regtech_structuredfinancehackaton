package cdm.event.common.validation.exists;

import cdm.event.common.BillingSummaryInstruction;
import cdm.event.common.RecordAmountTypeEnum;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class BillingSummaryInstructionOnlyExistsValidator implements ValidatorWithArg<BillingSummaryInstruction, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends BillingSummaryInstruction> ValidationResult<BillingSummaryInstruction> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("summaryAmountType", ExistenceChecker.isSet((RecordAmountTypeEnum) o.getSummaryAmountType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("BillingSummaryInstruction", ValidationType.ONLY_EXISTS, "BillingSummaryInstruction", path, "");
		}
		return failure("BillingSummaryInstruction", ValidationType.ONLY_EXISTS, "BillingSummaryInstruction", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
