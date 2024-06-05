package cdm.event.common.functions;

import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;


@ImplementedBy(Create_Split.Create_SplitDefault.class)
public abstract class Create_Split implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_TradeState create_TradeState;

	/**
	* @param breakdown Each primitive instruction contains the set of instructions to be applied to each post-split trade.
	* @param originalTrade The original trade to be split, which must be of single cardinality.
	* @return splitTrade 
	*/
	public List<? extends TradeState> evaluate(List<? extends PrimitiveInstruction> breakdown, TradeState originalTrade) {
		List<TradeState.TradeStateBuilder> splitTradeBuilder = doEvaluate(breakdown, originalTrade);
		
		final List<? extends TradeState> splitTrade;
		if (splitTradeBuilder == null) {
			splitTrade = null;
		} else {
			splitTrade = splitTradeBuilder.stream().map(TradeState::build).collect(Collectors.toList());
			objectValidator.validate(TradeState.class, splitTrade);
		}
		
		return splitTrade;
	}

	protected abstract List<TradeState.TradeStateBuilder> doEvaluate(List<? extends PrimitiveInstruction> breakdown, TradeState originalTrade);

	public static class Create_SplitDefault extends Create_Split {
		@Override
		protected List<TradeState.TradeStateBuilder> doEvaluate(List<? extends PrimitiveInstruction> breakdown, TradeState originalTrade) {
			if (breakdown == null) {
				breakdown = Collections.emptyList();
			}
			List<TradeState.TradeStateBuilder> splitTrade = new ArrayList<>();
			return assignOutput(splitTrade, breakdown, originalTrade);
		}
		
		protected List<TradeState.TradeStateBuilder> assignOutput(List<TradeState.TradeStateBuilder> splitTrade, List<? extends PrimitiveInstruction> breakdown, TradeState originalTrade) {
			splitTrade.addAll(toBuilder(MapperC.<PrimitiveInstruction>of(breakdown)
				.mapItem(item -> MapperS.of(create_TradeState.evaluate(item.get(), originalTrade))).getMulti()));
			
			return Optional.ofNullable(splitTrade)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
