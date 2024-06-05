package cdm.event.common.functions;

import cdm.base.math.CompareOp;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import cdm.product.template.functions.CompareTradeLot;
import cdm.product.template.functions.CompareTradeLotToAmount;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(QuantityDecreased.QuantityDecreasedDefault.class)
public abstract class QuantityDecreased implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected CompareTradeLot compareTradeLot;
	@Inject protected CompareTradeLotToAmount compareTradeLotToAmount;

	/**
	* @param before 
	* @param after 
	* @return result 
	*/
	public Boolean evaluate(TradeState before, List<? extends TradeState> after) {
		Boolean result = doEvaluate(before, after);
		
		return result;
	}

	protected abstract Boolean doEvaluate(TradeState before, List<? extends TradeState> after);

	public static class QuantityDecreasedDefault extends QuantityDecreased {
		@Override
		protected Boolean doEvaluate(TradeState before, List<? extends TradeState> after) {
			if (after == null) {
				after = Collections.emptyList();
			}
			Boolean result = null;
			return assignOutput(result, before, after);
		}
		
		protected Boolean assignOutput(Boolean result, TradeState before, List<? extends TradeState> after) {
			result = areEqual(MapperC.<TradeState>of(after)
				.mapItem(item -> areEqual(MapperS.of(compareTradeLot.evaluate(item.<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get(), CompareOp.LESS_THAN, MapperS.of(before).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get())), MapperS.of(true), CardinalityOperator.All).and(areEqual(MapperS.of(compareTradeLotToAmount.evaluate(item.<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get(), CompareOp.GREATER_THAN, new BigDecimal("0.0"))), MapperS.of(true), CardinalityOperator.All)).asMapper()), MapperS.of(true), CardinalityOperator.All).get();
			
			return result;
		}
	}
}
