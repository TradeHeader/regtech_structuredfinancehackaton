package cdm.event.common.validation.exists;

import cdm.base.datetime.AdjustableOrAdjustedDate;
import cdm.base.datetime.BusinessCenterTime;
import cdm.event.common.ExerciseInstruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.TradeIdentifier;
import cdm.product.template.metafields.ReferenceWithMetaOptionPayout;
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

public class ExerciseInstructionOnlyExistsValidator implements ValidatorWithArg<ExerciseInstruction, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ExerciseInstruction> ValidationResult<ExerciseInstruction> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("exerciseQuantity", ExistenceChecker.isSet((PrimitiveInstruction) o.getExerciseQuantity()))
				.put("exerciseOption", ExistenceChecker.isSet((ReferenceWithMetaOptionPayout) o.getExerciseOption()))
				.put("exerciseDate", ExistenceChecker.isSet((AdjustableOrAdjustedDate) o.getExerciseDate()))
				.put("exerciseTime", ExistenceChecker.isSet((BusinessCenterTime) o.getExerciseTime()))
				.put("replacementTradeIdentifier", ExistenceChecker.isSet((List<? extends TradeIdentifier>) o.getReplacementTradeIdentifier()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ExerciseInstruction", ValidationType.ONLY_EXISTS, "ExerciseInstruction", path, "");
		}
		return failure("ExerciseInstruction", ValidationType.ONLY_EXISTS, "ExerciseInstruction", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
