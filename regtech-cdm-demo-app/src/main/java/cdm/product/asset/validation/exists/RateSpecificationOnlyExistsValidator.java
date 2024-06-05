package cdm.product.asset.validation.exists;

import cdm.product.asset.FixedRateSpecification;
import cdm.product.asset.FloatingRateSpecification;
import cdm.product.asset.InflationRateSpecification;
import cdm.product.asset.RateSpecification;
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

public class RateSpecificationOnlyExistsValidator implements ValidatorWithArg<RateSpecification, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends RateSpecification> ValidationResult<RateSpecification> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("fixedRate", ExistenceChecker.isSet((FixedRateSpecification) o.getFixedRate()))
				.put("floatingRate", ExistenceChecker.isSet((FloatingRateSpecification) o.getFloatingRate()))
				.put("inflationRate", ExistenceChecker.isSet((InflationRateSpecification) o.getInflationRate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("RateSpecification", ValidationType.ONLY_EXISTS, "RateSpecification", path, "");
		}
		return failure("RateSpecification", ValidationType.ONLY_EXISTS, "RateSpecification", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
