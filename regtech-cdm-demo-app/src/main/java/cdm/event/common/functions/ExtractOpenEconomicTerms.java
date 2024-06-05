package cdm.event.common.functions;

import cdm.event.common.BusinessEvent;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.EconomicTerms.EconomicTermsBuilder;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(ExtractOpenEconomicTerms.ExtractOpenEconomicTermsDefault.class)
public abstract class ExtractOpenEconomicTerms implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected FilterOpenTradeStates filterOpenTradeStates;

	/**
	* @param businessEvent 
	* @return economicTerms 
	*/
	public EconomicTerms evaluate(BusinessEvent businessEvent) {
		EconomicTerms.EconomicTermsBuilder economicTermsBuilder = doEvaluate(businessEvent);
		
		final EconomicTerms economicTerms;
		if (economicTermsBuilder == null) {
			economicTerms = null;
		} else {
			economicTerms = economicTermsBuilder.build();
			objectValidator.validate(EconomicTerms.class, economicTerms);
		}
		
		return economicTerms;
	}

	protected abstract EconomicTerms.EconomicTermsBuilder doEvaluate(BusinessEvent businessEvent);

	public static class ExtractOpenEconomicTermsDefault extends ExtractOpenEconomicTerms {
		@Override
		protected EconomicTerms.EconomicTermsBuilder doEvaluate(BusinessEvent businessEvent) {
			EconomicTerms.EconomicTermsBuilder economicTerms = EconomicTerms.builder();
			return assignOutput(economicTerms, businessEvent);
		}
		
		protected EconomicTerms.EconomicTermsBuilder assignOutput(EconomicTerms.EconomicTermsBuilder economicTerms, BusinessEvent businessEvent) {
			economicTerms = toBuilder(MapperS.of(MapperC.of(filterOpenTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti())).get()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).get());
			
			return Optional.ofNullable(economicTerms)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
