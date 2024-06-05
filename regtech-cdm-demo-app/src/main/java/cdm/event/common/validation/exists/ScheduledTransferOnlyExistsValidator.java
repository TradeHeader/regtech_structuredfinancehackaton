package cdm.event.common.validation.exists;

import cdm.event.common.CorporateActionTypeEnum;
import cdm.event.common.ScheduledTransfer;
import cdm.product.common.settlement.ScheduledTransferEnum;
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

public class ScheduledTransferOnlyExistsValidator implements ValidatorWithArg<ScheduledTransfer, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ScheduledTransfer> ValidationResult<ScheduledTransfer> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("transferType", ExistenceChecker.isSet((ScheduledTransferEnum) o.getTransferType()))
				.put("corporateActionTransferType", ExistenceChecker.isSet((CorporateActionTypeEnum) o.getCorporateActionTransferType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ScheduledTransfer", ValidationType.ONLY_EXISTS, "ScheduledTransfer", path, "");
		}
		return failure("ScheduledTransfer", ValidationType.ONLY_EXISTS, "ScheduledTransfer", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
