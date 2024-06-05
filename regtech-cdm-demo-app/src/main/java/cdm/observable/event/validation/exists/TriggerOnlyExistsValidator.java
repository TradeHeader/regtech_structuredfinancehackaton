package cdm.observable.event.validation.exists;

import cdm.observable.event.CreditEvents;
import cdm.observable.event.Trigger;
import cdm.observable.event.TriggerTimeTypeEnum;
import cdm.observable.event.TriggerTypeEnum;
import cdm.observable.event.metafields.ReferenceWithMetaCreditEvents;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class TriggerOnlyExistsValidator implements ValidatorWithArg<Trigger, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Trigger> ValidationResult<Trigger> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("level", ExistenceChecker.isSet((BigDecimal) o.getLevel()))
				.put("levelPercentage", ExistenceChecker.isSet((BigDecimal) o.getLevelPercentage()))
				.put("creditEvents", ExistenceChecker.isSet((CreditEvents) o.getCreditEvents()))
				.put("creditEventsReference", ExistenceChecker.isSet((ReferenceWithMetaCreditEvents) o.getCreditEventsReference()))
				.put("triggerType", ExistenceChecker.isSet((TriggerTypeEnum) o.getTriggerType()))
				.put("triggerTimeType", ExistenceChecker.isSet((TriggerTimeTypeEnum) o.getTriggerTimeType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Trigger", ValidationType.ONLY_EXISTS, "Trigger", path, "");
		}
		return failure("Trigger", ValidationType.ONLY_EXISTS, "Trigger", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
