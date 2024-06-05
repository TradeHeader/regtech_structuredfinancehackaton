package cdm.legaldocumentation.master.validation.exists;

import cdm.legaldocumentation.master.MasterAgreementVariableSet;
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

public class MasterAgreementVariableSetOnlyExistsValidator implements ValidatorWithArg<MasterAgreementVariableSet, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends MasterAgreementVariableSet> ValidationResult<MasterAgreementVariableSet> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("variableSet", ExistenceChecker.isSet((List<? extends MasterAgreementVariableSet>) o.getVariableSet()))
				.put("name", ExistenceChecker.isSet((String) o.getName()))
				.put("value", ExistenceChecker.isSet((String) o.getValue()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("MasterAgreementVariableSet", ValidationType.ONLY_EXISTS, "MasterAgreementVariableSet", path, "");
		}
		return failure("MasterAgreementVariableSet", ValidationType.ONLY_EXISTS, "MasterAgreementVariableSet", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
