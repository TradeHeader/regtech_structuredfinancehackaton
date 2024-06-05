package cdm.observable.asset.validation.exists;

import cdm.observable.asset.FallbackReferencePrice;
import cdm.observable.asset.PriceSourceDisruption;
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

public class PriceSourceDisruptionOnlyExistsValidator implements ValidatorWithArg<PriceSourceDisruption, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends PriceSourceDisruption> ValidationResult<PriceSourceDisruption> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("fallbackReferencePrice", ExistenceChecker.isSet((FallbackReferencePrice) o.getFallbackReferencePrice()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("PriceSourceDisruption", ValidationType.ONLY_EXISTS, "PriceSourceDisruption", path, "");
		}
		return failure("PriceSourceDisruption", ValidationType.ONLY_EXISTS, "PriceSourceDisruption", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
