package cdm.product.template.functions;

import cdm.base.staticdata.identifier.Identifier;
import cdm.product.template.TradeLot;
import cdm.product.template.TradeLot.TradeLotBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
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

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(MergeTradeLot.MergeTradeLotDefault.class)
public abstract class MergeTradeLot implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param tradeLots Input list of TradeLots.
	* @param newTradeLot The TradeLot to replace a matching TradeLot in the list.
	* @return mergedTradeLots 
	*/
	public List<? extends TradeLot> evaluate(List<? extends TradeLot> tradeLots, TradeLot newTradeLot) {
		List<TradeLot.TradeLotBuilder> mergedTradeLotsBuilder = doEvaluate(tradeLots, newTradeLot);
		
		final List<? extends TradeLot> mergedTradeLots;
		if (mergedTradeLotsBuilder == null) {
			mergedTradeLots = null;
		} else {
			mergedTradeLots = mergedTradeLotsBuilder.stream().map(TradeLot::build).collect(Collectors.toList());
			objectValidator.validate(TradeLot.class, mergedTradeLots);
		}
		
		return mergedTradeLots;
	}

	protected abstract List<TradeLot.TradeLotBuilder> doEvaluate(List<? extends TradeLot> tradeLots, TradeLot newTradeLot);

	public static class MergeTradeLotDefault extends MergeTradeLot {
		@Override
		protected List<TradeLot.TradeLotBuilder> doEvaluate(List<? extends TradeLot> tradeLots, TradeLot newTradeLot) {
			if (tradeLots == null) {
				tradeLots = Collections.emptyList();
			}
			List<TradeLot.TradeLotBuilder> mergedTradeLots = new ArrayList<>();
			return assignOutput(mergedTradeLots, tradeLots, newTradeLot);
		}
		
		protected List<TradeLot.TradeLotBuilder> assignOutput(List<TradeLot.TradeLotBuilder> mergedTradeLots, List<? extends TradeLot> tradeLots, TradeLot newTradeLot) {
			mergedTradeLots.addAll(toBuilder(MapperC.<TradeLot>of(tradeLots)
				.mapItem(item -> {
					if (areEqual(item.<Identifier>mapC("getLotIdentifier", tradeLot -> tradeLot.getLotIdentifier()), MapperS.of(newTradeLot).<Identifier>mapC("getLotIdentifier", tradeLot -> tradeLot.getLotIdentifier()), CardinalityOperator.All).or(notExists(item.<Identifier>mapC("getLotIdentifier", tradeLot -> tradeLot.getLotIdentifier())).and(notExists(MapperS.of(newTradeLot).<Identifier>mapC("getLotIdentifier", tradeLot -> tradeLot.getLotIdentifier())))).getOrDefault(false)) {
						return MapperS.of(newTradeLot);
					}
					return item;
				}).getMulti()));
			
			return Optional.ofNullable(mergedTradeLots)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
