package cdm.product.collateral.validation.exists;

import cdm.product.collateral.DeliveryAmount;
import cdm.product.collateral.InterestAmountApplication;
import cdm.product.collateral.ReturnAmount;
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

public class InterestAmountApplicationOnlyExistsValidator implements ValidatorWithArg<InterestAmountApplication, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends InterestAmountApplication> ValidationResult<InterestAmountApplication> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("returnAmount", ExistenceChecker.isSet((ReturnAmount) o.getReturnAmount()))
				.put("deliveryAmount", ExistenceChecker.isSet((DeliveryAmount) o.getDeliveryAmount()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("InterestAmountApplication", ValidationType.ONLY_EXISTS, "InterestAmountApplication", path, "");
		}
		return failure("InterestAmountApplication", ValidationType.ONLY_EXISTS, "InterestAmountApplication", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
