package cdm.event.common.functions;

import cdm.event.common.ContractFormationInstruction;
import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import cdm.event.position.PositionStatusEnum;
import cdm.legaldocumentation.common.LegalAgreement;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_ContractFormation.Create_ContractFormationDefault.class)
public abstract class Create_ContractFormation implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param instruction Instructions to be used as an input to the function
	* @param execution 
	* @return contractFormation Primitive event containing the execution as its before state and the contract as the after state.
	*/
	public TradeState evaluate(ContractFormationInstruction instruction, TradeState execution) {
		TradeState.TradeStateBuilder contractFormationBuilder = doEvaluate(instruction, execution);
		
		final TradeState contractFormation;
		if (contractFormationBuilder == null) {
			contractFormation = null;
		} else {
			contractFormation = contractFormationBuilder.build();
			objectValidator.validate(TradeState.class, contractFormation);
		}
		
		return contractFormation;
	}

	protected abstract TradeState.TradeStateBuilder doEvaluate(ContractFormationInstruction instruction, TradeState execution);

	public static class Create_ContractFormationDefault extends Create_ContractFormation {
		@Override
		protected TradeState.TradeStateBuilder doEvaluate(ContractFormationInstruction instruction, TradeState execution) {
			TradeState.TradeStateBuilder contractFormation = TradeState.builder();
			return assignOutput(contractFormation, instruction, execution);
		}
		
		protected TradeState.TradeStateBuilder assignOutput(TradeState.TradeStateBuilder contractFormation, ContractFormationInstruction instruction, TradeState execution) {
			contractFormation = toBuilder(execution);
			
			contractFormation
				.getOrCreateTrade()
				.getOrCreateContractDetails()
				.addDocumentation(MapperS.of(instruction).<LegalAgreement>mapC("getLegalAgreement", contractFormationInstruction -> contractFormationInstruction.getLegalAgreement()).getMulti());
			
			contractFormation
				.getOrCreateState()
				.setPositionState(PositionStatusEnum.FORMED);
			
			return Optional.ofNullable(contractFormation)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
