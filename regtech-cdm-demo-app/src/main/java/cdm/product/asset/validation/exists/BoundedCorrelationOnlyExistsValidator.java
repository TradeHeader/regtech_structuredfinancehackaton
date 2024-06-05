package cdm.product.asset.validation.exists;

import cdm.product.asset.BoundedCorrelation;
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

public class BoundedCorrelationOnlyExistsValidator implements ValidatorWithArg<BoundedCorrelation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends BoundedCorrelation> ValidationResult<BoundedCorrelation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("minimumBoundaryPercent", ExistenceChecker.isSet((BigDecimal) o.getMinimumBoundaryPercent()))
				.put("maximumBoundaryPercent", ExistenceChecker.isSet((BigDecimal) o.getMaximumBoundaryPercent()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("BoundedCorrelation", ValidationType.ONLY_EXISTS, "BoundedCorrelation", path, "");
		}
		return failure("BoundedCorrelation", ValidationType.ONLY_EXISTS, "BoundedCorrelation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
