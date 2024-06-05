package cdm.legaldocumentation.common.validation.exists;

import cdm.base.staticdata.party.PartyContactInformation;
import cdm.legaldocumentation.common.AddressForNotices;
import cdm.product.collateral.ContactElection;
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

public class AddressForNoticesOnlyExistsValidator implements ValidatorWithArg<AddressForNotices, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AddressForNotices> ValidationResult<AddressForNotices> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("primaryNotices", ExistenceChecker.isSet((ContactElection) o.getPrimaryNotices()))
				.put("additionalNotices", ExistenceChecker.isSet((List<? extends PartyContactInformation>) o.getAdditionalNotices()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AddressForNotices", ValidationType.ONLY_EXISTS, "AddressForNotices", path, "");
		}
		return failure("AddressForNotices", ValidationType.ONLY_EXISTS, "AddressForNotices", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
