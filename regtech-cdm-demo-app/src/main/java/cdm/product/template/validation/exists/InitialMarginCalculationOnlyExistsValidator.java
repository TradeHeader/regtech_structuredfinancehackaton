package cdm.product.template.validation.exists;

import cdm.product.template.InitialMarginCalculation;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class InitialMarginCalculationOnlyExistsValidator implements ValidatorWithArg<InitialMarginCalculation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends InitialMarginCalculation> ValidationResult<InitialMarginCalculation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("marginRatio", ExistenceChecker.isSet((BigDecimal) o.getMarginRatio()))
				.put("marginRatioThreshold", ExistenceChecker.isSet((List<BigDecimal>) o.getMarginRatioThreshold()))
				.put("haircut", ExistenceChecker.isSet((BigDecimal) o.getHaircut()))
				.put("haircutThreshold", ExistenceChecker.isSet((List<BigDecimal>) o.getHaircutThreshold()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("InitialMarginCalculation", ValidationType.ONLY_EXISTS, "InitialMarginCalculation", path, "");
		}
		return failure("InitialMarginCalculation", ValidationType.ONLY_EXISTS, "InitialMarginCalculation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
