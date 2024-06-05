package cdm.event.common.functions;

import cdm.event.common.BusinessEvent;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradableProduct.TradableProductBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(ExtractAfterTradableProduct.ExtractAfterTradableProductDefault.class)
public abstract class ExtractAfterTradableProduct implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected FilterOpenTradeStates filterOpenTradeStates;

	/**
	* @param businessEvent 
	* @return tradableProduct 
	*/
	public TradableProduct evaluate(BusinessEvent businessEvent) {
		TradableProduct.TradableProductBuilder tradableProductBuilder = doEvaluate(businessEvent);
		
		final TradableProduct tradableProduct;
		if (tradableProductBuilder == null) {
			tradableProduct = null;
		} else {
			tradableProduct = tradableProductBuilder.build();
			objectValidator.validate(TradableProduct.class, tradableProduct);
		}
		
		return tradableProduct;
	}

	protected abstract TradableProduct.TradableProductBuilder doEvaluate(BusinessEvent businessEvent);

	public static class ExtractAfterTradableProductDefault extends ExtractAfterTradableProduct {
		@Override
		protected TradableProduct.TradableProductBuilder doEvaluate(BusinessEvent businessEvent) {
			TradableProduct.TradableProductBuilder tradableProduct = TradableProduct.builder();
			return assignOutput(tradableProduct, businessEvent);
		}
		
		protected TradableProduct.TradableProductBuilder assignOutput(TradableProduct.TradableProductBuilder tradableProduct, BusinessEvent businessEvent) {
			tradableProduct = toBuilder(MapperS.of(MapperC.of(filterOpenTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti())).get()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).get());
			
			return Optional.ofNullable(tradableProduct)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
