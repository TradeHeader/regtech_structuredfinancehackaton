package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.staticdata.asset.common.Equity;
import cdm.base.staticdata.asset.common.ProductIdentifier;
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

public class EquityOnlyExistsValidator implements ValidatorWithArg<Equity, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Equity> ValidationResult<Equity> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("productIdentifier", ExistenceChecker.isSet((ProductIdentifier) o.getProductIdentifier()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Equity", ValidationType.ONLY_EXISTS, "Equity", path, "");
		}
		return failure("Equity", ValidationType.ONLY_EXISTS, "Equity", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
