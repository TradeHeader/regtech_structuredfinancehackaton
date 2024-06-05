package cdm.event.position.functions;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.UnitType;
import cdm.base.math.functions.FilterQuantityByCurrency;
import cdm.base.math.metafields.FieldWithMetaNonNegativeQuantitySchedule;
import cdm.event.common.Trade;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.ForwardPayout;
import cdm.product.template.Payout;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(FxMarkToMarket.FxMarkToMarketDefault.class)
public abstract class FxMarkToMarket implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected FilterQuantityByCurrency filterQuantityByCurrency;
	@Inject protected InterpolateForwardRate interpolateForwardRate;

	/**
	* @param trade 
	* @return value 
	*/
	public BigDecimal evaluate(Trade trade) {
		// pre-conditions
		conditionValidator.validate(() -> exists(MapperS.of(trade).<TradableProduct>map("getTradableProduct", _trade -> _trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<ForwardPayout>mapC("getForwardPayout", payout -> payout.getForwardPayout())),
			"The forwardPayout on the contract must exist.");
		
		BigDecimal value = doEvaluate(trade);
		
		return value;
	}

	protected abstract BigDecimal doEvaluate(Trade trade);

	protected abstract MapperS<? extends ForwardPayout> forwardPayout(Trade trade);

	protected abstract MapperS<String> quotedCurrency(Trade trade);

	protected abstract MapperS<String> baseCurrency(Trade trade);

	protected abstract MapperC<? extends NonNegativeQuantitySchedule> quantities(Trade trade);

	protected abstract MapperS<BigDecimal> quotedQuantity(Trade trade);

	protected abstract MapperS<BigDecimal> baseQuantity(Trade trade);

	protected abstract MapperS<BigDecimal> interpolatedRate(Trade trade);

	public static class FxMarkToMarketDefault extends FxMarkToMarket {
		@Override
		protected BigDecimal doEvaluate(Trade trade) {
			BigDecimal value = null;
			return assignOutput(value, trade);
		}
		
		protected BigDecimal assignOutput(BigDecimal value, Trade trade) {
			value = MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(MapperMaths.<BigDecimal, BigDecimal, BigDecimal>subtract(MapperMaths.<BigDecimal, BigDecimal, BigDecimal>divide(quotedQuantity(trade), interpolatedRate(trade)), baseQuantity(trade)), interpolatedRate(trade)).get();
			
			return value;
		}
		
		@Override
		protected MapperS<? extends ForwardPayout> forwardPayout(Trade trade) {
			return MapperS.of(MapperS.of(trade).<TradableProduct>map("getTradableProduct", _trade -> _trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<ForwardPayout>mapC("getForwardPayout", payout -> payout.getForwardPayout()).get());
		}
		
		@Override
		protected MapperS<String> quotedCurrency(Trade trade) {
			return MapperS.of(distinct(MapperS.of(trade).<TradableProduct>map("getTradableProduct", _trade -> _trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue())).get());
		}
		
		@Override
		protected MapperS<String> baseCurrency(Trade trade) {
			return MapperS.of(distinct(MapperS.of(trade).<TradableProduct>map("getTradableProduct", _trade -> _trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).<UnitType>map("getPerUnitOf", priceSchedule -> priceSchedule.getPerUnitOf()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue())).get());
		}
		
		@Override
		protected MapperC<? extends NonNegativeQuantitySchedule> quantities(Trade trade) {
			return MapperS.of(MapperS.of(trade).<TradableProduct>map("getTradableProduct", _trade -> _trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue());
		}
		
		@Override
		protected MapperS<BigDecimal> quotedQuantity(Trade trade) {
			return MapperS.of(MapperC.of(filterQuantityByCurrency.evaluate(quantities(trade).getMulti(), quotedCurrency(trade).get())).get()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue());
		}
		
		@Override
		protected MapperS<BigDecimal> baseQuantity(Trade trade) {
			return MapperS.of(MapperC.of(filterQuantityByCurrency.evaluate(quantities(trade).getMulti(), baseCurrency(trade).get())).get()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue());
		}
		
		@Override
		protected MapperS<BigDecimal> interpolatedRate(Trade trade) {
			return MapperS.of(interpolateForwardRate.evaluate(forwardPayout(trade).get()));
		}
	}
}
