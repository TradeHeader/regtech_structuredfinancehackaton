package cdm.event.qualification.functions;

import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.PartyRoleEnum;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.common.BusinessEvent;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.SplitInstruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeIdentifier;
import cdm.event.common.TradeState;
import cdm.event.common.functions.FilterClosedTradeStates;
import cdm.event.common.functions.FilterOpenTradeStates;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.product.template.TradableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_ClearedTrade.Qualify_ClearedTradeDefault.class)
public abstract class Qualify_ClearedTrade implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
	@Inject protected FilterClosedTradeStates filterClosedTradeStates;
	@Inject protected FilterOpenTradeStates filterOpenTradeStates;

	/**
	* @param businessEvent 
	* @return is_event 
	*/
	@Override
	public Boolean evaluate(BusinessEvent businessEvent) {
		Boolean is_event = doEvaluate(businessEvent);
		
		return is_event;
	}

	protected abstract Boolean doEvaluate(BusinessEvent businessEvent);

	protected abstract MapperS<? extends TradeState> beforeTradeState(BusinessEvent businessEvent);

	protected abstract MapperC<? extends TradeState> closedTradeStates(BusinessEvent businessEvent);

	protected abstract MapperC<? extends TradeState> openTradeStates(BusinessEvent businessEvent);

	public static class Qualify_ClearedTradeDefault extends Qualify_ClearedTrade {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = areEqual(MapperS.of(businessEvent).<EventIntentEnum>map("getIntent", eventInstruction -> eventInstruction.getIntent()), MapperS.of(EventIntentEnum.CLEARING), CardinalityOperator.All).and(areEqual(MapperS.of(closedTradeStates(businessEvent).resultCount()), MapperS.of(1), CardinalityOperator.All)).and(areEqual(MapperS.of(openTradeStates(businessEvent).resultCount()), MapperS.of(2), CardinalityOperator.All)).and(exists(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).<SplitInstruction>map("getSplit", primitiveInstruction -> primitiveInstruction.getSplit()))).and(areEqual(beforeTradeState(businessEvent).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()), MapperS.of(closedTradeStates(businessEvent).get()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()), CardinalityOperator.All)).and(areEqual(openTradeStates(businessEvent)
				.mapItem(item -> notEqual(item.<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()), beforeTradeState(businessEvent).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()), CardinalityOperator.Any).and(notEqual(item.<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradeIdentifier>mapC("getTradeIdentifier", trade -> trade.getTradeIdentifier()), beforeTradeState(businessEvent).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradeIdentifier>mapC("getTradeIdentifier", trade -> trade.getTradeIdentifier()), CardinalityOperator.Any)).and(contains(item.<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<PartyRole>mapC("getPartyRole", trade -> trade.getPartyRole()).<PartyRoleEnum>map("getRole", partyRole -> partyRole.getRole()), MapperS.of(PartyRoleEnum.CLEARING_ORGANIZATION))).asMapper()), MapperS.of(true), CardinalityOperator.All)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperS<? extends TradeState> beforeTradeState(BusinessEvent businessEvent) {
			return MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<ReferenceWithMetaTradeState>map("getBefore", instruction -> instruction.getBefore()).<TradeState>map("getValue", _f->_f.getValue()).get());
		}
		
		@Override
		protected MapperC<? extends TradeState> closedTradeStates(BusinessEvent businessEvent) {
			return MapperC.<TradeState>of(filterClosedTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti()));
		}
		
		@Override
		protected MapperC<? extends TradeState> openTradeStates(BusinessEvent businessEvent) {
			return MapperC.<TradeState>of(filterOpenTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti()));
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
