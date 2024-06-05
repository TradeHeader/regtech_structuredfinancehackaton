package cdm.product.template.functions;

import cdm.product.template.TradableProduct;
import cdm.product.template.TradableProduct.TradableProductBuilder;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(AddTradeLot.AddTradeLotDefault.class)
public abstract class AddTradeLot implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param tradableProduct Input list of TradeLots.
	* @param newTradeLot The TradeLot to append to the list.
	* @return updatedTradableProduct 
	*/
	public TradableProduct evaluate(TradableProduct tradableProduct, TradeLot newTradeLot) {
		TradableProduct.TradableProductBuilder updatedTradableProductBuilder = doEvaluate(tradableProduct, newTradeLot);
		
		final TradableProduct updatedTradableProduct;
		if (updatedTradableProductBuilder == null) {
			updatedTradableProduct = null;
		} else {
			updatedTradableProduct = updatedTradableProductBuilder.build();
			objectValidator.validate(TradableProduct.class, updatedTradableProduct);
		}
		
		return updatedTradableProduct;
	}

	protected abstract TradableProduct.TradableProductBuilder doEvaluate(TradableProduct tradableProduct, TradeLot newTradeLot);

	public static class AddTradeLotDefault extends AddTradeLot {
		@Override
		protected TradableProduct.TradableProductBuilder doEvaluate(TradableProduct tradableProduct, TradeLot newTradeLot) {
			TradableProduct.TradableProductBuilder updatedTradableProduct = TradableProduct.builder();
			return assignOutput(updatedTradableProduct, tradableProduct, newTradeLot);
		}
		
		protected TradableProduct.TradableProductBuilder assignOutput(TradableProduct.TradableProductBuilder updatedTradableProduct, TradableProduct tradableProduct, TradeLot newTradeLot) {
			updatedTradableProduct = toBuilder(tradableProduct);
			
			updatedTradableProduct
				.addTradeLot((newTradeLot == null ? Collections.<TradeLot>emptyList() : Collections.singletonList(newTradeLot)));
			
			return Optional.ofNullable(updatedTradableProduct)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
