package cdm.observable.asset.validation.exists;

import cdm.observable.asset.MultipleValuationDates;
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

public class MultipleValuationDatesOnlyExistsValidator implements ValidatorWithArg<MultipleValuationDates, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends MultipleValuationDates> ValidationResult<MultipleValuationDates> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("businessDays", ExistenceChecker.isSet((Integer) o.getBusinessDays()))
				.put("businessDaysThereafter", ExistenceChecker.isSet((Integer) o.getBusinessDaysThereafter()))
				.put("numberValuationDates", ExistenceChecker.isSet((Integer) o.getNumberValuationDates()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("MultipleValuationDates", ValidationType.ONLY_EXISTS, "MultipleValuationDates", path, "");
		}
		return failure("MultipleValuationDates", ValidationType.ONLY_EXISTS, "MultipleValuationDates", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
