package cdm.event.common.functions;

import cdm.event.common.BusinessEvent;
import cdm.event.common.Instruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradableProduct.TradableProductBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(ExtractBeforeTradableProduct.ExtractBeforeTradableProductDefault.class)
public abstract class ExtractBeforeTradableProduct implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

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

	public static class ExtractBeforeTradableProductDefault extends ExtractBeforeTradableProduct {
		@Override
		protected TradableProduct.TradableProductBuilder doEvaluate(BusinessEvent businessEvent) {
			TradableProduct.TradableProductBuilder tradableProduct = TradableProduct.builder();
			return assignOutput(tradableProduct, businessEvent);
		}
		
		protected TradableProduct.TradableProductBuilder assignOutput(TradableProduct.TradableProductBuilder tradableProduct, BusinessEvent businessEvent) {
			tradableProduct = toBuilder(MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).get()).<ReferenceWithMetaTradeState>map("getBefore", instruction -> instruction.getBefore()).<TradeState>map("getValue", _f->_f.getValue()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).get());
			
			return Optional.ofNullable(tradableProduct)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
