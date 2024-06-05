package cdm.event.common.functions;

import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import cdm.event.common.TransferInstruction;
import cdm.event.common.TransferState;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_Transfer.Create_TransferDefault.class)
public abstract class Create_Transfer implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param instruction 
	* @param tradeState Represents the trade and associated state on which to construct the Transfer data type.
	* @return transfer 
	*/
	public TradeState evaluate(TransferInstruction instruction, TradeState tradeState) {
		TradeState.TradeStateBuilder transferBuilder = doEvaluate(instruction, tradeState);
		
		final TradeState transfer;
		if (transferBuilder == null) {
			transfer = null;
		} else {
			transfer = transferBuilder.build();
			objectValidator.validate(TradeState.class, transfer);
		}
		
		return transfer;
	}

	protected abstract TradeState.TradeStateBuilder doEvaluate(TransferInstruction instruction, TradeState tradeState);

	public static class Create_TransferDefault extends Create_Transfer {
		@Override
		protected TradeState.TradeStateBuilder doEvaluate(TransferInstruction instruction, TradeState tradeState) {
			TradeState.TradeStateBuilder transfer = TradeState.builder();
			return assignOutput(transfer, instruction, tradeState);
		}
		
		protected TradeState.TradeStateBuilder assignOutput(TradeState.TradeStateBuilder transfer, TransferInstruction instruction, TradeState tradeState) {
			transfer = toBuilder(tradeState);
			
			transfer
				.addTransferHistory(MapperS.of(instruction).<TransferState>mapC("getTransferState", transferInstruction -> transferInstruction.getTransferState()).getMulti());
			
			return Optional.ofNullable(transfer)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
