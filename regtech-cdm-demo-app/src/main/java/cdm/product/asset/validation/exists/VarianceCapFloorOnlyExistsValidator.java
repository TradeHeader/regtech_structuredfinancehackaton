package cdm.product.asset.validation.exists;

import cdm.product.asset.BoundedVariance;
import cdm.product.asset.VarianceCapFloor;
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

public class VarianceCapFloorOnlyExistsValidator implements ValidatorWithArg<VarianceCapFloor, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends VarianceCapFloor> ValidationResult<VarianceCapFloor> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("varianceCap", ExistenceChecker.isSet((Boolean) o.getVarianceCap()))
				.put("unadjustedVarianceCap", ExistenceChecker.isSet((BigDecimal) o.getUnadjustedVarianceCap()))
				.put("boundedVariance", ExistenceChecker.isSet((BoundedVariance) o.getBoundedVariance()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("VarianceCapFloor", ValidationType.ONLY_EXISTS, "VarianceCapFloor", path, "");
		}
		return failure("VarianceCapFloor", ValidationType.ONLY_EXISTS, "VarianceCapFloor", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
