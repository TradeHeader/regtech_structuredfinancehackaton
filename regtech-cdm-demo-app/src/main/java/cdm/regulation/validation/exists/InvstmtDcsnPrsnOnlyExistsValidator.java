package cdm.regulation.validation.exists;

import cdm.regulation.InvstmtDcsnPrsn;
import cdm.regulation.Prsn;
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

public class InvstmtDcsnPrsnOnlyExistsValidator implements ValidatorWithArg<InvstmtDcsnPrsn, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends InvstmtDcsnPrsn> ValidationResult<InvstmtDcsnPrsn> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("prsn", ExistenceChecker.isSet((Prsn) o.getPrsn()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("InvstmtDcsnPrsn", ValidationType.ONLY_EXISTS, "InvstmtDcsnPrsn", path, "");
		}
		return failure("InvstmtDcsnPrsn", ValidationType.ONLY_EXISTS, "InvstmtDcsnPrsn", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
