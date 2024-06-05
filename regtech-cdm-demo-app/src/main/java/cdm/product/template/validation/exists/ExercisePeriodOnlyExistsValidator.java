package cdm.product.template.validation.exists;

import cdm.base.datetime.Period;
import cdm.product.template.ExercisePeriod;
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

public class ExercisePeriodOnlyExistsValidator implements ValidatorWithArg<ExercisePeriod, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ExercisePeriod> ValidationResult<ExercisePeriod> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("earliestExerciseDateTenor", ExistenceChecker.isSet((Period) o.getEarliestExerciseDateTenor()))
				.put("exerciseFrequency", ExistenceChecker.isSet((Period) o.getExerciseFrequency()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ExercisePeriod", ValidationType.ONLY_EXISTS, "ExercisePeriod", path, "");
		}
		return failure("ExercisePeriod", ValidationType.ONLY_EXISTS, "ExercisePeriod", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
