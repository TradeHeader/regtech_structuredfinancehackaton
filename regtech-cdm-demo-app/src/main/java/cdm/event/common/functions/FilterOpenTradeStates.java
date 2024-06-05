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

@ImplementedBy(FilterOpenTradeStates.FilterOpenTradeStatesDefault.class)
public abstract class FilterOpenTradeStates implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param tradeStates 
	* @return openTradeStates 
	*/
	public List<? extends TradeState> evaluate(List<? extends TradeState> tradeStates) {
		List<TradeState.TradeStateBuilder> openTradeStatesBuilder = doEvaluate(tradeStates);
		
		final List<? extends TradeState> openTradeStates;
		if (openTradeStatesBuilder == null) {
			openTradeStates = null;
		} else {
			openTradeStates = openTradeStatesBuilder.stream().map(TradeState::build).collect(Collectors.toList());
			objectValidator.validate(TradeState.class, openTradeStates);
		}
		
		return openTradeStates;
	}

	protected abstract List<TradeState.TradeStateBuilder> doEvaluate(List<? extends TradeState> tradeStates);

	public static class FilterOpenTradeStatesDefault extends FilterOpenTradeStates {
		@Override
		protected List<TradeState.TradeStateBuilder> doEvaluate(List<? extends TradeState> tradeStates) {
			if (tradeStates == null) {
				tradeStates = Collections.emptyList();
			}
			List<TradeState.TradeStateBuilder> openTradeStates = new ArrayList<>();
			return assignOutput(openTradeStates, tradeStates);
		}
		
		protected List<TradeState.TradeStateBuilder> assignOutput(List<TradeState.TradeStateBuilder> openTradeStates, List<? extends TradeState> tradeStates) {
			openTradeStates.addAll(toBuilder(MapperC.<TradeState>of(tradeStates)
				.filterItemNullSafe(item -> notExists(item.<State>map("getState", tradeState -> tradeState.getState()).<ClosedState>map("getClosedState", state -> state.getClosedState())).get()).getMulti()));
			
			return Optional.ofNullable(openTradeStates)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
