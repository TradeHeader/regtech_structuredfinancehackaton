package cdm.event.common.functions;

import cdm.event.common.BusinessEvent;
import cdm.event.common.BusinessEvent.BusinessEventBuilder;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.ExerciseInstruction;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.SplitInstruction;
import cdm.event.common.TradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperListOfLists;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_BusinessEvent.Create_BusinessEventDefault.class)
public abstract class Create_BusinessEvent implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_Exercise create_Exercise;
	@Inject protected Create_Split create_Split;
	@Inject protected Create_TradeState create_TradeState;

	/**
	* @param instruction 
	* @param intent 
	* @param eventDate 
	* @param effectiveDate 
	* @return businessEvent 
	*/
	public BusinessEvent evaluate(List<? extends Instruction> instruction, EventIntentEnum intent, Date eventDate, Date effectiveDate) {
		BusinessEvent.BusinessEventBuilder businessEventBuilder = doEvaluate(instruction, intent, eventDate, effectiveDate);
		
		final BusinessEvent businessEvent;
		if (businessEventBuilder == null) {
			businessEvent = null;
		} else {
			businessEvent = businessEventBuilder.build();
			objectValidator.validate(BusinessEvent.class, businessEvent);
		}
		
		return businessEvent;
	}

	protected abstract BusinessEvent.BusinessEventBuilder doEvaluate(List<? extends Instruction> instruction, EventIntentEnum intent, Date eventDate, Date effectiveDate);

	public static class Create_BusinessEventDefault extends Create_BusinessEvent {
		@Override
		protected BusinessEvent.BusinessEventBuilder doEvaluate(List<? extends Instruction> instruction, EventIntentEnum intent, Date eventDate, Date effectiveDate) {
			if (instruction == null) {
				instruction = Collections.emptyList();
			}
			BusinessEvent.BusinessEventBuilder businessEvent = BusinessEvent.builder();
			return assignOutput(businessEvent, instruction, intent, eventDate, effectiveDate);
		}
		
		protected BusinessEvent.BusinessEventBuilder assignOutput(BusinessEvent.BusinessEventBuilder businessEvent, List<? extends Instruction> instruction, EventIntentEnum intent, Date eventDate, Date effectiveDate) {
			businessEvent
				.addInstruction(instruction);
			
			businessEvent
				.setIntent(intent);
			
			businessEvent
				.setEventDate(eventDate);
			
			businessEvent
				.setEffectiveDate(effectiveDate);
			
			final MapperListOfLists<TradeState> thenResult = MapperC.<Instruction>of(instruction)
				.mapItemToList(item -> {
					if (exists(item.<PrimitiveInstruction>map("getPrimitiveInstruction", _instruction -> _instruction.getPrimitiveInstruction()).<SplitInstruction>map("getSplit", primitiveInstruction -> primitiveInstruction.getSplit())).getOrDefault(false)) {
						return MapperC.<TradeState>of(create_Split.evaluate(item.<PrimitiveInstruction>map("getPrimitiveInstruction", _instruction -> _instruction.getPrimitiveInstruction()).<SplitInstruction>map("getSplit", primitiveInstruction -> primitiveInstruction.getSplit()).<PrimitiveInstruction>mapC("getBreakdown", splitInstruction -> splitInstruction.getBreakdown()).getMulti(), item.<ReferenceWithMetaTradeState>map("getBefore", _instruction -> _instruction.getBefore()).<TradeState>map("getValue", _f->_f.getValue()).get()));
					}
					if (exists(item.<PrimitiveInstruction>map("getPrimitiveInstruction", _instruction -> _instruction.getPrimitiveInstruction()).<ExerciseInstruction>map("getExercise", primitiveInstruction -> primitiveInstruction.getExercise())).getOrDefault(false)) {
						return MapperC.<TradeState>of(create_Exercise.evaluate(item.<PrimitiveInstruction>map("getPrimitiveInstruction", _instruction -> _instruction.getPrimitiveInstruction()).<ExerciseInstruction>map("getExercise", primitiveInstruction -> primitiveInstruction.getExercise()).get(), item.<ReferenceWithMetaTradeState>map("getBefore", _instruction -> _instruction.getBefore()).<TradeState>map("getValue", _f->_f.getValue()).get()));
					}
					return MapperC.<TradeState>of(MapperS.of(create_TradeState.evaluate(item.<PrimitiveInstruction>map("getPrimitiveInstruction", _instruction -> _instruction.getPrimitiveInstruction()).get(), item.<ReferenceWithMetaTradeState>map("getBefore", _instruction -> _instruction.getBefore()).<TradeState>map("getValue", _f->_f.getValue()).get())));
				});
			businessEvent
				.addAfter(thenResult
					.flattenList().getMulti());
			
			return Optional.ofNullable(businessEvent)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
