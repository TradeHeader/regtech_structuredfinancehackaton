package cdm.product.common.schedule.validation.exists;

import cdm.base.datetime.RelativeDateOffset;
import cdm.product.common.schedule.InitialFixingDate;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class InitialFixingDateOnlyExistsValidator implements ValidatorWithArg<InitialFixingDate, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends InitialFixingDate> ValidationResult<InitialFixingDate> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("relativeDateOffset", ExistenceChecker.isSet((RelativeDateOffset) o.getRelativeDateOffset()))
				.put("initialFixingDate", ExistenceChecker.isSet((Date) o.getInitialFixingDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("InitialFixingDate", ValidationType.ONLY_EXISTS, "InitialFixingDate", path, "");
		}
		return failure("InitialFixingDate", ValidationType.ONLY_EXISTS, "InitialFixingDate", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
