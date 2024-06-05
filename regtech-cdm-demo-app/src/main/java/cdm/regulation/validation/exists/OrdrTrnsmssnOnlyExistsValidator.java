package cdm.regulation.validation.exists;

import cdm.regulation.OrdrTrnsmssn;
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

public class OrdrTrnsmssnOnlyExistsValidator implements ValidatorWithArg<OrdrTrnsmssn, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends OrdrTrnsmssn> ValidationResult<OrdrTrnsmssn> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("trnsmssnInd", ExistenceChecker.isSet((String) o.getTrnsmssnInd()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("OrdrTrnsmssn", ValidationType.ONLY_EXISTS, "OrdrTrnsmssn", path, "");
		}
		return failure("OrdrTrnsmssn", ValidationType.ONLY_EXISTS, "OrdrTrnsmssn", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
