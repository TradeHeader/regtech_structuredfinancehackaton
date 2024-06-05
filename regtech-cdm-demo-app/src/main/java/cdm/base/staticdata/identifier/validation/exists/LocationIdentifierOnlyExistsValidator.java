package cdm.base.staticdata.identifier.validation.exists;

import cdm.base.staticdata.identifier.AssignedIdentifier;
import cdm.base.staticdata.identifier.CommodityLocationIdentifierTypeEnum;
import cdm.base.staticdata.identifier.LocationIdentifier;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class LocationIdentifierOnlyExistsValidator implements ValidatorWithArg<LocationIdentifier, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends LocationIdentifier> ValidationResult<LocationIdentifier> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("issuerReference", ExistenceChecker.isSet((ReferenceWithMetaParty) o.getIssuerReference()))
				.put("issuer", ExistenceChecker.isSet((FieldWithMetaString) o.getIssuer()))
				.put("assignedIdentifier", ExistenceChecker.isSet((List<? extends AssignedIdentifier>) o.getAssignedIdentifier()))
				.put("locationIdentifierType", ExistenceChecker.isSet((CommodityLocationIdentifierTypeEnum) o.getLocationIdentifierType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("LocationIdentifier", ValidationType.ONLY_EXISTS, "LocationIdentifier", path, "");
		}
		return failure("LocationIdentifier", ValidationType.ONLY_EXISTS, "LocationIdentifier", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
