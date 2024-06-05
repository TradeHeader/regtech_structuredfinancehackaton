package cdm.event.common.functions;

import cdm.event.common.CalculateTransferInstruction;
import cdm.event.common.Transfer;
import cdm.event.common.Transfer.TransferBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_CashTransfer.Create_CashTransferDefault.class)
public abstract class Create_CashTransfer implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected ResolveTransfer resolveTransfer;

	/**
	* @param instruction 
	* @return transfer 
	*/
	public Transfer evaluate(CalculateTransferInstruction instruction) {
		Transfer.TransferBuilder transferBuilder = doEvaluate(instruction);
		
		final Transfer transfer;
		if (transferBuilder == null) {
			transfer = null;
		} else {
			transfer = transferBuilder.build();
			objectValidator.validate(Transfer.class, transfer);
		}
		
		return transfer;
	}

	protected abstract Transfer.TransferBuilder doEvaluate(CalculateTransferInstruction instruction);

	public static class Create_CashTransferDefault extends Create_CashTransfer {
		@Override
		protected Transfer.TransferBuilder doEvaluate(CalculateTransferInstruction instruction) {
			Transfer.TransferBuilder transfer = Transfer.builder();
			return assignOutput(transfer, instruction);
		}
		
		protected Transfer.TransferBuilder assignOutput(Transfer.TransferBuilder transfer, CalculateTransferInstruction instruction) {
			transfer = toBuilder(resolveTransfer.evaluate(instruction));
			
			return Optional.ofNullable(transfer)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
