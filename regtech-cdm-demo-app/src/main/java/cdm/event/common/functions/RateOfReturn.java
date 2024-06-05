package cdm.event.common.functions;

import cdm.observable.asset.PriceSchedule;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(RateOfReturn.RateOfReturnDefault.class)
public abstract class RateOfReturn implements RosettaFunction {

	/**
	* @param initialPrice 
	* @param finalPrice 
	* @return rateOfReturn 
	*/
	public BigDecimal evaluate(PriceSchedule initialPrice, PriceSchedule finalPrice) {
		BigDecimal rateOfReturn = doEvaluate(initialPrice, finalPrice);
		
		return rateOfReturn;
	}

	protected abstract BigDecimal doEvaluate(PriceSchedule initialPrice, PriceSchedule finalPrice);

	protected abstract MapperS<BigDecimal> initialPriceValue(PriceSchedule initialPrice, PriceSchedule finalPrice);

	protected abstract MapperS<BigDecimal> finalPriceValue(PriceSchedule initialPrice, PriceSchedule finalPrice);

	public static class RateOfReturnDefault extends RateOfReturn {
		@Override
		protected BigDecimal doEvaluate(PriceSchedule initialPrice, PriceSchedule finalPrice) {
			BigDecimal rateOfReturn = null;
			return assignOutput(rateOfReturn, initialPrice, finalPrice);
		}
		
		protected BigDecimal assignOutput(BigDecimal rateOfReturn, PriceSchedule initialPrice, PriceSchedule finalPrice) {
			if (exists(finalPriceValue(initialPrice, finalPrice)).and(exists(initialPriceValue(initialPrice, finalPrice))).and(greaterThan(initialPriceValue(initialPrice, finalPrice), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All)).getOrDefault(false)) {
				rateOfReturn = MapperMaths.<BigDecimal, BigDecimal, BigDecimal>divide(MapperMaths.<BigDecimal, BigDecimal, BigDecimal>subtract(finalPriceValue(initialPrice, finalPrice), initialPriceValue(initialPrice, finalPrice)), initialPriceValue(initialPrice, finalPrice)).get();
			} else {
				rateOfReturn = null;
			}
			
			return rateOfReturn;
		}
		
		@Override
		protected MapperS<BigDecimal> initialPriceValue(PriceSchedule initialPrice, PriceSchedule finalPrice) {
			return MapperS.of(initialPrice).<BigDecimal>map("getValue", measureBase -> measureBase.getValue());
		}
		
		@Override
		protected MapperS<BigDecimal> finalPriceValue(PriceSchedule initialPrice, PriceSchedule finalPrice) {
			return MapperS.of(finalPrice).<BigDecimal>map("getValue", measureBase -> measureBase.getValue());
		}
	}
}
