package cdm.product.common.settlement.validation.exists;

import cdm.product.common.settlement.ComputedAmount;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ComputedAmountOnlyExistsValidator implements ValidatorWithArg<ComputedAmount, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ComputedAmount> ValidationResult<ComputedAmount> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("callFunction", ExistenceChecker.isSet((String) o.getCallFunction()))
				.put("amount", ExistenceChecker.isSet((BigDecimal) o.getAmount()))
				.put("currency", ExistenceChecker.isSet((FieldWithMetaString) o.getCurrency()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ComputedAmount", ValidationType.ONLY_EXISTS, "ComputedAmount", path, "");
		}
		return failure("ComputedAmount", ValidationType.ONLY_EXISTS, "ComputedAmount", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
