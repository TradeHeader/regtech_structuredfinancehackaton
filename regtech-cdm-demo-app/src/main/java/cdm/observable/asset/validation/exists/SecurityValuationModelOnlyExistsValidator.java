package cdm.observable.asset.validation.exists;

import cdm.observable.asset.BondValuationModel;
import cdm.observable.asset.SecurityValuationModel;
import cdm.observable.asset.UnitContractValuationModel;
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

public class SecurityValuationModelOnlyExistsValidator implements ValidatorWithArg<SecurityValuationModel, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SecurityValuationModel> ValidationResult<SecurityValuationModel> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("bondValuationModel", ExistenceChecker.isSet((BondValuationModel) o.getBondValuationModel()))
				.put("unitContractValuationModel", ExistenceChecker.isSet((UnitContractValuationModel) o.getUnitContractValuationModel()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SecurityValuationModel", ValidationType.ONLY_EXISTS, "SecurityValuationModel", path, "");
		}
		return failure("SecurityValuationModel", ValidationType.ONLY_EXISTS, "SecurityValuationModel", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
