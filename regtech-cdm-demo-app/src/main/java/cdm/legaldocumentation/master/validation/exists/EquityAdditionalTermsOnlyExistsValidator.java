package cdm.legaldocumentation.master.validation.exists;

import cdm.legaldocumentation.master.DeterminationRolesAndTerms;
import cdm.legaldocumentation.master.EquityAdditionalTerms;
import cdm.legaldocumentation.master.ExtraordinaryEvents;
import cdm.legaldocumentation.master.UnderlierSubstitutionProvision;
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

public class EquityAdditionalTermsOnlyExistsValidator implements ValidatorWithArg<EquityAdditionalTerms, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends EquityAdditionalTerms> ValidationResult<EquityAdditionalTerms> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("extraordinaryEvents", ExistenceChecker.isSet((ExtraordinaryEvents) o.getExtraordinaryEvents()))
				.put("determinationTerms", ExistenceChecker.isSet((List<? extends DeterminationRolesAndTerms>) o.getDeterminationTerms()))
				.put("substitutionProvision", ExistenceChecker.isSet((UnderlierSubstitutionProvision) o.getSubstitutionProvision()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("EquityAdditionalTerms", ValidationType.ONLY_EXISTS, "EquityAdditionalTerms", path, "");
		}
		return failure("EquityAdditionalTerms", ValidationType.ONLY_EXISTS, "EquityAdditionalTerms", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
