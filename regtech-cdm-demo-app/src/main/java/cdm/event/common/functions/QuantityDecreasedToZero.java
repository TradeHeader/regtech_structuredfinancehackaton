package cdm.event.common.functions;

import cdm.base.math.CompareOp;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
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

@ImplementedBy(QuantityDecreasedToZero.QuantityDecreasedToZeroDefault.class)
public abstract class QuantityDecreasedToZero implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected CompareTradeLotToAmount compareTradeLotToAmount;
	@Inject protected CompareTradeStatesToAmount compareTradeStatesToAmount;

	/**
	* @param before 
	* @param after 
	* @return result 
	*/
	public Boolean evaluate(List<? extends TradeState> before, List<? extends TradeState> after) {
		Boolean result = doEvaluate(before, after);
		
		return result;
	}

	protected abstract Boolean doEvaluate(List<? extends TradeState> before, List<? extends TradeState> after);

	public static class QuantityDecreasedToZeroDefault extends QuantityDecreasedToZero {
		@Override
		protected Boolean doEvaluate(List<? extends TradeState> before, List<? extends TradeState> after) {
			if (before == null) {
				before = Collections.emptyList();
			}
			if (after == null) {
				after = Collections.emptyList();
			}
			Boolean result = null;
			return assignOutput(result, before, after);
		}
		
		protected Boolean assignOutput(Boolean result, List<? extends TradeState> before, List<? extends TradeState> after) {
			result = areEqual(MapperS.of(compareTradeLotToAmount.evaluate(MapperC.<TradeState>of(before).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get(), CompareOp.GREATER_THAN_OR_EQUALS, new BigDecimal("0.0"))), MapperS.of(true), CardinalityOperator.All).and(areEqual(MapperS.of(compareTradeStatesToAmount.evaluate(after, CompareOp.EQUALS, new BigDecimal("0.0"))), MapperS.of(true), CardinalityOperator.All)).get();
			
			return result;
		}
	}
}
