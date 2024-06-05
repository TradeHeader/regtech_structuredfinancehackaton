package cdm.product.asset.validation.exists;

import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.product.asset.SpreadSchedule;
import cdm.product.asset.metafields.FieldWithMetaSpreadScheduleTypeEnum;
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

public class SpreadScheduleOnlyExistsValidator implements ValidatorWithArg<SpreadSchedule, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SpreadSchedule> ValidationResult<SpreadSchedule> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("price", ExistenceChecker.isSet((ReferenceWithMetaPriceSchedule) o.getPrice()))
				.put("spreadScheduleType", ExistenceChecker.isSet((FieldWithMetaSpreadScheduleTypeEnum) o.getSpreadScheduleType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SpreadSchedule", ValidationType.ONLY_EXISTS, "SpreadSchedule", path, "");
		}
		return failure("SpreadSchedule", ValidationType.ONLY_EXISTS, "SpreadSchedule", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
