package cdm.event.common.functions;

import cdm.event.common.ContractFormationInstruction;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.PrimitiveInstruction;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(NewTradeInstructionOnlyExists.NewTradeInstructionOnlyExistsDefault.class)
public abstract class NewTradeInstructionOnlyExists implements RosettaFunction {

	/**
	* @param primitiveInstruction 
	* @return result 
	*/
	public Boolean evaluate(PrimitiveInstruction primitiveInstruction) {
		Boolean result = doEvaluate(primitiveInstruction);
		
		return result;
	}

	protected abstract Boolean doEvaluate(PrimitiveInstruction primitiveInstruction);

	public static class NewTradeInstructionOnlyExistsDefault extends NewTradeInstructionOnlyExists {
		@Override
		protected Boolean doEvaluate(PrimitiveInstruction primitiveInstruction) {
			Boolean result = null;
			return assignOutput(result, primitiveInstruction);
		}
		
		protected Boolean assignOutput(Boolean result, PrimitiveInstruction primitiveInstruction) {
			result = onlyExists(Arrays.asList(MapperS.of(primitiveInstruction).<ExecutionInstruction>map("getExecution", _primitiveInstruction -> _primitiveInstruction.getExecution()))).or(onlyExists(Arrays.asList(MapperS.of(primitiveInstruction).<ExecutionInstruction>map("getExecution", _primitiveInstruction -> _primitiveInstruction.getExecution()), MapperS.of(primitiveInstruction).<ContractFormationInstruction>map("getContractFormation", _primitiveInstruction -> _primitiveInstruction.getContractFormation())))).get();
			
			return result;
		}
	}
}
