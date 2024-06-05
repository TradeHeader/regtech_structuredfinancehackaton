package cdm.product.common.schedule.validation.exists;

import cdm.product.common.schedule.WeightedAveragingObservation;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class WeightedAveragingObservationOnlyExistsValidator implements ValidatorWithArg<WeightedAveragingObservation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends WeightedAveragingObservation> ValidationResult<WeightedAveragingObservation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("dateTime", ExistenceChecker.isSet((ZonedDateTime) o.getDateTime()))
				.put("observationNumber", ExistenceChecker.isSet((Integer) o.getObservationNumber()))
				.put("weight", ExistenceChecker.isSet((BigDecimal) o.getWeight()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("WeightedAveragingObservation", ValidationType.ONLY_EXISTS, "WeightedAveragingObservation", path, "");
		}
		return failure("WeightedAveragingObservation", ValidationType.ONLY_EXISTS, "WeightedAveragingObservation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
