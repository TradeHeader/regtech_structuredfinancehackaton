package cdm.product.template.validation.exists;

import cdm.product.template.ExerciseProcedure;
import cdm.product.template.OptionExercise;
import cdm.product.template.OptionStrike;
import cdm.product.template.OptionStyle;
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

public class OptionExerciseOnlyExistsValidator implements ValidatorWithArg<OptionExercise, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends OptionExercise> ValidationResult<OptionExercise> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("optionStyle", ExistenceChecker.isSet((OptionStyle) o.getOptionStyle()))
				.put("strike", ExistenceChecker.isSet((OptionStrike) o.getStrike()))
				.put("exerciseProcedure", ExistenceChecker.isSet((ExerciseProcedure) o.getExerciseProcedure()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("OptionExercise", ValidationType.ONLY_EXISTS, "OptionExercise", path, "");
		}
		return failure("OptionExercise", ValidationType.ONLY_EXISTS, "OptionExercise", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
