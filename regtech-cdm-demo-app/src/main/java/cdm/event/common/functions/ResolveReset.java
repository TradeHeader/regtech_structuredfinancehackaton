package cdm.event.common.functions;

import cdm.event.common.Reset;
import cdm.event.common.Reset.ResetBuilder;
import cdm.event.common.TradeState;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(ResolveReset.ResolveResetDefault.class)
public abstract class ResolveReset implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param tradeState 
	* @param date 
	* @return reset 
	*/
	public Reset evaluate(TradeState tradeState, Date date) {
		Reset.ResetBuilder resetBuilder = doEvaluate(tradeState, date);
		
		final Reset reset;
		if (resetBuilder == null) {
			reset = null;
		} else {
			reset = resetBuilder.build();
			objectValidator.validate(Reset.class, reset);
		}
		
		return reset;
	}

	protected abstract Reset.ResetBuilder doEvaluate(TradeState tradeState, Date date);

	public static class ResolveResetDefault extends ResolveReset {
		@Override
		protected Reset.ResetBuilder doEvaluate(TradeState tradeState, Date date) {
			Reset.ResetBuilder reset = Reset.builder();
			return assignOutput(reset, tradeState, date);
		}
		
		protected Reset.ResetBuilder assignOutput(Reset.ResetBuilder reset, TradeState tradeState, Date date) {
			return Optional.ofNullable(reset)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
