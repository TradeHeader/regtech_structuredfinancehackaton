package cdm.product.template.validation.exists;

import cdm.observable.asset.metafields.ReferenceWithMetaMoney;
import cdm.product.template.MultipleExercise;
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

public class MultipleExerciseOnlyExistsValidator implements ValidatorWithArg<MultipleExercise, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends MultipleExercise> ValidationResult<MultipleExercise> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("notionaReference", ExistenceChecker.isSet((ReferenceWithMetaMoney) o.getNotionaReference()))
				.put("integralMultipleAmount", ExistenceChecker.isSet((BigDecimal) o.getIntegralMultipleAmount()))
				.put("minimumNotionalAmount", ExistenceChecker.isSet((BigDecimal) o.getMinimumNotionalAmount()))
				.put("minimumNumberOfOptions", ExistenceChecker.isSet((Integer) o.getMinimumNumberOfOptions()))
				.put("maximumNotionalAmount", ExistenceChecker.isSet((BigDecimal) o.getMaximumNotionalAmount()))
				.put("maximumNumberOfOptions", ExistenceChecker.isSet((Integer) o.getMaximumNumberOfOptions()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("MultipleExercise", ValidationType.ONLY_EXISTS, "MultipleExercise", path, "");
		}
		return failure("MultipleExercise", ValidationType.ONLY_EXISTS, "MultipleExercise", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
