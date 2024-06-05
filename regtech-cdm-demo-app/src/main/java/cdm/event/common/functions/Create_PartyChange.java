package cdm.event.common.functions;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.functions.ExtractCounterpartyByRole;
import cdm.base.staticdata.party.functions.ReplaceParty;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.common.Trade;
import cdm.event.common.TradeIdentifier;
import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import cdm.product.template.TradableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_PartyChange.Create_PartyChangeDefault.class)
public abstract class Create_PartyChange implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected EmptyTransferHistory emptyTransferHistory;
	@Inject protected ExtractCounterpartyByRole extractCounterpartyByRole;
	@Inject protected ReplaceParty replaceParty;

	/**
	* @param counterparty The counterparty to change and the role it plays in the transaction.
	* @param ancillaryParty Optional ancillary party, which can be used to keep a reference to the original executing party, for instance.
	* @param partyRole 
	* @param tradeId A mandatory trade identifier must be specified, as the chnage of party results in a new trade.
	* @param originalTrade The original trade on which to update the counterparty. The original trade will be terminated.
	* @return newTrade 
	*/
	public TradeState evaluate(Counterparty counterparty, AncillaryParty ancillaryParty, PartyRole partyRole, List<? extends TradeIdentifier> tradeId, TradeState originalTrade) {
		TradeState.TradeStateBuilder newTradeBuilder = doEvaluate(counterparty, ancillaryParty, partyRole, tradeId, originalTrade);
		
		final TradeState newTrade;
		if (newTradeBuilder == null) {
			newTrade = null;
		} else {
			newTrade = newTradeBuilder.build();
			objectValidator.validate(TradeState.class, newTrade);
		}
		
		return newTrade;
	}

	protected abstract TradeState.TradeStateBuilder doEvaluate(Counterparty counterparty, AncillaryParty ancillaryParty, PartyRole partyRole, List<? extends TradeIdentifier> tradeId, TradeState originalTrade);

	protected abstract MapperS<? extends Counterparty> counterparty1(Counterparty counterparty, AncillaryParty ancillaryParty, PartyRole partyRole, List<? extends TradeIdentifier> tradeId, TradeState originalTrade);

	protected abstract MapperS<? extends Counterparty> counterparty2(Counterparty counterparty, AncillaryParty ancillaryParty, PartyRole partyRole, List<? extends TradeIdentifier> tradeId, TradeState originalTrade);

	protected abstract MapperS<? extends Party> partyToRemove(Counterparty counterparty, AncillaryParty ancillaryParty, PartyRole partyRole, List<? extends TradeIdentifier> tradeId, TradeState originalTrade);

	public static class Create_PartyChangeDefault extends Create_PartyChange {
		@Override
		protected TradeState.TradeStateBuilder doEvaluate(Counterparty counterparty, AncillaryParty ancillaryParty, PartyRole partyRole, List<? extends TradeIdentifier> tradeId, TradeState originalTrade) {
			if (tradeId == null) {
				tradeId = Collections.emptyList();
			}
			TradeState.TradeStateBuilder newTrade = TradeState.builder();
			return assignOutput(newTrade, counterparty, ancillaryParty, partyRole, tradeId, originalTrade);
		}
		
		protected TradeState.TradeStateBuilder assignOutput(TradeState.TradeStateBuilder newTrade, Counterparty counterparty, AncillaryParty ancillaryParty, PartyRole partyRole, List<? extends TradeIdentifier> tradeId, TradeState originalTrade) {
			newTrade = toBuilder(originalTrade);
			
			newTrade
				.getOrCreateTrade()
				.getOrCreateTradableProduct()
				.setCounterparty(MapperC.<Counterparty>of(counterparty1(counterparty, ancillaryParty, partyRole, tradeId, originalTrade), counterparty2(counterparty, ancillaryParty, partyRole, tradeId, originalTrade)).getMulti());
			
			newTrade
				.getOrCreateTrade()
				.setParty(replaceParty.evaluate(MapperS.of(originalTrade).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<Party>mapC("getParty", trade -> trade.getParty()).getMulti(), partyToRemove(counterparty, ancillaryParty, partyRole, tradeId, originalTrade).get(), MapperS.of(counterparty).<ReferenceWithMetaParty>map("getPartyReference", _counterparty -> _counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()).get()));
			
			newTrade
				.getOrCreateTrade()
				.setTradeIdentifier(tradeId);
			
			newTrade
				.getOrCreateTrade()
				.addParty(MapperS.of(ancillaryParty).<ReferenceWithMetaParty>mapC("getPartyReference", _ancillaryParty -> _ancillaryParty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()).getMulti());
			
			newTrade
				.getOrCreateTrade()
				.getOrCreateTradableProduct()
				.addAncillaryParty((ancillaryParty == null ? Collections.<AncillaryParty>emptyList() : Collections.singletonList(ancillaryParty)));
			
			newTrade
				.getOrCreateTrade()
				.addParty(MapperS.of(partyRole).<ReferenceWithMetaParty>map("getPartyReference", _partyRole -> _partyRole.getPartyReference()).<Party>map("getValue", _f->_f.getValue()).getMulti());
			
			newTrade
				.getOrCreateTrade()
				.addPartyRole((partyRole == null ? Collections.<PartyRole>emptyList() : Collections.singletonList(partyRole)));
			
			newTrade
				.setTransferHistory(emptyTransferHistory.evaluate());
			
			return Optional.ofNullable(newTrade)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends Counterparty> counterparty1(Counterparty counterparty, AncillaryParty ancillaryParty, PartyRole partyRole, List<? extends TradeIdentifier> tradeId, TradeState originalTrade) {
			if (areEqual(MapperS.of(counterparty).<CounterpartyRoleEnum>map("getRole", _counterparty -> _counterparty.getRole()), MapperS.of(CounterpartyRoleEnum.PARTY_1), CardinalityOperator.All).getOrDefault(false)) {
				final Party partyReference = MapperS.of(counterparty).<ReferenceWithMetaParty>map("getPartyReference", _counterparty -> _counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()).get();
				return MapperS.of(Counterparty.builder()
					.setPartyReference(ReferenceWithMetaParty.builder()
						.setGlobalReference(Optional.ofNullable(partyReference)
							.map(r -> r.getMeta())
							.map(m -> m.getGlobalKey())
							.orElse(null))
						.setExternalReference(Optional.ofNullable(partyReference)
							.map(r -> r.getMeta())
							.map(m -> m.getExternalKey())
							.orElse(null))
						.build())
					.setRole(MapperS.of(counterparty).<CounterpartyRoleEnum>map("getRole", _counterparty -> _counterparty.getRole()).get())
					.build()
				);
			}
			return MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(originalTrade).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), CounterpartyRoleEnum.PARTY_1));
		}
		
		@Override
		protected MapperS<? extends Counterparty> counterparty2(Counterparty counterparty, AncillaryParty ancillaryParty, PartyRole partyRole, List<? extends TradeIdentifier> tradeId, TradeState originalTrade) {
			if (areEqual(MapperS.of(counterparty).<CounterpartyRoleEnum>map("getRole", _counterparty -> _counterparty.getRole()), MapperS.of(CounterpartyRoleEnum.PARTY_2), CardinalityOperator.All).getOrDefault(false)) {
				final Party partyReference = MapperS.of(counterparty).<ReferenceWithMetaParty>map("getPartyReference", _counterparty -> _counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()).get();
				return MapperS.of(Counterparty.builder()
					.setPartyReference(ReferenceWithMetaParty.builder()
						.setGlobalReference(Optional.ofNullable(partyReference)
							.map(r -> r.getMeta())
							.map(m -> m.getGlobalKey())
							.orElse(null))
						.setExternalReference(Optional.ofNullable(partyReference)
							.map(r -> r.getMeta())
							.map(m -> m.getExternalKey())
							.orElse(null))
						.build())
					.setRole(MapperS.of(counterparty).<CounterpartyRoleEnum>map("getRole", _counterparty -> _counterparty.getRole()).get())
					.build()
				);
			}
			return MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(originalTrade).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), CounterpartyRoleEnum.PARTY_2));
		}
		
		@Override
		protected MapperS<? extends Party> partyToRemove(Counterparty counterparty, AncillaryParty ancillaryParty, PartyRole partyRole, List<? extends TradeIdentifier> tradeId, TradeState originalTrade) {
			return MapperS.of(extractCounterpartyByRole.evaluate(MapperS.of(originalTrade).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).getMulti(), MapperS.of(counterparty).<CounterpartyRoleEnum>map("getRole", _counterparty -> _counterparty.getRole()).get())).<ReferenceWithMetaParty>map("getPartyReference", _counterparty -> _counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue());
		}
	}
}
