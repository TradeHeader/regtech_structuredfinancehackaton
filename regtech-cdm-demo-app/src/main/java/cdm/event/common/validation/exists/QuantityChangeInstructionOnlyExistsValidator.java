package cdm.event.common.validation.exists;

import cdm.base.math.QuantityChangeDirectionEnum;
import cdm.base.staticdata.identifier.Identifier;
import cdm.event.common.QuantityChangeInstruction;
import cdm.product.common.settlement.PriceQuantity;
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

public class QuantityChangeInstructionOnlyExistsValidator implements ValidatorWithArg<QuantityChangeInstruction, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends QuantityChangeInstruction> ValidationResult<QuantityChangeInstruction> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("change", ExistenceChecker.isSet((List<? extends PriceQuantity>) o.getChange()))
				.put("direction", ExistenceChecker.isSet((QuantityChangeDirectionEnum) o.getDirection()))
				.put("lotIdentifier", ExistenceChecker.isSet((List<? extends Identifier>) o.getLotIdentifier()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("QuantityChangeInstruction", ValidationType.ONLY_EXISTS, "QuantityChangeInstruction", path, "");
		}
		return failure("QuantityChangeInstruction", ValidationType.ONLY_EXISTS, "QuantityChangeInstruction", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
