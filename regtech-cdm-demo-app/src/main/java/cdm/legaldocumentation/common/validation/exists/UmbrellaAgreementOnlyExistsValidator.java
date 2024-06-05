package cdm.legaldocumentation.common.validation.exists;

import cdm.legaldocumentation.common.UmbrellaAgreement;
import cdm.legaldocumentation.common.UmbrellaAgreementEntity;
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

public class UmbrellaAgreementOnlyExistsValidator implements ValidatorWithArg<UmbrellaAgreement, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends UmbrellaAgreement> ValidationResult<UmbrellaAgreement> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("isApplicable", ExistenceChecker.isSet((Boolean) o.getIsApplicable()))
				.put("language", ExistenceChecker.isSet((String) o.getLanguage()))
				.put("parties", ExistenceChecker.isSet((List<? extends UmbrellaAgreementEntity>) o.getParties()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("UmbrellaAgreement", ValidationType.ONLY_EXISTS, "UmbrellaAgreement", path, "");
		}
		return failure("UmbrellaAgreement", ValidationType.ONLY_EXISTS, "UmbrellaAgreement", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
