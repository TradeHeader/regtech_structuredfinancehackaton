package cdm.product.template.functions;

import cdm.base.staticdata.identifier.Identifier;
import cdm.product.template.TradeLot;
import cdm.product.template.TradeLot.TradeLotBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
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

@ImplementedBy(FilterTradeLot.FilterTradeLotDefault.class)
public abstract class FilterTradeLot implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param tradeLots List of TradeLots to filter.
	* @param lotIdentifier The lot Identifiers to filter by.
	* @return filteredTradeLots 
	*/
	public List<? extends TradeLot> evaluate(List<? extends TradeLot> tradeLots, List<? extends Identifier> lotIdentifier) {
		List<TradeLot.TradeLotBuilder> filteredTradeLotsBuilder = doEvaluate(tradeLots, lotIdentifier);
		
		final List<? extends TradeLot> filteredTradeLots;
		if (filteredTradeLotsBuilder == null) {
			filteredTradeLots = null;
		} else {
			filteredTradeLots = filteredTradeLotsBuilder.stream().map(TradeLot::build).collect(Collectors.toList());
			objectValidator.validate(TradeLot.class, filteredTradeLots);
		}
		
		return filteredTradeLots;
	}

	protected abstract List<TradeLot.TradeLotBuilder> doEvaluate(List<? extends TradeLot> tradeLots, List<? extends Identifier> lotIdentifier);

	public static class FilterTradeLotDefault extends FilterTradeLot {
		@Override
		protected List<TradeLot.TradeLotBuilder> doEvaluate(List<? extends TradeLot> tradeLots, List<? extends Identifier> lotIdentifier) {
			if (tradeLots == null) {
				tradeLots = Collections.emptyList();
			}
			if (lotIdentifier == null) {
				lotIdentifier = Collections.emptyList();
			}
			List<TradeLot.TradeLotBuilder> filteredTradeLots = new ArrayList<>();
			return assignOutput(filteredTradeLots, tradeLots, lotIdentifier);
		}
		
		protected List<TradeLot.TradeLotBuilder> assignOutput(List<TradeLot.TradeLotBuilder> filteredTradeLots, List<? extends TradeLot> tradeLots, List<? extends Identifier> lotIdentifier) {
			filteredTradeLots.addAll(toBuilder(MapperC.<TradeLot>of(tradeLots)
				.filterItemNullSafe(item -> areEqual(item.<Identifier>mapC("getLotIdentifier", tradeLot -> tradeLot.getLotIdentifier()), MapperC.<Identifier>of(lotIdentifier), CardinalityOperator.All).get()).getMulti()));
			
			return Optional.ofNullable(filteredTradeLots)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
