package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.staticdata.asset.common.DebtClassEnum;
import cdm.base.staticdata.asset.common.DebtEconomics;
import cdm.base.staticdata.asset.common.DebtType;
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

public class DebtTypeOnlyExistsValidator implements ValidatorWithArg<DebtType, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DebtType> ValidationResult<DebtType> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("debtClass", ExistenceChecker.isSet((DebtClassEnum) o.getDebtClass()))
				.put("debtEconomics", ExistenceChecker.isSet((List<? extends DebtEconomics>) o.getDebtEconomics()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DebtType", ValidationType.ONLY_EXISTS, "DebtType", path, "");
		}
		return failure("DebtType", ValidationType.ONLY_EXISTS, "DebtType", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
