package cdm.product.asset.validation.exists;

import cdm.product.asset.AssetDeliveryProfile;
import cdm.product.asset.AssetDeliveryProfileBlock;
import cdm.product.asset.BankHolidayTreatmentEnum;
import cdm.product.asset.LoadTypeEnum;
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

public class AssetDeliveryProfileOnlyExistsValidator implements ValidatorWithArg<AssetDeliveryProfile, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AssetDeliveryProfile> ValidationResult<AssetDeliveryProfile> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("loadType", ExistenceChecker.isSet((LoadTypeEnum) o.getLoadType()))
				.put("block", ExistenceChecker.isSet((List<? extends AssetDeliveryProfileBlock>) o.getBlock()))
				.put("bankHolidaysTreatment", ExistenceChecker.isSet((BankHolidayTreatmentEnum) o.getBankHolidaysTreatment()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AssetDeliveryProfile", ValidationType.ONLY_EXISTS, "AssetDeliveryProfile", path, "");
		}
		return failure("AssetDeliveryProfile", ValidationType.ONLY_EXISTS, "AssetDeliveryProfile", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
