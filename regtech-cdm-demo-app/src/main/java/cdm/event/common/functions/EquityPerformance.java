package cdm.event.common.functions;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.event.common.Trade;
import cdm.observable.asset.Observable;
import cdm.observable.asset.Price;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.product.asset.PriceReturnTerms;
import cdm.product.asset.functions.ResolvePerformancePeriodStartPrice;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
import cdm.product.template.PerformancePayout;
import cdm.product.template.Product;
import cdm.product.template.ReturnTerms;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(EquityPerformance.EquityPerformanceDefault.class)
public abstract class EquityPerformance implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected EquityNotionalAmount equityNotionalAmount;
	@Inject protected RateOfReturn rateOfReturn0;
	@Inject protected ResolvePerformancePeriodStartPrice resolvePerformancePeriodStartPrice;

	/**
	* @param trade 
	* @param observation 
	* @param date 
	* @return equityPerformance 
	*/
	public BigDecimal evaluate(Trade trade, Price observation, Date date) {
		// pre-conditions
		conditionValidator.validate(() -> exists(MapperS.of(trade).<TradableProduct>map("getTradableProduct", _trade -> _trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout()).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<PriceReturnTerms>map("getPriceReturnTerms", returnTerms -> returnTerms.getPriceReturnTerms())),
			"");
		
		BigDecimal equityPerformance = doEvaluate(trade, observation, date);
		
		return equityPerformance;
	}

	protected abstract BigDecimal doEvaluate(Trade trade, Price observation, Date date);

	protected abstract MapperS<? extends PerformancePayout> performancePayout(Trade trade, Price observation, Date date);

	protected abstract MapperS<? extends PriceSchedule> periodStartPrice(Trade trade, Price observation, Date date);

	protected abstract MapperS<? extends Price> periodEndPrice(Trade trade, Price observation, Date date);

	protected abstract MapperS<BigDecimal> numberOfSecurities(Trade trade, Price observation, Date date);

	protected abstract MapperS<BigDecimal> rateOfReturn1(Trade trade, Price observation, Date date);

	protected abstract MapperS<BigDecimal> notionalAmount(Trade trade, Price observation, Date date);

	public static class EquityPerformanceDefault extends EquityPerformance {
		@Override
		protected BigDecimal doEvaluate(Trade trade, Price observation, Date date) {
			BigDecimal equityPerformance = null;
			return assignOutput(equityPerformance, trade, observation, date);
		}
		
		protected BigDecimal assignOutput(BigDecimal equityPerformance, Trade trade, Price observation, Date date) {
			equityPerformance = MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(rateOfReturn1(trade, observation, date), notionalAmount(trade, observation, date)).get();
			
			return equityPerformance;
		}
		
		@Override
		protected MapperS<? extends PerformancePayout> performancePayout(Trade trade, Price observation, Date date) {
			return MapperS.of(MapperS.of(trade).<TradableProduct>map("getTradableProduct", _trade -> _trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<PerformancePayout>mapC("getPerformancePayout", payout -> payout.getPerformancePayout()).get());
		}
		
		@Override
		protected MapperS<? extends PriceSchedule> periodStartPrice(Trade trade, Price observation, Date date) {
			return MapperS.of(resolvePerformancePeriodStartPrice.evaluate(performancePayout(trade, observation, date).get(), MapperS.of(MapperS.of(trade).<TradableProduct>map("getTradableProduct", _trade -> _trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).getMulti(), MapperS.of(trade).<TradableProduct>map("getTradableProduct", _trade -> _trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<Observable>map("getObservable", priceQuantity -> priceQuantity.getObservable()).get(), date));
		}
		
		@Override
		protected MapperS<? extends Price> periodEndPrice(Trade trade, Price observation, Date date) {
			return MapperS.of(observation);
		}
		
		@Override
		protected MapperS<BigDecimal> numberOfSecurities(Trade trade, Price observation, Date date) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>divide(performancePayout(trade, observation, date).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<ReferenceWithMetaNonNegativeQuantitySchedule>map("getQuantitySchedule", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantitySchedule()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), periodStartPrice(trade, observation, date).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()));
		}
		
		@Override
		protected MapperS<BigDecimal> rateOfReturn1(Trade trade, Price observation, Date date) {
			return MapperS.of(rateOfReturn0.evaluate(periodStartPrice(trade, observation, date).get(), periodEndPrice(trade, observation, date).get()));
		}
		
		@Override
		protected MapperS<BigDecimal> notionalAmount(Trade trade, Price observation, Date date) {
			return MapperS.of(equityNotionalAmount.evaluate(numberOfSecurities(trade, observation, date).get(), periodEndPrice(trade, observation, date).get()));
		}
	}
}
