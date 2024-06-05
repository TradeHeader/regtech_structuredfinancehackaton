package cdm.product.template.validation.exists;

import cdm.product.template.OptionStrike;
import cdm.product.template.StrikeSpread;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class StrikeSpreadOnlyExistsValidator implements ValidatorWithArg<StrikeSpread, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends StrikeSpread> ValidationResult<StrikeSpread> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("upperStrike", ExistenceChecker.isSet((OptionStrike) o.getUpperStrike()))
				.put("upperStrikeNumberOfOptions", ExistenceChecker.isSet((BigDecimal) o.getUpperStrikeNumberOfOptions()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("StrikeSpread", ValidationType.ONLY_EXISTS, "StrikeSpread", path, "");
		}
		return failure("StrikeSpread", ValidationType.ONLY_EXISTS, "StrikeSpread", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
