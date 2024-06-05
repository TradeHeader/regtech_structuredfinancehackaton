package cdm.event.common.validation.exists;

import cdm.event.common.IndexTransitionInstruction;
import cdm.event.common.Transfer;
import cdm.product.common.settlement.PriceQuantity;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class IndexTransitionInstructionOnlyExistsValidator implements ValidatorWithArg<IndexTransitionInstruction, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends IndexTransitionInstruction> ValidationResult<IndexTransitionInstruction> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("priceQuantity", ExistenceChecker.isSet((List<? extends PriceQuantity>) o.getPriceQuantity()))
				.put("effectiveDate", ExistenceChecker.isSet((Date) o.getEffectiveDate()))
				.put("cashTransfer", ExistenceChecker.isSet((Transfer) o.getCashTransfer()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("IndexTransitionInstruction", ValidationType.ONLY_EXISTS, "IndexTransitionInstruction", path, "");
		}
		return failure("IndexTransitionInstruction", ValidationType.ONLY_EXISTS, "IndexTransitionInstruction", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
