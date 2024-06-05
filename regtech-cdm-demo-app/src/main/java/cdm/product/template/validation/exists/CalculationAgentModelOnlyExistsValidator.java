package cdm.product.template.validation.exists;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.observable.asset.CalculationAgent;
import cdm.product.template.CalculationAgentModel;
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

public class CalculationAgentModelOnlyExistsValidator implements ValidatorWithArg<CalculationAgentModel, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CalculationAgentModel> ValidationResult<CalculationAgentModel> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("calculationAgent", ExistenceChecker.isSet((CalculationAgent) o.getCalculationAgent()))
				.put("calculationAgentBusinessCenter", ExistenceChecker.isSet((BusinessCenterEnum) o.getCalculationAgentBusinessCenter()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CalculationAgentModel", ValidationType.ONLY_EXISTS, "CalculationAgentModel", path, "");
		}
		return failure("CalculationAgentModel", ValidationType.ONLY_EXISTS, "CalculationAgentModel", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
