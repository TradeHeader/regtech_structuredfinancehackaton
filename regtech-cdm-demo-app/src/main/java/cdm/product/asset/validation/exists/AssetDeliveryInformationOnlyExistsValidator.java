package cdm.product.asset.validation.exists;

import cdm.base.math.Quantity;
import cdm.base.staticdata.identifier.LocationIdentifier;
import cdm.product.asset.AssetDeliveryInformation;
import cdm.product.asset.AssetDeliveryPeriods;
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

public class AssetDeliveryInformationOnlyExistsValidator implements ValidatorWithArg<AssetDeliveryInformation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AssetDeliveryInformation> ValidationResult<AssetDeliveryInformation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("periods", ExistenceChecker.isSet((AssetDeliveryPeriods) o.getPeriods()))
				.put("location", ExistenceChecker.isSet((List<? extends LocationIdentifier>) o.getLocation()))
				.put("deliveryCapacity", ExistenceChecker.isSet((Quantity) o.getDeliveryCapacity()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AssetDeliveryInformation", ValidationType.ONLY_EXISTS, "AssetDeliveryInformation", path, "");
		}
		return failure("AssetDeliveryInformation", ValidationType.ONLY_EXISTS, "AssetDeliveryInformation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
