package cdm.event.common.validation.exists;

import cdm.event.common.Transfer;
import cdm.event.common.TransferState;
import cdm.event.common.TransferStatusEnum;
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

public class TransferStateOnlyExistsValidator implements ValidatorWithArg<TransferState, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends TransferState> ValidationResult<TransferState> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("transfer", ExistenceChecker.isSet((Transfer) o.getTransfer()))
				.put("transferStatus", ExistenceChecker.isSet((TransferStatusEnum) o.getTransferStatus()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("TransferState", ValidationType.ONLY_EXISTS, "TransferState", path, "");
		}
		return failure("TransferState", ValidationType.ONLY_EXISTS, "TransferState", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
