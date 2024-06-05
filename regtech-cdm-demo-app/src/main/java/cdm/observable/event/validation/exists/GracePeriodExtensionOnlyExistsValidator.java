package cdm.observable.event.validation.exists;

import cdm.base.datetime.Offset;
import cdm.observable.event.GracePeriodExtension;
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

public class GracePeriodExtensionOnlyExistsValidator implements ValidatorWithArg<GracePeriodExtension, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends GracePeriodExtension> ValidationResult<GracePeriodExtension> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("applicable", ExistenceChecker.isSet((Boolean) o.getApplicable()))
				.put("gracePeriod", ExistenceChecker.isSet((Offset) o.getGracePeriod()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("GracePeriodExtension", ValidationType.ONLY_EXISTS, "GracePeriodExtension", path, "");
		}
		return failure("GracePeriodExtension", ValidationType.ONLY_EXISTS, "GracePeriodExtension", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
