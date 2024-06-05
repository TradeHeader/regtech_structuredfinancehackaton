package cdm.product.asset.functions;

import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;


@ImplementedBy(DividendCashSettlementAmount.DividendCashSettlementAmountDefault.class)
public abstract class DividendCashSettlementAmount implements RosettaFunction {

	/**
	* @param numberOfSecurities 
	* @param declaredDividend 
	* @return dividendCashSettlementAmount 
	*/
	public BigDecimal evaluate(BigDecimal numberOfSecurities, BigDecimal declaredDividend) {
		BigDecimal dividendCashSettlementAmount = doEvaluate(numberOfSecurities, declaredDividend);
		
		return dividendCashSettlementAmount;
	}

	protected abstract BigDecimal doEvaluate(BigDecimal numberOfSecurities, BigDecimal declaredDividend);

	public static class DividendCashSettlementAmountDefault extends DividendCashSettlementAmount {
		@Override
		protected BigDecimal doEvaluate(BigDecimal numberOfSecurities, BigDecimal declaredDividend) {
			BigDecimal dividendCashSettlementAmount = null;
			return assignOutput(dividendCashSettlementAmount, numberOfSecurities, declaredDividend);
		}
		
		protected BigDecimal assignOutput(BigDecimal dividendCashSettlementAmount, BigDecimal numberOfSecurities, BigDecimal declaredDividend) {
			dividendCashSettlementAmount = MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(MapperS.of(declaredDividend), MapperS.of(numberOfSecurities)).get();
			
			return dividendCashSettlementAmount;
		}
	}
}
