package cdm.event.qualification.functions;

import cdm.event.common.BusinessEvent;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.ExerciseInstruction;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.QuantityChangeInstruction;
import cdm.event.common.TransferInstruction;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_Exercise.Qualify_ExerciseDefault.class)
public abstract class Qualify_Exercise implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {

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

	public static class Qualify_ExerciseDefault extends Qualify_Exercise {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = areEqual(MapperS.of(businessEvent).<EventIntentEnum>map("getIntent", eventInstruction -> eventInstruction.getIntent()), MapperS.of(EventIntentEnum.OPTION_EXERCISE), CardinalityOperator.All).and(areEqual(MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).resultCount()), MapperS.of(1), CardinalityOperator.All).and(exists(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).<QuantityChangeInstruction>map("getQuantityChange", _primitiveInstruction -> _primitiveInstruction.getQuantityChange())).and(exists(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).<TransferInstruction>map("getTransfer", _primitiveInstruction -> _primitiveInstruction.getTransfer())))).or(areEqual(MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).resultCount()), MapperS.of(2), CardinalityOperator.All).and(exists(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).<QuantityChangeInstruction>map("getQuantityChange", _primitiveInstruction -> _primitiveInstruction.getQuantityChange())).and(exists(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).<ExecutionInstruction>map("getExecution", _primitiveInstruction -> _primitiveInstruction.getExecution()))))).or(areEqual(MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).resultCount()), MapperS.of(1), CardinalityOperator.All).and(exists(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).<ExerciseInstruction>map("getExercise", _primitiveInstruction -> _primitiveInstruction.getExercise()))))).get();
			
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
