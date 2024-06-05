package cdm.event.common.functions;

import cdm.event.common.ObservationEvent;
import cdm.event.common.ObservationInstruction;
import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_Observation.Create_ObservationDefault.class)
public abstract class Create_Observation implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param instruction 
	* @param before Specifies the trade to be updated.
	* @return after Specifies the resulting trade state incorporating the observation event in the observation history.
	*/
	public TradeState evaluate(ObservationInstruction instruction, TradeState before) {
		TradeState.TradeStateBuilder afterBuilder = doEvaluate(instruction, before);
		
		final TradeState after;
		if (afterBuilder == null) {
			after = null;
		} else {
			after = afterBuilder.build();
			objectValidator.validate(TradeState.class, after);
		}
		
		return after;
	}

	protected abstract TradeState.TradeStateBuilder doEvaluate(ObservationInstruction instruction, TradeState before);

	public static class Create_ObservationDefault extends Create_Observation {
		@Override
		protected TradeState.TradeStateBuilder doEvaluate(ObservationInstruction instruction, TradeState before) {
			TradeState.TradeStateBuilder after = TradeState.builder();
			return assignOutput(after, instruction, before);
		}
		
		protected TradeState.TradeStateBuilder assignOutput(TradeState.TradeStateBuilder after, ObservationInstruction instruction, TradeState before) {
			after = toBuilder(before);
			
			after
				.addObservationHistory(MapperS.of(instruction).<ObservationEvent>map("getObservationEvent", observationInstruction -> observationInstruction.getObservationEvent()).getMulti());
			
			return Optional.ofNullable(after)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
