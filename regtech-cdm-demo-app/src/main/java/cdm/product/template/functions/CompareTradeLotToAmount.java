package cdm.product.template.functions;

import cdm.base.math.CompareOp;
import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.functions.CompareNumbers;
import cdm.base.math.metafields.FieldWithMetaNonNegativeQuantitySchedule;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(CompareTradeLotToAmount.CompareTradeLotToAmountDefault.class)
public abstract class CompareTradeLotToAmount implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected CompareNumbers compareNumbers;

	/**
	* @param tradeLot 
	* @param op 
	* @param amount 
	* @return result 
	*/
	public Boolean evaluate(TradeLot tradeLot, CompareOp op, BigDecimal amount) {
		Boolean result = doEvaluate(tradeLot, op, amount);
		
		return result;
	}

	protected abstract Boolean doEvaluate(TradeLot tradeLot, CompareOp op, BigDecimal amount);

	public static class CompareTradeLotToAmountDefault extends CompareTradeLotToAmount {
		@Override
		protected Boolean doEvaluate(TradeLot tradeLot, CompareOp op, BigDecimal amount) {
			Boolean result = null;
			return assignOutput(result, tradeLot, op, amount);
		}
		
		protected Boolean assignOutput(Boolean result, TradeLot tradeLot, CompareOp op, BigDecimal amount) {
			final MapperC<NonNegativeQuantitySchedule> thenResult = MapperS.of(tradeLot).<PriceQuantity>mapC("getPriceQuantity", _tradeLot -> _tradeLot.getPriceQuantity()).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue())
				.filterItemNullSafe(item -> exists(item.<BigDecimal>map("getValue", measureBase -> measureBase.getValue())).get());
			result = areEqual(thenResult
				.mapItem(item -> MapperS.of(compareNumbers.evaluate(item.<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get(), op, amount))), MapperS.of(true), CardinalityOperator.All).asMapper().get();
			
			return result;
		}
	}
}
