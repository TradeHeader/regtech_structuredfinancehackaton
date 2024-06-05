package cdm.product.asset.validation.exists;

import cdm.base.staticdata.party.LegalEntity;
import cdm.observable.asset.Price;
import cdm.product.asset.ReferenceInformation;
import cdm.product.asset.ReferenceObligation;
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

public class ReferenceInformationOnlyExistsValidator implements ValidatorWithArg<ReferenceInformation, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ReferenceInformation> ValidationResult<ReferenceInformation> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("referenceEntity", ExistenceChecker.isSet((LegalEntity) o.getReferenceEntity()))
				.put("referenceObligation", ExistenceChecker.isSet((List<? extends ReferenceObligation>) o.getReferenceObligation()))
				.put("noReferenceObligation", ExistenceChecker.isSet((Boolean) o.getNoReferenceObligation()))
				.put("unknownReferenceObligation", ExistenceChecker.isSet((Boolean) o.getUnknownReferenceObligation()))
				.put("allGuarantees", ExistenceChecker.isSet((Boolean) o.getAllGuarantees()))
				.put("referencePrice", ExistenceChecker.isSet((Price) o.getReferencePrice()))
				.put("referencePolicy", ExistenceChecker.isSet((Boolean) o.getReferencePolicy()))
				.put("securedList", ExistenceChecker.isSet((Boolean) o.getSecuredList()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ReferenceInformation", ValidationType.ONLY_EXISTS, "ReferenceInformation", path, "");
		}
		return failure("ReferenceInformation", ValidationType.ONLY_EXISTS, "ReferenceInformation", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
