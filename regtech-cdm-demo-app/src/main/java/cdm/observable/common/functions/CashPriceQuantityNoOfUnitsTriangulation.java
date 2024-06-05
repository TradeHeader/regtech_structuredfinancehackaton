package cdm.observable.common.functions;

import cdm.base.math.FinancialUnitEnum;
import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.QuantitySchedule;
import cdm.base.math.functions.FilterQuantityByCurrencyExists;
import cdm.base.math.functions.FilterQuantityByFinancialUnit;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.PriceTypeEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(CashPriceQuantityNoOfUnitsTriangulation.CashPriceQuantityNoOfUnitsTriangulationDefault.class)
public abstract class CashPriceQuantityNoOfUnitsTriangulation implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected FilterQuantityByCurrencyExists filterQuantityByCurrencyExists;
	@Inject protected FilterQuantityByFinancialUnit filterQuantityByFinancialUnit;

	/**
	* @param quantity 
	* @param price 
	* @return success 
	*/
	public Boolean evaluate(List<? extends NonNegativeQuantitySchedule> quantity, List<? extends PriceSchedule> price) {
		Boolean success = doEvaluate(quantity, price);
		
		return success;
	}

	protected abstract Boolean doEvaluate(List<? extends NonNegativeQuantitySchedule> quantity, List<? extends PriceSchedule> price);

	protected abstract MapperS<BigDecimal> notional(List<? extends NonNegativeQuantitySchedule> quantity, List<? extends PriceSchedule> price);

	protected abstract MapperS<BigDecimal> noOfUnits(List<? extends NonNegativeQuantitySchedule> quantity, List<? extends PriceSchedule> price);

	protected abstract MapperS<BigDecimal> cashPrice(List<? extends NonNegativeQuantitySchedule> quantity, List<? extends PriceSchedule> price);

	public static class CashPriceQuantityNoOfUnitsTriangulationDefault extends CashPriceQuantityNoOfUnitsTriangulation {
		@Override
		protected Boolean doEvaluate(List<? extends NonNegativeQuantitySchedule> quantity, List<? extends PriceSchedule> price) {
			if (quantity == null) {
				quantity = Collections.emptyList();
			}
			if (price == null) {
				price = Collections.emptyList();
			}
			Boolean success = null;
			return assignOutput(success, quantity, price);
		}
		
		protected Boolean assignOutput(Boolean success, List<? extends NonNegativeQuantitySchedule> quantity, List<? extends PriceSchedule> price) {
			if (exists(cashPrice(quantity, price)).and(exists(noOfUnits(quantity, price))).and(exists(notional(quantity, price))).getOrDefault(false)) {
				success = areEqual(MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(cashPrice(quantity, price), noOfUnits(quantity, price)), notional(quantity, price), CardinalityOperator.All).get();
			} else {
				success = true;
			}
			
			return success;
		}
		
		@Override
		protected MapperS<BigDecimal> notional(List<? extends NonNegativeQuantitySchedule> quantity, List<? extends PriceSchedule> price) {
			final MapperC<BigDecimal> thenResult0 = MapperC.<QuantitySchedule>of(filterQuantityByCurrencyExists.evaluate(quantity))
				.mapItem(item -> item.<BigDecimal>map("getValue", measureBase -> measureBase.getValue()));
			final MapperC<BigDecimal> thenResult1 = distinct(thenResult0);
			return MapperS.of(thenResult1.get());
		}
		
		@Override
		protected MapperS<BigDecimal> noOfUnits(List<? extends NonNegativeQuantitySchedule> quantity, List<? extends PriceSchedule> price) {
			final MapperC<BigDecimal> thenResult = MapperC.<QuantitySchedule>of(filterQuantityByFinancialUnit.evaluate(quantity, FinancialUnitEnum.SHARE))
				.mapItem(item -> item.<BigDecimal>map("getValue", measureBase -> measureBase.getValue()));
			return MapperS.of(thenResult.get());
		}
		
		@Override
		protected MapperS<BigDecimal> cashPrice(List<? extends NonNegativeQuantitySchedule> quantity, List<? extends PriceSchedule> price) {
			final MapperC<PriceSchedule> thenResult0 = MapperC.<PriceSchedule>of(price)
				.filterItemNullSafe(item -> areEqual(item.<PriceTypeEnum>map("getPriceType", priceSchedule -> priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.ASSET_PRICE), CardinalityOperator.All).get());
			final MapperC<BigDecimal> thenResult1 = thenResult0
				.mapItem(item -> item.<BigDecimal>map("getValue", measureBase -> measureBase.getValue()));
			return MapperS.of(thenResult1.get());
		}
	}
}
