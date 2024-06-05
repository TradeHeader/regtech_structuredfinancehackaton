package cdm.event.common.functions;

import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(UpdateSpreadAdjustmentAndRateOptions.UpdateSpreadAdjustmentAndRateOptionsDefault.class)
public abstract class UpdateSpreadAdjustmentAndRateOptions implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected FindMatchingIndexTransitionInstruction findMatchingIndexTransitionInstruction;
	@Inject protected UpdateIndexTransitionPriceAndRateOption updateIndexTransitionPriceAndRateOption;

	/**
	* @param tradeState Specifies the trade to be updated.
	* @param instructions List of PriceQuantity from the IndexTransitionInstruction (e.g. one for each floating rate leg).
	* @return updatedTradeState Specifies the updated trade.
	*/
	public TradeState evaluate(TradeState tradeState, List<? extends PriceQuantity> instructions) {
		TradeState.TradeStateBuilder updatedTradeStateBuilder = doEvaluate(tradeState, instructions);
		
		final TradeState updatedTradeState;
		if (updatedTradeStateBuilder == null) {
			updatedTradeState = null;
		} else {
			updatedTradeState = updatedTradeStateBuilder.build();
			objectValidator.validate(TradeState.class, updatedTradeState);
		}
		
		return updatedTradeState;
	}

	protected abstract TradeState.TradeStateBuilder doEvaluate(TradeState tradeState, List<? extends PriceQuantity> instructions);

	public static class UpdateSpreadAdjustmentAndRateOptionsDefault extends UpdateSpreadAdjustmentAndRateOptions {
		@Override
		protected TradeState.TradeStateBuilder doEvaluate(TradeState tradeState, List<? extends PriceQuantity> instructions) {
			if (instructions == null) {
				instructions = Collections.emptyList();
			}
			TradeState.TradeStateBuilder updatedTradeState = TradeState.builder();
			return assignOutput(updatedTradeState, tradeState, instructions);
		}
		
		protected TradeState.TradeStateBuilder assignOutput(TradeState.TradeStateBuilder updatedTradeState, TradeState tradeState, List<? extends PriceQuantity> instructions) {
			updatedTradeState = toBuilder(tradeState);
			
			updatedTradeState
				.getOrCreateTrade()
				.getOrCreateTradableProduct()
				.getOrCreateTradeLot(0)
				.setPriceQuantity(MapperS.of(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity())
					.mapItem(item -> MapperS.of(updateIndexTransitionPriceAndRateOption.evaluate(item.get(), findMatchingIndexTransitionInstruction.evaluate(instructions, item.get())))).getMulti());
			
			return Optional.ofNullable(updatedTradeState)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
