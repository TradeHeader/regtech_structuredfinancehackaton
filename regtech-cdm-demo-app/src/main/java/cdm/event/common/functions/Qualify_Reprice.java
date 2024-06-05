package cdm.event.common.functions;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.event.common.BusinessEvent;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.product.asset.InterestRatePayout;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_Reprice.Qualify_RepriceDefault.class)
public abstract class Qualify_Reprice implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
	@Inject protected ExtractAfterTradableProduct extractAfterTradableProduct;
	@Inject protected ExtractBeforeEconomicTerms extractBeforeEconomicTerms;
	@Inject protected ExtractBeforeTradableProduct extractBeforeTradableProduct;
	@Inject protected ExtractOpenEconomicTerms extractOpenEconomicTerms;
	@Inject protected ExtractTradeCollateralPrice extractTradeCollateralPrice;
	@Inject protected ExtractTradeCollateralQuantity extractTradeCollateralQuantity;
	@Inject protected ExtractTradePurchasePrice extractTradePurchasePrice;
	@Inject protected FilterClosedTradeStates filterClosedTradeStates;
	@Inject protected FilterOpenTradeStates filterOpenTradeStates;

	/**
	* @param businessEvent 
	* @return is_event 
	*/
	@Override
	public Boolean evaluate(BusinessEvent businessEvent) {
		Boolean is_event = doEvaluate(businessEvent);
		
		return is_event;
	}

	protected abstract Boolean doEvaluate(BusinessEvent businessEvent);

	protected abstract MapperC<? extends Trade> openTrades(BusinessEvent businessEvent);

	protected abstract MapperC<? extends TradeState> closedTradeState(BusinessEvent businessEvent);

	protected abstract MapperS<? extends TradableProduct> beforeTradableProduct(BusinessEvent businessEvent);

	protected abstract MapperS<? extends TradableProduct> afterTradableProduct(BusinessEvent businessEvent);

	protected abstract MapperC<BigDecimal> beforeTradePurchasePrice(BusinessEvent businessEvent);

	protected abstract MapperC<BigDecimal> afterTradePurchasePrice(BusinessEvent businessEvent);

	protected abstract MapperC<BigDecimal> beforeTradeCollateralQuantity(BusinessEvent businessEvent);

	protected abstract MapperC<BigDecimal> afterTradeCollateralQuantity(BusinessEvent businessEvent);

	protected abstract MapperC<BigDecimal> beforeTradeCollateralPrice(BusinessEvent businessEvent);

	protected abstract MapperC<BigDecimal> afterTradeCollateralPrice(BusinessEvent businessEvent);

	protected abstract MapperS<? extends EconomicTerms> beforeEconomicterms(BusinessEvent businessEvent);

	protected abstract MapperS<? extends EconomicTerms> openEconomicTerms(BusinessEvent businessEvent);

	public static class Qualify_RepriceDefault extends Qualify_Reprice {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = exists(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<InterestRatePayout>mapC("getInterestRatePayout", payout -> payout.getInterestRatePayout())).and(areEqual(MapperS.of(openTrades(businessEvent).resultCount()), MapperS.of(1), CardinalityOperator.All)).and(areEqual(MapperS.of(closedTradeState(businessEvent).resultCount()), MapperS.of(1), CardinalityOperator.All)).and(exists(beforeTradePurchasePrice(businessEvent))).and(exists(afterTradePurchasePrice(businessEvent))).and(notEqual(afterTradePurchasePrice(businessEvent), beforeTradePurchasePrice(businessEvent), CardinalityOperator.Any)).and(areEqual(beforeTradeCollateralQuantity(businessEvent), afterTradeCollateralQuantity(businessEvent), CardinalityOperator.All)).and(notEqual(beforeTradeCollateralPrice(businessEvent), afterTradeCollateralPrice(businessEvent), CardinalityOperator.Any)).and(exists(beforeEconomicterms(businessEvent))).and(exists(openEconomicTerms(businessEvent))).and(areEqual(openEconomicTerms(businessEvent).<AdjustableOrRelativeDate>map("getTerminationDate", economicTerms -> economicTerms.getTerminationDate()), beforeEconomicterms(businessEvent).<AdjustableOrRelativeDate>map("getTerminationDate", economicTerms -> economicTerms.getTerminationDate()), CardinalityOperator.All)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperC<? extends Trade> openTrades(BusinessEvent businessEvent) {
			return MapperC.<TradeState>of(filterOpenTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti())).<Trade>map("getTrade", tradeState -> tradeState.getTrade());
		}
		
		@Override
		protected MapperC<? extends TradeState> closedTradeState(BusinessEvent businessEvent) {
			return MapperC.<TradeState>of(filterClosedTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti()));
		}
		
		@Override
		protected MapperS<? extends TradableProduct> beforeTradableProduct(BusinessEvent businessEvent) {
			return MapperS.of(extractBeforeTradableProduct.evaluate(businessEvent));
		}
		
		@Override
		protected MapperS<? extends TradableProduct> afterTradableProduct(BusinessEvent businessEvent) {
			return MapperS.of(extractAfterTradableProduct.evaluate(businessEvent));
		}
		
		@Override
		protected MapperC<BigDecimal> beforeTradePurchasePrice(BusinessEvent businessEvent) {
			return MapperC.<BigDecimal>of(extractTradePurchasePrice.evaluate(beforeTradableProduct(businessEvent).get()));
		}
		
		@Override
		protected MapperC<BigDecimal> afterTradePurchasePrice(BusinessEvent businessEvent) {
			return MapperC.<BigDecimal>of(extractTradePurchasePrice.evaluate(afterTradableProduct(businessEvent).get()));
		}
		
		@Override
		protected MapperC<BigDecimal> beforeTradeCollateralQuantity(BusinessEvent businessEvent) {
			return MapperC.<BigDecimal>of(extractTradeCollateralQuantity.evaluate(beforeTradableProduct(businessEvent).get()));
		}
		
		@Override
		protected MapperC<BigDecimal> afterTradeCollateralQuantity(BusinessEvent businessEvent) {
			return MapperC.<BigDecimal>of(extractTradeCollateralQuantity.evaluate(afterTradableProduct(businessEvent).get()));
		}
		
		@Override
		protected MapperC<BigDecimal> beforeTradeCollateralPrice(BusinessEvent businessEvent) {
			return MapperC.<BigDecimal>of(extractTradeCollateralPrice.evaluate(beforeTradableProduct(businessEvent).get()));
		}
		
		@Override
		protected MapperC<BigDecimal> afterTradeCollateralPrice(BusinessEvent businessEvent) {
			return MapperC.<BigDecimal>of(extractTradeCollateralPrice.evaluate(afterTradableProduct(businessEvent).get()));
		}
		
		@Override
		protected MapperS<? extends EconomicTerms> beforeEconomicterms(BusinessEvent businessEvent) {
			return MapperS.of(extractBeforeEconomicTerms.evaluate(businessEvent));
		}
		
		@Override
		protected MapperS<? extends EconomicTerms> openEconomicTerms(BusinessEvent businessEvent) {
			return MapperS.of(extractOpenEconomicTerms.evaluate(businessEvent));
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
