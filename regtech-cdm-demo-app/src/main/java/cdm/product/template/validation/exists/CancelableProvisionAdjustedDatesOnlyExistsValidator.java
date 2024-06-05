package cdm.product.template.validation.exists;

import cdm.product.template.CancelableProvisionAdjustedDates;
import cdm.product.template.CancellationEvent;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CancelableProvisionAdjustedDatesOnlyExistsValidator implements ValidatorWithArg<CancelableProvisionAdjustedDates, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CancelableProvisionAdjustedDates> ValidationResult<CancelableProvisionAdjustedDates> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("cancellationEvent", ExistenceChecker.isSet((List<? extends CancellationEvent>) o.getCancellationEvent()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CancelableProvisionAdjustedDates", ValidationType.ONLY_EXISTS, "CancelableProvisionAdjustedDates", path, "");
		}
		return failure("CancelableProvisionAdjustedDates", ValidationType.ONLY_EXISTS, "CancelableProvisionAdjustedDates", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
