package cdm.event.common.validation.exists;

import cdm.base.staticdata.identifier.IdentifiedList;
import cdm.base.staticdata.party.LegalEntity;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.ExecutionTypeEnum;
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

public class ExecutionDetailsOnlyExistsValidator implements ValidatorWithArg<ExecutionDetails, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ExecutionDetails> ValidationResult<ExecutionDetails> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("executionType", ExistenceChecker.isSet((ExecutionTypeEnum) o.getExecutionType()))
				.put("executionVenue", ExistenceChecker.isSet((LegalEntity) o.getExecutionVenue()))
				.put("packageReference", ExistenceChecker.isSet((IdentifiedList) o.getPackageReference()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ExecutionDetails", ValidationType.ONLY_EXISTS, "ExecutionDetails", path, "");
		}
		return failure("ExecutionDetails", ValidationType.ONLY_EXISTS, "ExecutionDetails", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
