package cdm.base.datetime.validation.exists;

import cdm.base.datetime.TimeZone;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class TimeZoneOnlyExistsValidator implements ValidatorWithArg<TimeZone, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends TimeZone> ValidationResult<TimeZone> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("time", ExistenceChecker.isSet((LocalTime) o.getTime()))
				.put("location", ExistenceChecker.isSet((FieldWithMetaString) o.getLocation()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("TimeZone", ValidationType.ONLY_EXISTS, "TimeZone", path, "");
		}
		return failure("TimeZone", ValidationType.ONLY_EXISTS, "TimeZone", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
