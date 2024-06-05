package cdm.product.template.functions;

import cdm.base.math.CompareOp;
import cdm.base.math.NonNegativeQuantity;
import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.UnitType;
import cdm.base.math.functions.CompareQuantityByUnitOfAmount;
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

@ImplementedBy(CompareTradeLot.CompareTradeLotDefault.class)
public abstract class CompareTradeLot implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected CompareQuantityByUnitOfAmount compareQuantityByUnitOfAmount;

	/**
	* @param tradeLot1 
	* @param op 
	* @param tradeLot2 
	* @return result 
	*/
	public Boolean evaluate(TradeLot tradeLot1, CompareOp op, TradeLot tradeLot2) {
		Boolean result = doEvaluate(tradeLot1, op, tradeLot2);
		
		return result;
	}

	protected abstract Boolean doEvaluate(TradeLot tradeLot1, CompareOp op, TradeLot tradeLot2);

	protected abstract MapperC<? extends UnitType> unitOfAmounts(TradeLot tradeLot1, CompareOp op, TradeLot tradeLot2);

	public static class CompareTradeLotDefault extends CompareTradeLot {
		@Override
		protected Boolean doEvaluate(TradeLot tradeLot1, CompareOp op, TradeLot tradeLot2) {
			Boolean result = null;
			return assignOutput(result, tradeLot1, op, tradeLot2);
		}
		
		protected Boolean assignOutput(Boolean result, TradeLot tradeLot1, CompareOp op, TradeLot tradeLot2) {
			result = areEqual(unitOfAmounts(tradeLot1, op, tradeLot2)
				.mapItem(unitOfAmount -> MapperS.of(compareQuantityByUnitOfAmount.evaluate(MapperS.of(tradeLot1).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue())
					.mapItem(item -> MapperS.of(NonNegativeQuantity.builder()
						.setValue(item.<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get())
						.setUnit(item.<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).get())
						.build()
					)).getMulti(), op, MapperS.of(tradeLot2).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue())
					.mapItem(item -> MapperS.of(NonNegativeQuantity.builder()
						.setValue(item.<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get())
						.setUnit(item.<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).get())
						.build()
					)).getMulti(), unitOfAmount.get()))), MapperS.of(true), CardinalityOperator.All).get();
			
			return result;
		}
		
		@Override
		protected MapperC<? extends UnitType> unitOfAmounts(TradeLot tradeLot1, CompareOp op, TradeLot tradeLot2) {
			return distinct(MapperS.of(tradeLot2).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()));
		}
	}
}
