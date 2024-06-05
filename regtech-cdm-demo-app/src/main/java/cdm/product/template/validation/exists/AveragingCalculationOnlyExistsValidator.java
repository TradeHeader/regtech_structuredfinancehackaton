package cdm.product.template.validation.exists;

import cdm.base.math.AveragingCalculationMethod;
import cdm.base.math.Rounding;
import cdm.product.template.AveragingCalculation;
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

public class AveragingCalculationOnlyExistsValidator implements ValidatorWithArg<AveragingCalculation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AveragingCalculation> ValidationResult<AveragingCalculation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("averagingMethod", ExistenceChecker.isSet((AveragingCalculationMethod) o.getAveragingMethod()))
				.put("precision", ExistenceChecker.isSet((Rounding) o.getPrecision()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AveragingCalculation", ValidationType.ONLY_EXISTS, "AveragingCalculation", path, "");
		}
		return failure("AveragingCalculation", ValidationType.ONLY_EXISTS, "AveragingCalculation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
