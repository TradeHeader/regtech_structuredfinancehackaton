package cdm.legaldocumentation.common.validation.exists;

import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.GoverningLawEnum;
import cdm.legaldocumentation.common.LegalAgreementIdentification;
import cdm.legaldocumentation.common.LegalAgreementPublisherEnum;
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

public class LegalAgreementIdentificationOnlyExistsValidator implements ValidatorWithArg<LegalAgreementIdentification, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends LegalAgreementIdentification> ValidationResult<LegalAgreementIdentification> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("governingLaw", ExistenceChecker.isSet((GoverningLawEnum) o.getGoverningLaw()))
				.put("agreementName", ExistenceChecker.isSet((AgreementName) o.getAgreementName()))
				.put("publisher", ExistenceChecker.isSet((LegalAgreementPublisherEnum) o.getPublisher()))
				.put("vintage", ExistenceChecker.isSet((Integer) o.getVintage()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("LegalAgreementIdentification", ValidationType.ONLY_EXISTS, "LegalAgreementIdentification", path, "");
		}
		return failure("LegalAgreementIdentification", ValidationType.ONLY_EXISTS, "LegalAgreementIdentification", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
