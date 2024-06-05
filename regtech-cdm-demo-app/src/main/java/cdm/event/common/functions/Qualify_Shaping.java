package cdm.event.common.functions;

import cdm.base.staticdata.identifier.IdentifiedList;
import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.common.BusinessEvent;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.SplitInstruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.product.template.TradableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Arrays;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_Shaping.Qualify_ShapingDefault.class)
public abstract class Qualify_Shaping implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
	@Inject protected FilterClosedTradeStates filterClosedTradeStates;
	@Inject protected FilterOpenTradeStates filterOpenTradeStates;
	@Inject protected TradeNoExecutionDetails tradeNoExecutionDetails;

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

	protected abstract MapperS<? extends TradeState> closedTradeState(BusinessEvent businessEvent);

	protected abstract MapperC<? extends TradeState> openTradeStates(BusinessEvent businessEvent);

	protected abstract MapperC<? extends IdentifiedList> packageRef(BusinessEvent businessEvent);

	protected abstract MapperC<? extends Trade> openTradeNoExecutionDetails(BusinessEvent businessEvent);

	public static class Qualify_ShapingDefault extends Qualify_Shaping {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = exists(beforeTradeState(businessEvent)).and(exists(closedTradeState(businessEvent))).and(greaterThan(MapperS.of(openTradeStates(businessEvent).resultCount()), MapperS.of(1), CardinalityOperator.All)).and(onlyExists(Arrays.asList(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).<SplitInstruction>map("getSplit", primitiveInstruction -> primitiveInstruction.getSplit())))).and(areEqual(openTradeStates(businessEvent)
				.mapItem(item -> areEqual(item.<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()), beforeTradeState(businessEvent).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Counterparty>mapC("getCounterparty", tradableProduct -> tradableProduct.getCounterparty()).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("getValue", _f->_f.getValue()), CardinalityOperator.All).asMapper()), MapperS.of(true), CardinalityOperator.All)).and(areEqual(MapperS.of(packageRef(businessEvent).resultCount()), MapperS.of(openTradeNoExecutionDetails(businessEvent).resultCount()), CardinalityOperator.All)).and(areEqual(MapperS.of(distinct(packageRef(businessEvent)).resultCount()), MapperS.of(1), CardinalityOperator.All)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperS<? extends TradeState> beforeTradeState(BusinessEvent businessEvent) {
			return MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<ReferenceWithMetaTradeState>map("getBefore", instruction -> instruction.getBefore()).<TradeState>map("getValue", _f->_f.getValue()).get());
		}
		
		@Override
		protected MapperS<? extends TradeState> closedTradeState(BusinessEvent businessEvent) {
			return MapperS.of(MapperC.of(filterClosedTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti())).get());
		}
		
		@Override
		protected MapperC<? extends TradeState> openTradeStates(BusinessEvent businessEvent) {
			return MapperC.<TradeState>of(filterOpenTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti()));
		}
		
		@Override
		protected MapperC<? extends IdentifiedList> packageRef(BusinessEvent businessEvent) {
			return openTradeStates(businessEvent).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<ExecutionDetails>map("getExecutionDetails", trade -> trade.getExecutionDetails()).<IdentifiedList>map("getPackageReference", executionDetails -> executionDetails.getPackageReference());
		}
		
		@Override
		protected MapperC<? extends Trade> openTradeNoExecutionDetails(BusinessEvent businessEvent) {
			return openTradeStates(businessEvent)
				.mapItem(item -> MapperS.of(tradeNoExecutionDetails.evaluate(item.<Trade>map("getTrade", tradeState -> tradeState.getTrade()).get())));
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
