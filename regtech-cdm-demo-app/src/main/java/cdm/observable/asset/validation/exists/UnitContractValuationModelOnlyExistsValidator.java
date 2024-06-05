package cdm.observable.asset.validation.exists;

import cdm.base.math.Quantity;
import cdm.observable.asset.Money;
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

public class UnitContractValuationModelOnlyExistsValidator implements ValidatorWithArg<UnitContractValuationModel, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends UnitContractValuationModel> ValidationResult<UnitContractValuationModel> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("numberOfUnits", ExistenceChecker.isSet((Quantity) o.getNumberOfUnits()))
				.put("unitPrice", ExistenceChecker.isSet((Money) o.getUnitPrice()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("UnitContractValuationModel", ValidationType.ONLY_EXISTS, "UnitContractValuationModel", path, "");
		}
		return failure("UnitContractValuationModel", ValidationType.ONLY_EXISTS, "UnitContractValuationModel", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
