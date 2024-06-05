package cdm.event.common.functions;

import cdm.event.common.Trade;
import cdm.event.common.Trade.TradeBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(TradeNoExecutionDetails.TradeNoExecutionDetailsDefault.class)
public abstract class TradeNoExecutionDetails implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected EmptyExecutionDetails emptyExecutionDetails;

	/**
	* @param trade 
	* @return newTrade 
	*/
	public Trade evaluate(Trade trade) {
		Trade.TradeBuilder newTradeBuilder = doEvaluate(trade);
		
		final Trade newTrade;
		if (newTradeBuilder == null) {
			newTrade = null;
		} else {
			newTrade = newTradeBuilder.build();
			objectValidator.validate(Trade.class, newTrade);
		}
		
		return newTrade;
	}

	protected abstract Trade.TradeBuilder doEvaluate(Trade trade);

	public static class TradeNoExecutionDetailsDefault extends TradeNoExecutionDetails {
		@Override
		protected Trade.TradeBuilder doEvaluate(Trade trade) {
			Trade.TradeBuilder newTrade = Trade.builder();
			return assignOutput(newTrade, trade);
		}
		
		protected Trade.TradeBuilder assignOutput(Trade.TradeBuilder newTrade, Trade trade) {
			newTrade = toBuilder(trade);
			
			newTrade
				.setExecutionDetails(emptyExecutionDetails.evaluate());
			
			return Optional.ofNullable(newTrade)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
