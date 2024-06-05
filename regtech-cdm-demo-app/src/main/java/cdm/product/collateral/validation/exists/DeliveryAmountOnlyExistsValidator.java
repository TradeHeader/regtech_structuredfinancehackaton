package cdm.product.collateral.validation.exists;

import cdm.product.collateral.DeliveryAmount;
import cdm.product.collateral.DeliveryAmountElectionEnum;
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

public class DeliveryAmountOnlyExistsValidator implements ValidatorWithArg<DeliveryAmount, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DeliveryAmount> ValidationResult<DeliveryAmount> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("standardElection", ExistenceChecker.isSet((DeliveryAmountElectionEnum) o.getStandardElection()))
				.put("customElection", ExistenceChecker.isSet((String) o.getCustomElection()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DeliveryAmount", ValidationType.ONLY_EXISTS, "DeliveryAmount", path, "");
		}
		return failure("DeliveryAmount", ValidationType.ONLY_EXISTS, "DeliveryAmount", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
