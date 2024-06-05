package cdm.event.common.validation.exists;

import cdm.event.common.ContractDetails;
import cdm.legaldocumentation.common.LegalAgreement;
import cdm.legaldocumentation.common.metafields.FieldWithMetaGoverningLawEnum;
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

public class ContractDetailsOnlyExistsValidator implements ValidatorWithArg<ContractDetails, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ContractDetails> ValidationResult<ContractDetails> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("documentation", ExistenceChecker.isSet((List<? extends LegalAgreement>) o.getDocumentation()))
				.put("governingLaw", ExistenceChecker.isSet((FieldWithMetaGoverningLawEnum) o.getGoverningLaw()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ContractDetails", ValidationType.ONLY_EXISTS, "ContractDetails", path, "");
		}
		return failure("ContractDetails", ValidationType.ONLY_EXISTS, "ContractDetails", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
