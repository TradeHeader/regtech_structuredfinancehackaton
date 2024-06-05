package cdm.product.template.validation.exists;

import cdm.product.template.AmericanExercise;
import cdm.product.template.BermudaExercise;
import cdm.product.template.EuropeanExercise;
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

public class OptionStyleOnlyExistsValidator implements ValidatorWithArg<OptionStyle, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends OptionStyle> ValidationResult<OptionStyle> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("americanExercise", ExistenceChecker.isSet((AmericanExercise) o.getAmericanExercise()))
				.put("bermudaExercise", ExistenceChecker.isSet((BermudaExercise) o.getBermudaExercise()))
				.put("europeanExercise", ExistenceChecker.isSet((EuropeanExercise) o.getEuropeanExercise()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("OptionStyle", ValidationType.ONLY_EXISTS, "OptionStyle", path, "");
		}
		return failure("OptionStyle", ValidationType.ONLY_EXISTS, "OptionStyle", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
