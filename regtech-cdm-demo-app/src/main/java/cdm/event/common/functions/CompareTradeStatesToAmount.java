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

@ImplementedBy(CompareTradeStatesToAmount.CompareTradeStatesToAmountDefault.class)
public abstract class CompareTradeStatesToAmount implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected CompareTradeLotToAmount compareTradeLotToAmount;

	/**
	* @param tradeStates List of TradeState to be compared.
	* @param op Comparison operation to use.
	* @param amount Quantity amount to use.
	* @return result 
	*/
	public Boolean evaluate(List<? extends TradeState> tradeStates, CompareOp op, BigDecimal amount) {
		Boolean result = doEvaluate(tradeStates, op, amount);
		
		return result;
	}

	protected abstract Boolean doEvaluate(List<? extends TradeState> tradeStates, CompareOp op, BigDecimal amount);

	public static class CompareTradeStatesToAmountDefault extends CompareTradeStatesToAmount {
		@Override
		protected Boolean doEvaluate(List<? extends TradeState> tradeStates, CompareOp op, BigDecimal amount) {
			if (tradeStates == null) {
				tradeStates = Collections.emptyList();
			}
			Boolean result = null;
			return assignOutput(result, tradeStates, op, amount);
		}
		
		protected Boolean assignOutput(Boolean result, List<? extends TradeState> tradeStates, CompareOp op, BigDecimal amount) {
			result = areEqual(MapperC.<TradeState>of(tradeStates)
				.mapItem(item -> MapperS.of(compareTradeLotToAmount.evaluate(item.<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get(), op, amount))), MapperS.of(true), CardinalityOperator.All).get();
			
			return result;
		}
	}
}
