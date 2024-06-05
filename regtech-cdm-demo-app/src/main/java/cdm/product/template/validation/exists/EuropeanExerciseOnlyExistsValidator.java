package cdm.product.template.validation.exists;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableOrRelativeDates;
import cdm.base.datetime.BusinessCenterTime;
import cdm.product.template.EuropeanExercise;
import cdm.product.template.ExerciseFee;
import cdm.product.template.ExpirationTimeTypeEnum;
import cdm.product.template.PartialExercise;
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

public class EuropeanExerciseOnlyExistsValidator implements ValidatorWithArg<EuropeanExercise, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends EuropeanExercise> ValidationResult<EuropeanExercise> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("expirationDate", ExistenceChecker.isSet((List<? extends AdjustableOrRelativeDate>) o.getExpirationDate()))
				.put("relevantUnderlyingDate", ExistenceChecker.isSet((AdjustableOrRelativeDates) o.getRelevantUnderlyingDate()))
				.put("earliestExerciseTime", ExistenceChecker.isSet((BusinessCenterTime) o.getEarliestExerciseTime()))
				.put("expirationTime", ExistenceChecker.isSet((BusinessCenterTime) o.getExpirationTime()))
				.put("expirationTimeType", ExistenceChecker.isSet((ExpirationTimeTypeEnum) o.getExpirationTimeType()))
				.put("partialExercise", ExistenceChecker.isSet((PartialExercise) o.getPartialExercise()))
				.put("exerciseFee", ExistenceChecker.isSet((ExerciseFee) o.getExerciseFee()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("EuropeanExercise", ValidationType.ONLY_EXISTS, "EuropeanExercise", path, "");
		}
		return failure("EuropeanExercise", ValidationType.ONLY_EXISTS, "EuropeanExercise", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
