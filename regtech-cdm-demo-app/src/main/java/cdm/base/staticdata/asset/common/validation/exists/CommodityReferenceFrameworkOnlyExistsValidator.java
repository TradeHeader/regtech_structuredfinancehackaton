package cdm.base.staticdata.asset.common.validation.exists;

import cdm.base.math.CapacityUnitEnum;
import cdm.base.math.WeatherUnitEnum;
import cdm.base.staticdata.asset.common.CommodityReferenceFramework;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CommodityReferenceFrameworkOnlyExistsValidator implements ValidatorWithArg<CommodityReferenceFramework, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CommodityReferenceFramework> ValidationResult<CommodityReferenceFramework> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("commodityName", ExistenceChecker.isSet((String) o.getCommodityName()))
				.put("capacityUnit", ExistenceChecker.isSet((CapacityUnitEnum) o.getCapacityUnit()))
				.put("weatherUnit", ExistenceChecker.isSet((WeatherUnitEnum) o.getWeatherUnit()))
				.put("currency", ExistenceChecker.isSet((FieldWithMetaString) o.getCurrency()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CommodityReferenceFramework", ValidationType.ONLY_EXISTS, "CommodityReferenceFramework", path, "");
		}
		return failure("CommodityReferenceFramework", ValidationType.ONLY_EXISTS, "CommodityReferenceFramework", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
