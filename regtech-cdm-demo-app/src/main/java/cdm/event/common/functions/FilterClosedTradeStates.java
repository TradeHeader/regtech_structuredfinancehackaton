package cdm.event.common.functions;

import cdm.event.common.State;
import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import cdm.legaldocumentation.common.ClosedState;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(FilterClosedTradeStates.FilterClosedTradeStatesDefault.class)
public abstract class FilterClosedTradeStates implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param tradeStates 
	* @return closedTradeStates 
	*/
	public List<? extends TradeState> evaluate(List<? extends TradeState> tradeStates) {
		List<TradeState.TradeStateBuilder> closedTradeStatesBuilder = doEvaluate(tradeStates);
		
		final List<? extends TradeState> closedTradeStates;
		if (closedTradeStatesBuilder == null) {
			closedTradeStates = null;
		} else {
			closedTradeStates = closedTradeStatesBuilder.stream().map(TradeState::build).collect(Collectors.toList());
			objectValidator.validate(TradeState.class, closedTradeStates);
		}
		
		return closedTradeStates;
	}

	protected abstract List<TradeState.TradeStateBuilder> doEvaluate(List<? extends TradeState> tradeStates);

	public static class FilterClosedTradeStatesDefault extends FilterClosedTradeStates {
		@Override
		protected List<TradeState.TradeStateBuilder> doEvaluate(List<? extends TradeState> tradeStates) {
			if (tradeStates == null) {
				tradeStates = Collections.emptyList();
			}
			List<TradeState.TradeStateBuilder> closedTradeStates = new ArrayList<>();
			return assignOutput(closedTradeStates, tradeStates);
		}
		
		protected List<TradeState.TradeStateBuilder> assignOutput(List<TradeState.TradeStateBuilder> closedTradeStates, List<? extends TradeState> tradeStates) {
			closedTradeStates.addAll(toBuilder(MapperC.<TradeState>of(tradeStates)
				.filterItemNullSafe(item -> exists(item.<State>map("getState", tradeState -> tradeState.getState()).<ClosedState>map("getClosedState", state -> state.getClosedState())).get()).getMulti()));
			
			return Optional.ofNullable(closedTradeStates)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
