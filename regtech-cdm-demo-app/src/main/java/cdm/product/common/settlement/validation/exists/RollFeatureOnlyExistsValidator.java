package cdm.product.common.settlement.validation.exists;

import cdm.base.datetime.Offset;
import cdm.product.asset.RollSourceCalendarEnum;
import cdm.product.common.settlement.RollFeature;
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

public class RollFeatureOnlyExistsValidator implements ValidatorWithArg<RollFeature, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends RollFeature> ValidationResult<RollFeature> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("rollSourceCalendar", ExistenceChecker.isSet((RollSourceCalendarEnum) o.getRollSourceCalendar()))
				.put("deliveryDateRollConvention", ExistenceChecker.isSet((Offset) o.getDeliveryDateRollConvention()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("RollFeature", ValidationType.ONLY_EXISTS, "RollFeature", path, "");
		}
		return failure("RollFeature", ValidationType.ONLY_EXISTS, "RollFeature", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
