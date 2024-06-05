package cdm.event.common.validation.exists;

import cdm.base.datetime.metafields.FieldWithMetaTimeZone;
import cdm.base.staticdata.party.Account;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.ContractDetails;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.Trade;
import cdm.event.common.TradeIdentifier;
import cdm.product.collateral.Collateral;
import cdm.product.template.TradableProduct;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class TradeOnlyExistsValidator implements ValidatorWithArg<Trade, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Trade> ValidationResult<Trade> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("tradeIdentifier", ExistenceChecker.isSet((List<? extends TradeIdentifier>) o.getTradeIdentifier()))
				.put("tradeDate", ExistenceChecker.isSet((FieldWithMetaDate) o.getTradeDate()))
				.put("tradeTime", ExistenceChecker.isSet((FieldWithMetaTimeZone) o.getTradeTime()))
				.put("tradableProduct", ExistenceChecker.isSet((TradableProduct) o.getTradableProduct()))
				.put("party", ExistenceChecker.isSet((List<? extends Party>) o.getParty()))
				.put("partyRole", ExistenceChecker.isSet((List<? extends PartyRole>) o.getPartyRole()))
				.put("executionDetails", ExistenceChecker.isSet((ExecutionDetails) o.getExecutionDetails()))
				.put("contractDetails", ExistenceChecker.isSet((ContractDetails) o.getContractDetails()))
				.put("clearedDate", ExistenceChecker.isSet((Date) o.getClearedDate()))
				.put("collateral", ExistenceChecker.isSet((Collateral) o.getCollateral()))
				.put("account", ExistenceChecker.isSet((List<? extends Account>) o.getAccount()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Trade", ValidationType.ONLY_EXISTS, "Trade", path, "");
		}
		return failure("Trade", ValidationType.ONLY_EXISTS, "Trade", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
