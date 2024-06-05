package cdm.product.template.validation.exists;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.product.template.CalendarSpread;
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

public class CalendarSpreadOnlyExistsValidator implements ValidatorWithArg<CalendarSpread, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CalendarSpread> ValidationResult<CalendarSpread> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("expirationDateTwo", ExistenceChecker.isSet((AdjustableOrRelativeDate) o.getExpirationDateTwo()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CalendarSpread", ValidationType.ONLY_EXISTS, "CalendarSpread", path, "");
		}
		return failure("CalendarSpread", ValidationType.ONLY_EXISTS, "CalendarSpread", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
