package cdm.product.asset.floatingrate.validation.exists;

import cdm.product.asset.floatingrate.FloatingRateProcessingDetails;
import cdm.product.asset.floatingrate.FloatingRateProcessingParameters;
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

public class FloatingRateProcessingDetailsOnlyExistsValidator implements ValidatorWithArg<FloatingRateProcessingDetails, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FloatingRateProcessingDetails> ValidationResult<FloatingRateProcessingDetails> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("rawRate", ExistenceChecker.isSet((BigDecimal) o.getRawRate()))
				.put("processingParameters", ExistenceChecker.isSet((FloatingRateProcessingParameters) o.getProcessingParameters()))
				.put("processedRate", ExistenceChecker.isSet((BigDecimal) o.getProcessedRate()))
				.put("spreadExclusiveRate", ExistenceChecker.isSet((BigDecimal) o.getSpreadExclusiveRate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FloatingRateProcessingDetails", ValidationType.ONLY_EXISTS, "FloatingRateProcessingDetails", path, "");
		}
		return failure("FloatingRateProcessingDetails", ValidationType.ONLY_EXISTS, "FloatingRateProcessingDetails", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
