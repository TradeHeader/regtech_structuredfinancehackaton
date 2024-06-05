package cdm.product.common.schedule.validation.exists;

import cdm.base.datetime.PeriodicDates;
import cdm.product.common.schedule.ObservationDates;
import cdm.product.common.schedule.ObservationSchedule;
import cdm.product.common.schedule.ParametricDates;
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

public class ObservationDatesOnlyExistsValidator implements ValidatorWithArg<ObservationDates, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ObservationDates> ValidationResult<ObservationDates> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("observationSchedule", ExistenceChecker.isSet((ObservationSchedule) o.getObservationSchedule()))
				.put("periodicSchedule", ExistenceChecker.isSet((PeriodicDates) o.getPeriodicSchedule()))
				.put("parametricDates", ExistenceChecker.isSet((ParametricDates) o.getParametricDates()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ObservationDates", ValidationType.ONLY_EXISTS, "ObservationDates", path, "");
		}
		return failure("ObservationDates", ValidationType.ONLY_EXISTS, "ObservationDates", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
