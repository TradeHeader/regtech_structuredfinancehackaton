package cdm.product.template.validation.exists;

import cdm.product.template.AutomaticExercise;
import cdm.product.template.ExerciseProcedure;
import cdm.product.template.ManualExercise;
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

public class ExerciseProcedureOnlyExistsValidator implements ValidatorWithArg<ExerciseProcedure, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ExerciseProcedure> ValidationResult<ExerciseProcedure> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("manualExercise", ExistenceChecker.isSet((ManualExercise) o.getManualExercise()))
				.put("automaticExercise", ExistenceChecker.isSet((AutomaticExercise) o.getAutomaticExercise()))
				.put("followUpConfirmation", ExistenceChecker.isSet((Boolean) o.getFollowUpConfirmation()))
				.put("limitedRightToConfirm", ExistenceChecker.isSet((Boolean) o.getLimitedRightToConfirm()))
				.put("splitTicket", ExistenceChecker.isSet((Boolean) o.getSplitTicket()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ExerciseProcedure", ValidationType.ONLY_EXISTS, "ExerciseProcedure", path, "");
		}
		return failure("ExerciseProcedure", ValidationType.ONLY_EXISTS, "ExerciseProcedure", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
