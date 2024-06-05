package cdm.regulation.validation.exists;

import cdm.regulation.FinInstrmGnlAttrbts;
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

public class FinInstrmGnlAttrbtsOnlyExistsValidator implements ValidatorWithArg<FinInstrmGnlAttrbts, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends FinInstrmGnlAttrbts> ValidationResult<FinInstrmGnlAttrbts> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("fullNm", ExistenceChecker.isSet((String) o.getFullNm()))
				.put("clssfctnTp", ExistenceChecker.isSet((String) o.getClssfctnTp()))
				.put("ntnlCcy", ExistenceChecker.isSet((String) o.getNtnlCcy()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("FinInstrmGnlAttrbts", ValidationType.ONLY_EXISTS, "FinInstrmGnlAttrbts", path, "");
		}
		return failure("FinInstrmGnlAttrbts", ValidationType.ONLY_EXISTS, "FinInstrmGnlAttrbts", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
