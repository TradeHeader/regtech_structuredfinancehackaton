package cdm.base.staticdata.party.validation.exists;

import cdm.base.staticdata.party.NaturalPersonRole;
import cdm.base.staticdata.party.metafields.FieldWithMetaNaturalPersonRoleEnum;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaNaturalPerson;
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

public class NaturalPersonRoleOnlyExistsValidator implements ValidatorWithArg<NaturalPersonRole, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends NaturalPersonRole> ValidationResult<NaturalPersonRole> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("personReference", ExistenceChecker.isSet((ReferenceWithMetaNaturalPerson) o.getPersonReference()))
				.put("role", ExistenceChecker.isSet((List<? extends FieldWithMetaNaturalPersonRoleEnum>) o.getRole()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("NaturalPersonRole", ValidationType.ONLY_EXISTS, "NaturalPersonRole", path, "");
		}
		return failure("NaturalPersonRole", ValidationType.ONLY_EXISTS, "NaturalPersonRole", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
