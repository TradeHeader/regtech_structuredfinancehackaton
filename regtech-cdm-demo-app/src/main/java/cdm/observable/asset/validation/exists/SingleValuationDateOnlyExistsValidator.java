package cdm.observable.asset.validation.exists;

import cdm.observable.asset.SingleValuationDate;
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

public class SingleValuationDateOnlyExistsValidator implements ValidatorWithArg<SingleValuationDate, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends SingleValuationDate> ValidationResult<SingleValuationDate> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("businessDays", ExistenceChecker.isSet((Integer) o.getBusinessDays()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("SingleValuationDate", ValidationType.ONLY_EXISTS, "SingleValuationDate", path, "");
		}
		return failure("SingleValuationDate", ValidationType.ONLY_EXISTS, "SingleValuationDate", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
