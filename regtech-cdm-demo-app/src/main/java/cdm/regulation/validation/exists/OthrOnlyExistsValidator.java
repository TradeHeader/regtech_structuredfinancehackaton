package cdm.regulation.validation.exists;

import cdm.regulation.DerivInstrmAttrbts;
import cdm.regulation.FinInstrmGnlAttrbts;
import cdm.regulation.Othr;
import cdm.regulation.SchmeNm;
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

public class OthrOnlyExistsValidator implements ValidatorWithArg<Othr, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Othr> ValidationResult<Othr> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("finInstrmGnlAttrbts", ExistenceChecker.isSet((FinInstrmGnlAttrbts) o.getFinInstrmGnlAttrbts()))
				.put("derivInstrmAttrbts", ExistenceChecker.isSet((DerivInstrmAttrbts) o.getDerivInstrmAttrbts()))
				.put("id", ExistenceChecker.isSet((String) o.getId()))
				.put("schmeNm", ExistenceChecker.isSet((SchmeNm) o.getSchmeNm()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Othr", ValidationType.ONLY_EXISTS, "Othr", path, "");
		}
		return failure("Othr", ValidationType.ONLY_EXISTS, "Othr", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
