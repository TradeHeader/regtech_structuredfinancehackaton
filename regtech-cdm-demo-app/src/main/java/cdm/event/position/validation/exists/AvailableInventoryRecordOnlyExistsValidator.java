package cdm.event.position.validation.exists;

import cdm.base.math.Quantity;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.identifier.AssignedIdentifier;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.position.AvailableInventoryRecord;
import cdm.observable.asset.Price;
import cdm.product.collateral.CollateralProvisions;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class AvailableInventoryRecordOnlyExistsValidator implements ValidatorWithArg<AvailableInventoryRecord, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends AvailableInventoryRecord> ValidationResult<AvailableInventoryRecord> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("identifer", ExistenceChecker.isSet((AssignedIdentifier) o.getIdentifer()))
				.put("security", ExistenceChecker.isSet((Security) o.getSecurity()))
				.put("expirationDateTime", ExistenceChecker.isSet((ZonedDateTime) o.getExpirationDateTime()))
				.put("collateral", ExistenceChecker.isSet((List<? extends CollateralProvisions>) o.getCollateral()))
				.put("partyRole", ExistenceChecker.isSet((List<? extends PartyRole>) o.getPartyRole()))
				.put("availableQuantity", ExistenceChecker.isSet((Quantity) o.getAvailableQuantity()))
				.put("interestRate", ExistenceChecker.isSet((Price) o.getInterestRate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("AvailableInventoryRecord", ValidationType.ONLY_EXISTS, "AvailableInventoryRecord", path, "");
		}
		return failure("AvailableInventoryRecord", ValidationType.ONLY_EXISTS, "AvailableInventoryRecord", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
