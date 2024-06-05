package cdm.event.qualification.functions;

import cdm.event.common.BusinessEvent;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.QuantityChangeInstruction;
import cdm.event.common.State;
import cdm.event.common.TradeState;
import cdm.event.common.Transfer;
import cdm.event.common.TransferInstruction;
import cdm.event.common.TransferState;
import cdm.event.common.functions.QuantityDecreasedToZero;
import cdm.event.common.functions.TransfersForDate;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.legaldocumentation.common.ClosedState;
import cdm.legaldocumentation.common.ClosedStateEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Arrays;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_Termination.Qualify_TerminationDefault.class)
public abstract class Qualify_Termination implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
	@Inject protected QuantityDecreasedToZero quantityDecreasedToZero;
	@Inject protected TransfersForDate transfersForDate;

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

	protected abstract MapperS<? extends PrimitiveInstruction> primitiveInstruction(BusinessEvent businessEvent);

	protected abstract MapperS<? extends Transfer> transfer(BusinessEvent businessEvent);

	public static class Qualify_TerminationDefault extends Qualify_Termination {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = notExists(MapperS.of(businessEvent).<EventIntentEnum>map("getIntent", eventInstruction -> eventInstruction.getIntent())).and(onlyExists(Arrays.asList(primitiveInstruction(businessEvent).<QuantityChangeInstruction>map("getQuantityChange", _primitiveInstruction -> _primitiveInstruction.getQuantityChange()))).or(onlyExists(Arrays.asList(primitiveInstruction(businessEvent).<QuantityChangeInstruction>map("getQuantityChange", _primitiveInstruction -> _primitiveInstruction.getQuantityChange()), primitiveInstruction(businessEvent).<TransferInstruction>map("getTransfer", _primitiveInstruction -> _primitiveInstruction.getTransfer()))))).and(areEqual(MapperS.of(quantityDecreasedToZero.evaluate(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<ReferenceWithMetaTradeState>map("getBefore", instruction -> instruction.getBefore()).<TradeState>map("getValue", _f->_f.getValue()).getMulti(), MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti())), MapperS.of(true), CardinalityOperator.All)).and(areEqual(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).<State>map("getState", tradeState -> tradeState.getState()).<ClosedState>map("getClosedState", state -> state.getClosedState()).<ClosedStateEnum>map("getState", closedState -> closedState.getState()), MapperS.of(ClosedStateEnum.TERMINATED), CardinalityOperator.All)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperS<? extends PrimitiveInstruction> primitiveInstruction(BusinessEvent businessEvent) {
			return MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).get());
		}
		
		@Override
		protected MapperS<? extends Transfer> transfer(BusinessEvent businessEvent) {
			return MapperS.of(MapperC.of(transfersForDate.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).<TransferState>mapC("getTransferHistory", tradeState -> tradeState.getTransferHistory()).<Transfer>map("getTransfer", transferState -> transferState.getTransfer()).getMulti(), MapperS.of(businessEvent).<Date>map("getEventDate", eventInstruction -> eventInstruction.getEventDate()).get())).get());
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
