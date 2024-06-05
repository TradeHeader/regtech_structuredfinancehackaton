package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.datetime.AdjustableDate;
import cdm.base.datetime.Offset;
import cdm.base.staticdata.asset.common.DeliveryDateParameters;
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

public class DeliveryDateParametersOnlyExistsValidator implements ValidatorWithArg<DeliveryDateParameters, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends DeliveryDateParameters> ValidationResult<DeliveryDateParameters> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("deliveryNearby", ExistenceChecker.isSet((Offset) o.getDeliveryNearby()))
				.put("deliveryDate", ExistenceChecker.isSet((AdjustableDate) o.getDeliveryDate()))
				.put("deliveryDateRollConvention", ExistenceChecker.isSet((Offset) o.getDeliveryDateRollConvention()))
				.put("deliveryDateExpirationConvention", ExistenceChecker.isSet((Offset) o.getDeliveryDateExpirationConvention()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("DeliveryDateParameters", ValidationType.ONLY_EXISTS, "DeliveryDateParameters", path, "");
		}
		return failure("DeliveryDateParameters", ValidationType.ONLY_EXISTS, "DeliveryDateParameters", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
