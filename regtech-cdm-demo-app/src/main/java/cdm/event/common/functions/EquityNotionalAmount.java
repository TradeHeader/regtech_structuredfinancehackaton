package cdm.event.common.functions;

import cdm.observable.asset.Price;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;


@ImplementedBy(EquityNotionalAmount.EquityNotionalAmountDefault.class)
public abstract class EquityNotionalAmount implements RosettaFunction {

	/**
	* @param numberOfSecurities 
	* @param price 
	* @return equityNotionalAmount 
	*/
	public BigDecimal evaluate(BigDecimal numberOfSecurities, Price price) {
		BigDecimal equityNotionalAmount = doEvaluate(numberOfSecurities, price);
		
		return equityNotionalAmount;
	}

	protected abstract BigDecimal doEvaluate(BigDecimal numberOfSecurities, Price price);

	protected abstract MapperS<BigDecimal> priceValue(BigDecimal numberOfSecurities, Price price);

	public static class EquityNotionalAmountDefault extends EquityNotionalAmount {
		@Override
		protected BigDecimal doEvaluate(BigDecimal numberOfSecurities, Price price) {
			BigDecimal equityNotionalAmount = null;
			return assignOutput(equityNotionalAmount, numberOfSecurities, price);
		}
		
		protected BigDecimal assignOutput(BigDecimal equityNotionalAmount, BigDecimal numberOfSecurities, Price price) {
			equityNotionalAmount = MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(MapperS.of(numberOfSecurities), priceValue(numberOfSecurities, price)).get();
			
			return equityNotionalAmount;
		}
		
		@Override
		protected MapperS<BigDecimal> priceValue(BigDecimal numberOfSecurities, Price price) {
			return MapperS.of(price).<BigDecimal>map("getValue", measureBase -> measureBase.getValue());
		}
	}
}
