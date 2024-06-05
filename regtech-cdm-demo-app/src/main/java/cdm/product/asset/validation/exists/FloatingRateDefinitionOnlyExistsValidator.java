package cdm.product.asset.validation.exists;

import cdm.observable.asset.RateObservation;
import cdm.product.asset.FloatingRateDefinition;
import cdm.product.template.Strike;
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

public class FloatingRateDefinitionOnlyExistsValidator implements ValidatorWithArg<FloatingRateDefinition, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FloatingRateDefinition> ValidationResult<FloatingRateDefinition> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("calculatedRate", ExistenceChecker.isSet((BigDecimal) o.getCalculatedRate()))
				.put("rateObservation", ExistenceChecker.isSet((List<? extends RateObservation>) o.getRateObservation()))
				.put("floatingRateMultiplier", ExistenceChecker.isSet((BigDecimal) o.getFloatingRateMultiplier()))
				.put("spread", ExistenceChecker.isSet((BigDecimal) o.getSpread()))
				.put("capRate", ExistenceChecker.isSet((List<? extends Strike>) o.getCapRate()))
				.put("floorRate", ExistenceChecker.isSet((List<? extends Strike>) o.getFloorRate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FloatingRateDefinition", ValidationType.ONLY_EXISTS, "FloatingRateDefinition", path, "");
		}
		return failure("FloatingRateDefinition", ValidationType.ONLY_EXISTS, "FloatingRateDefinition", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
