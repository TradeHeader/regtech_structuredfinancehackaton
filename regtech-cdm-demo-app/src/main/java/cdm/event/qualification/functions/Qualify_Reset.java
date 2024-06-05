package cdm.event.qualification.functions;

import cdm.event.common.BusinessEvent;
import cdm.event.common.Instruction;
import cdm.event.common.Reset;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_Reset.Qualify_ResetDefault.class)
public abstract class Qualify_Reset implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {

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

	protected abstract MapperS<? extends TradeState> afterTradeState(BusinessEvent businessEvent);

	public static class Qualify_ResetDefault extends Qualify_Reset {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = areEqual(MapperS.of(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).resultCount()), MapperS.of(1), CardinalityOperator.All).and(areEqual(beforeTradeState(businessEvent).<Trade>map("getTrade", tradeState -> tradeState.getTrade()), afterTradeState(businessEvent).<Trade>map("getTrade", tradeState -> tradeState.getTrade()), CardinalityOperator.All)).and(areEqual(MapperMaths.<Integer, Integer, Integer>add(MapperS.of(beforeTradeState(businessEvent).<Reset>mapC("getResetHistory", tradeState -> tradeState.getResetHistory()).resultCount()), MapperS.of(1)), MapperS.of(afterTradeState(businessEvent).<Reset>mapC("getResetHistory", tradeState -> tradeState.getResetHistory()).resultCount()), CardinalityOperator.All)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperS<? extends TradeState> beforeTradeState(BusinessEvent businessEvent) {
			return MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<ReferenceWithMetaTradeState>map("getBefore", instruction -> instruction.getBefore()).<TradeState>map("getValue", _f->_f.getValue()).get());
		}
		
		@Override
		protected MapperS<? extends TradeState> afterTradeState(BusinessEvent businessEvent) {
			return MapperS.of(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).get());
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
