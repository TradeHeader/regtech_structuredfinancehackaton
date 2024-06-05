package cdm.event.common.functions;

import cdm.base.staticdata.identifier.IdentifiedList;
import cdm.event.common.BusinessEvent;
import cdm.event.common.ExecutionDetails;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
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
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_PairOff.Qualify_PairOffDefault.class)
public abstract class Qualify_PairOff implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
	@Inject protected FilterOpenTradeStates filterOpenTradeStates;
	@Inject protected NewTradeInstructionOnlyExists newTradeInstructionOnlyExists;

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

	protected abstract MapperC<? extends TradeState> openTradeState(BusinessEvent businessEvent);

	protected abstract MapperC<? extends Instruction> newTradeInstruction(BusinessEvent businessEvent);

	protected abstract MapperC<? extends IdentifiedList> packageRef(BusinessEvent businessEvent);

	public static class Qualify_PairOffDefault extends Qualify_PairOff {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = areEqual(MapperS.of(newTradeInstruction(businessEvent).resultCount()), MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).resultCount()), CardinalityOperator.All).and(areEqual(openTradeState(businessEvent).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()), newTradeInstruction(businessEvent).<ReferenceWithMetaTradeState>map("getBefore", instruction -> instruction.getBefore()).<TradeState>map("getValue", _f->_f.getValue()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()), CardinalityOperator.All)).and(areEqual(MapperS.of(packageRef(businessEvent).resultCount()), MapperS.of(openTradeState(businessEvent).resultCount()), CardinalityOperator.All)).and(areEqual(MapperS.of(distinct(packageRef(businessEvent)).resultCount()), MapperS.of(1), CardinalityOperator.All)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperC<? extends TradeState> openTradeState(BusinessEvent businessEvent) {
			return MapperC.<TradeState>of(filterOpenTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti()));
		}
		
		@Override
		protected MapperC<? extends Instruction> newTradeInstruction(BusinessEvent businessEvent) {
			return MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction())
				.filterItemNullSafe(item -> newTradeInstructionOnlyExists.evaluate(item.<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).get()));
		}
		
		@Override
		protected MapperC<? extends IdentifiedList> packageRef(BusinessEvent businessEvent) {
			return openTradeState(businessEvent).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<ExecutionDetails>map("getExecutionDetails", trade -> trade.getExecutionDetails()).<IdentifiedList>map("getPackageReference", executionDetails -> executionDetails.getPackageReference());
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
