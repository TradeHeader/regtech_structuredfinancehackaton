package cdm.product.asset.validation.exists;

import cdm.product.asset.BoundedVariance;
import cdm.product.asset.RealisedVarianceMethodEnum;
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

public class BoundedVarianceOnlyExistsValidator implements ValidatorWithArg<BoundedVariance, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends BoundedVariance> ValidationResult<BoundedVariance> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("realisedVarianceMethod", ExistenceChecker.isSet((RealisedVarianceMethodEnum) o.getRealisedVarianceMethod()))
				.put("daysInRangeAdjustment", ExistenceChecker.isSet((Boolean) o.getDaysInRangeAdjustment()))
				.put("upperBarrier", ExistenceChecker.isSet((BigDecimal) o.getUpperBarrier()))
				.put("lowerBarrier", ExistenceChecker.isSet((BigDecimal) o.getLowerBarrier()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("BoundedVariance", ValidationType.ONLY_EXISTS, "BoundedVariance", path, "");
		}
		return failure("BoundedVariance", ValidationType.ONLY_EXISTS, "BoundedVariance", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
