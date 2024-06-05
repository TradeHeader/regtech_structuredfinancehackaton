package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.staticdata.asset.common.DebtEconomics;
import cdm.base.staticdata.asset.common.DebtInterestEnum;
import cdm.base.staticdata.asset.common.DebtPrincipalEnum;
import cdm.base.staticdata.asset.common.DebtSeniorityEnum;
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

public class DebtEconomicsOnlyExistsValidator implements ValidatorWithArg<DebtEconomics, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DebtEconomics> ValidationResult<DebtEconomics> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("debtSeniority", ExistenceChecker.isSet((DebtSeniorityEnum) o.getDebtSeniority()))
				.put("debtInterest", ExistenceChecker.isSet((DebtInterestEnum) o.getDebtInterest()))
				.put("debtPrincipal", ExistenceChecker.isSet((DebtPrincipalEnum) o.getDebtPrincipal()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DebtEconomics", ValidationType.ONLY_EXISTS, "DebtEconomics", path, "");
		}
		return failure("DebtEconomics", ValidationType.ONLY_EXISTS, "DebtEconomics", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
