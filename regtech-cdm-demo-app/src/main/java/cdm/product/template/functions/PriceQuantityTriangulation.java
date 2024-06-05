package cdm.product.template.functions;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.metafields.FieldWithMetaNonNegativeQuantitySchedule;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.PriceTypeEnum;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.observable.common.functions.CashPriceQuantityNoOfUnitsTriangulation;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(PriceQuantityTriangulation.PriceQuantityTriangulationDefault.class)
public abstract class PriceQuantityTriangulation implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected CashPriceQuantityNoOfUnitsTriangulation cashPriceQuantityNoOfUnitsTriangulation;

	/**
	* @param tradeLots 
	* @return success 
	*/
	public Boolean evaluate(List<? extends TradeLot> tradeLots) {
		Boolean success = doEvaluate(tradeLots);
		
		return success;
	}

	protected abstract Boolean doEvaluate(List<? extends TradeLot> tradeLots);

	public static class PriceQuantityTriangulationDefault extends PriceQuantityTriangulation {
		@Override
		protected Boolean doEvaluate(List<? extends TradeLot> tradeLots) {
			if (tradeLots == null) {
				tradeLots = Collections.emptyList();
			}
			Boolean success = null;
			return assignOutput(success, tradeLots);
		}
		
		protected Boolean assignOutput(Boolean success, List<? extends TradeLot> tradeLots) {
			if (greaterThan(MapperS.of(MapperC.<TradeLot>of(tradeLots).resultCount()), MapperS.of(0), CardinalityOperator.All).getOrDefault(false)) {
				success = areEqual(MapperC.<TradeLot>of(tradeLots)
					.mapItem(item -> {
						if (contains(item.<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).<PriceTypeEnum>map("getPriceType", priceSchedule -> priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.CASH_PRICE)).getOrDefault(false)) {
							return MapperS.of(cashPriceQuantityNoOfUnitsTriangulation.evaluate(item.<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).getMulti(), item.<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).getMulti()));
						}
						return MapperS.of(true);
					}), MapperS.of(true), CardinalityOperator.All).get();
			} else {
				success = true;
			}
			
			return success;
		}
	}
}
