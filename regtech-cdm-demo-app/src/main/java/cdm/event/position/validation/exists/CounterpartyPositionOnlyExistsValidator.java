package cdm.event.position.validation.exists;

import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.PositionIdentifier;
import cdm.event.common.metafields.ReferenceWithMetaContractDetails;
import cdm.event.common.metafields.ReferenceWithMetaExecutionDetails;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.event.position.CounterpartyPosition;
import cdm.product.collateral.metafields.ReferenceWithMetaCollateral;
import cdm.product.template.TradableProduct;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class CounterpartyPositionOnlyExistsValidator implements ValidatorWithArg<CounterpartyPosition, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends CounterpartyPosition> ValidationResult<CounterpartyPosition> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("contractDetails", ExistenceChecker.isSet((ReferenceWithMetaContractDetails) o.getContractDetails()))
				.put("executionDetails", ExistenceChecker.isSet((ReferenceWithMetaExecutionDetails) o.getExecutionDetails()))
				.put("collateral", ExistenceChecker.isSet((ReferenceWithMetaCollateral) o.getCollateral()))
				.put("positionIdentifier", ExistenceChecker.isSet((List<? extends PositionIdentifier>) o.getPositionIdentifier()))
				.put("openDateTime", ExistenceChecker.isSet((LocalDateTime) o.getOpenDateTime()))
				.put("tradeReference", ExistenceChecker.isSet((List<? extends ReferenceWithMetaTradeState>) o.getTradeReference()))
				.put("party", ExistenceChecker.isSet((List<? extends Party>) o.getParty()))
				.put("partyRole", ExistenceChecker.isSet((List<? extends PartyRole>) o.getPartyRole()))
				.put("positionBase", ExistenceChecker.isSet((TradableProduct) o.getPositionBase()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("CounterpartyPosition", ValidationType.ONLY_EXISTS, "CounterpartyPosition", path, "");
		}
		return failure("CounterpartyPosition", ValidationType.ONLY_EXISTS, "CounterpartyPosition", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
