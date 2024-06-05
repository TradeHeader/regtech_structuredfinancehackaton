package cdm.event.common.validation.exists;

import cdm.base.datetime.metafields.FieldWithMetaTimeZone;
import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.TradeIdentifier;
import cdm.product.collateral.Collateral;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.Product;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ExecutionInstructionOnlyExistsValidator implements ValidatorWithArg<ExecutionInstruction, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ExecutionInstruction> ValidationResult<ExecutionInstruction> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("product", ExistenceChecker.isSet((Product) o.getProduct()))
				.put("priceQuantity", ExistenceChecker.isSet((List<? extends PriceQuantity>) o.getPriceQuantity()))
				.put("counterparty", ExistenceChecker.isSet((List<? extends Counterparty>) o.getCounterparty()))
				.put("ancillaryParty", ExistenceChecker.isSet((List<? extends AncillaryParty>) o.getAncillaryParty()))
				.put("parties", ExistenceChecker.isSet((List<? extends Party>) o.getParties()))
				.put("partyRoles", ExistenceChecker.isSet((List<? extends PartyRole>) o.getPartyRoles()))
				.put("executionDetails", ExistenceChecker.isSet((ExecutionDetails) o.getExecutionDetails()))
				.put("tradeDate", ExistenceChecker.isSet((FieldWithMetaDate) o.getTradeDate()))
				.put("tradeTime", ExistenceChecker.isSet((FieldWithMetaTimeZone) o.getTradeTime()))
				.put("tradeIdentifier", ExistenceChecker.isSet((List<? extends TradeIdentifier>) o.getTradeIdentifier()))
				.put("collateral", ExistenceChecker.isSet((Collateral) o.getCollateral()))
				.put("lotIdentifier", ExistenceChecker.isSet((Identifier) o.getLotIdentifier()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ExecutionInstruction", ValidationType.ONLY_EXISTS, "ExecutionInstruction", path, "");
		}
		return failure("ExecutionInstruction", ValidationType.ONLY_EXISTS, "ExecutionInstruction", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
