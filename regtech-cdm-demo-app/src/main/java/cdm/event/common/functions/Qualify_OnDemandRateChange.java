package cdm.event.common.functions;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.metafields.FieldWithMetaNonNegativeQuantitySchedule;
import cdm.event.common.BusinessEvent;
import cdm.event.common.Instruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.observable.asset.Observable;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.PriceTypeEnum;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.product.collateral.Collateral;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperListOfLists;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_OnDemandRateChange.Qualify_OnDemandRateChangeDefault.class)
public abstract class Qualify_OnDemandRateChange implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
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

	protected abstract MapperS<? extends TradableProduct> beforeTradableProduct(BusinessEvent businessEvent);

	protected abstract MapperS<? extends EconomicTerms> beforeEconomicterms(BusinessEvent businessEvent);

	protected abstract MapperS<? extends TradableProduct> openTradableProduct(BusinessEvent businessEvent);

	protected abstract MapperS<? extends EconomicTerms> openEconomicTerms(BusinessEvent businessEvent);

	protected abstract MapperC<? extends TradeState> closedTradeState(BusinessEvent businessEvent);

	protected abstract MapperC<BigDecimal> beforePriceQuantityRateOnly(BusinessEvent businessEvent);

	protected abstract MapperC<BigDecimal> openPriceQuantityRateOnly(BusinessEvent businessEvent);

	protected abstract MapperC<? extends PriceQuantity> beforePriceQuantityNoRate(BusinessEvent businessEvent);

	protected abstract MapperC<? extends PriceQuantity> openPriceQuantityNoRate(BusinessEvent businessEvent);

	public static class Qualify_OnDemandRateChangeDefault extends Qualify_OnDemandRateChange {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = exists(beforeEconomicterms(businessEvent)).and(exists(openEconomicTerms(businessEvent))).and(areEqual(MapperS.of(closedTradeState(businessEvent).resultCount()), MapperS.of(1), CardinalityOperator.All)).and(areEqual(openEconomicTerms(businessEvent).<Collateral>map("getCollateral", economicTerms -> economicTerms.getCollateral()), beforeEconomicterms(businessEvent).<Collateral>map("getCollateral", economicTerms -> economicTerms.getCollateral()), CardinalityOperator.All)).and(areEqual(MapperS.of(beforePriceQuantityRateOnly(businessEvent).resultCount()), MapperS.of(1), CardinalityOperator.All)).and(areEqual(MapperS.of(openPriceQuantityRateOnly(businessEvent).resultCount()), MapperS.of(1), CardinalityOperator.All)).and(notEqual(MapperS.of(beforePriceQuantityRateOnly(businessEvent).get()), MapperS.of(openPriceQuantityRateOnly(businessEvent).get()), CardinalityOperator.Any)).and(areEqual(beforePriceQuantityNoRate(businessEvent), openPriceQuantityNoRate(businessEvent), CardinalityOperator.All)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperS<? extends TradableProduct> beforeTradableProduct(BusinessEvent businessEvent) {
			return MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).get()).<ReferenceWithMetaTradeState>map("getBefore", instruction -> instruction.getBefore()).<TradeState>map("getValue", _f->_f.getValue()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct());
		}
		
		@Override
		protected MapperS<? extends EconomicTerms> beforeEconomicterms(BusinessEvent businessEvent) {
			return beforeTradableProduct(businessEvent).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms());
		}
		
		@Override
		protected MapperS<? extends TradableProduct> openTradableProduct(BusinessEvent businessEvent) {
			return MapperS.of(MapperC.of(filterOpenTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti())).get()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct());
		}
		
		@Override
		protected MapperS<? extends EconomicTerms> openEconomicTerms(BusinessEvent businessEvent) {
			return openTradableProduct(businessEvent).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms());
		}
		
		@Override
		protected MapperC<? extends TradeState> closedTradeState(BusinessEvent businessEvent) {
			return MapperC.<TradeState>of(filterClosedTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti()));
		}
		
		@Override
		protected MapperC<BigDecimal> beforePriceQuantityRateOnly(BusinessEvent businessEvent) {
			final MapperListOfLists<PriceSchedule> thenResult0 = MapperS.of(beforeTradableProduct(businessEvent).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity())
				.mapItemToList(item -> item.<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()));
			final MapperC<PriceSchedule> thenResult1 = thenResult0
				.flattenList();
			final MapperC<PriceSchedule> thenResult2 = thenResult1
				.filterItemNullSafe(item -> areEqual(item.<PriceTypeEnum>map("getPriceType", priceSchedule -> priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.INTEREST_RATE), CardinalityOperator.All).get());
			return thenResult2
				.mapItem(item -> item.<BigDecimal>map("getValue", measureBase -> measureBase.getValue()));
		}
		
		@Override
		protected MapperC<BigDecimal> openPriceQuantityRateOnly(BusinessEvent businessEvent) {
			final MapperListOfLists<PriceSchedule> thenResult0 = MapperS.of(openTradableProduct(businessEvent).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity())
				.mapItemToList(item -> item.<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()));
			final MapperC<PriceSchedule> thenResult1 = thenResult0
				.flattenList();
			final MapperC<PriceSchedule> thenResult2 = thenResult1
				.filterItemNullSafe(item -> areEqual(item.<PriceTypeEnum>map("getPriceType", priceSchedule -> priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.INTEREST_RATE), CardinalityOperator.All).get());
			return thenResult2
				.mapItem(item -> item.<BigDecimal>map("getValue", measureBase -> measureBase.getValue()));
		}
		
		@Override
		protected MapperC<? extends PriceQuantity> beforePriceQuantityNoRate(BusinessEvent businessEvent) {
			return MapperS.of(beforeTradableProduct(businessEvent).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity())
				.mapItem(item -> MapperS.of(PriceQuantity.builder()
					.setPriceValue(item.<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue())
						.filterItemNullSafe(p -> notEqual(p.<PriceTypeEnum>map("getPriceType", priceSchedule -> priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.INTEREST_RATE), CardinalityOperator.Any).get()).getMulti())
					.setQuantityValue(item.<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).getMulti())
					.setObservable(item.<Observable>map("getObservable", priceQuantity -> priceQuantity.getObservable()).get())
					.build()
				));
		}
		
		@Override
		protected MapperC<? extends PriceQuantity> openPriceQuantityNoRate(BusinessEvent businessEvent) {
			return MapperS.of(openTradableProduct(businessEvent).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity())
				.mapItem(item -> MapperS.of(PriceQuantity.builder()
					.setPriceValue(item.<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue())
						.filterItemNullSafe(p -> notEqual(p.<PriceTypeEnum>map("getPriceType", priceSchedule -> priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.INTEREST_RATE), CardinalityOperator.Any).get()).getMulti())
					.setQuantityValue(item.<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).getMulti())
					.setObservable(item.<Observable>map("getObservable", priceQuantity -> priceQuantity.getObservable()).get())
					.build()
				));
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
