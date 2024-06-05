package cdm.event.qualification.functions;

import cdm.event.common.BusinessEvent;
import cdm.event.common.ContractFormationInstruction;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.TransferInstruction;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_ContractFormation.Qualify_ContractFormationDefault.class)
public abstract class Qualify_ContractFormation implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {

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

	public static class Qualify_ContractFormationDefault extends Qualify_ContractFormation {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = onlyExists(Arrays.asList(primitiveInstruction(businessEvent).<ContractFormationInstruction>map("getContractFormation", _primitiveInstruction -> _primitiveInstruction.getContractFormation()))).or(onlyExists(Arrays.asList(primitiveInstruction(businessEvent).<ContractFormationInstruction>map("getContractFormation", _primitiveInstruction -> _primitiveInstruction.getContractFormation()), primitiveInstruction(businessEvent).<TransferInstruction>map("getTransfer", _primitiveInstruction -> _primitiveInstruction.getTransfer())))).or(onlyExists(Arrays.asList(primitiveInstruction(businessEvent).<ExecutionInstruction>map("getExecution", _primitiveInstruction -> _primitiveInstruction.getExecution()), primitiveInstruction(businessEvent).<ContractFormationInstruction>map("getContractFormation", _primitiveInstruction -> _primitiveInstruction.getContractFormation())))).or(onlyExists(Arrays.asList(primitiveInstruction(businessEvent).<ExecutionInstruction>map("getExecution", _primitiveInstruction -> _primitiveInstruction.getExecution()), primitiveInstruction(businessEvent).<ContractFormationInstruction>map("getContractFormation", _primitiveInstruction -> _primitiveInstruction.getContractFormation()), primitiveInstruction(businessEvent).<TransferInstruction>map("getTransfer", _primitiveInstruction -> _primitiveInstruction.getTransfer())))).or(notExists(primitiveInstruction(businessEvent)).and(areEqual(MapperS.of(businessEvent).<EventIntentEnum>map("getIntent", eventInstruction -> eventInstruction.getIntent()), MapperS.of(EventIntentEnum.CONTRACT_FORMATION), CardinalityOperator.All))).get();
			
			return is_event;
		}
		
		@Override
		protected MapperS<? extends PrimitiveInstruction> primitiveInstruction(BusinessEvent businessEvent) {
			return MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).get());
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
