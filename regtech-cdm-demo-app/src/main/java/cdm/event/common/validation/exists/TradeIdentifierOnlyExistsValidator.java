package cdm.event.common.validation.exists;

import cdm.base.staticdata.identifier.AssignedIdentifier;
import cdm.base.staticdata.identifier.TradeIdentifierTypeEnum;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.common.TradeIdentifier;
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

public class TradeIdentifierOnlyExistsValidator implements ValidatorWithArg<TradeIdentifier, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends TradeIdentifier> ValidationResult<TradeIdentifier> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("issuerReference", ExistenceChecker.isSet((ReferenceWithMetaParty) o.getIssuerReference()))
				.put("issuer", ExistenceChecker.isSet((FieldWithMetaString) o.getIssuer()))
				.put("assignedIdentifier", ExistenceChecker.isSet((List<? extends AssignedIdentifier>) o.getAssignedIdentifier()))
				.put("identifierType", ExistenceChecker.isSet((TradeIdentifierTypeEnum) o.getIdentifierType()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("TradeIdentifier", ValidationType.ONLY_EXISTS, "TradeIdentifier", path, "");
		}
		return failure("TradeIdentifier", ValidationType.ONLY_EXISTS, "TradeIdentifier", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
