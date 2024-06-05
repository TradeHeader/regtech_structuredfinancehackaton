package cdm.product.asset.functions;

import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.calculation.functions.Create_CalculationPeriodBase;
import cdm.product.asset.calculation.functions.FloatingAmountCalculation;
import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails;
import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.CalculationPeriodData;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.functions.CalculationPeriod;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(FloatingAmount.FloatingAmountDefault.class)
public abstract class FloatingAmount implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected CalculationPeriod calculationPeriod0;
	@Inject protected Create_CalculationPeriodBase create_CalculationPeriodBase;
	@Inject protected FloatingAmountCalculation floatingAmountCalculation;

	/**
	* @param interestRatePayout full description of the interest rate payout
	* @param rate the floating rate to use; if omitted it is retrieved/calculated based on the interest rate payout and floating index 
	* @param notional the notional; if omitted it is obtained from the payout
	* @param date The date to use to obtain the calculation period
	* @param calculationPeriodData full details of the calculation period
	* @return floatingAmount 
	*/
	public BigDecimal evaluate(InterestRatePayout interestRatePayout, BigDecimal rate, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData) {
		BigDecimal floatingAmount = doEvaluate(interestRatePayout, rate, notional, date, calculationPeriodData);
		
		return floatingAmount;
	}

	protected abstract BigDecimal doEvaluate(InterestRatePayout interestRatePayout, BigDecimal rate, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData);

	protected abstract MapperS<? extends CalculationPeriodData> calculationPeriod1(InterestRatePayout interestRatePayout, BigDecimal rate, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData);

	protected abstract MapperS<? extends CalculationPeriodBase> calcPeriodBase(InterestRatePayout interestRatePayout, BigDecimal rate, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData);

	protected abstract MapperS<? extends FloatingAmountCalculationDetails> floatingCalc(InterestRatePayout interestRatePayout, BigDecimal rate, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData);

	public static class FloatingAmountDefault extends FloatingAmount {
		@Override
		protected BigDecimal doEvaluate(InterestRatePayout interestRatePayout, BigDecimal rate, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData) {
			BigDecimal floatingAmount = null;
			return assignOutput(floatingAmount, interestRatePayout, rate, notional, date, calculationPeriodData);
		}
		
		protected BigDecimal assignOutput(BigDecimal floatingAmount, InterestRatePayout interestRatePayout, BigDecimal rate, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData) {
			floatingAmount = floatingCalc(interestRatePayout, rate, notional, date, calculationPeriodData).<BigDecimal>map("getCalculatedAmount", floatingAmountCalculationDetails -> floatingAmountCalculationDetails.getCalculatedAmount()).get();
			
			return floatingAmount;
		}
		
		@Override
		protected MapperS<? extends CalculationPeriodData> calculationPeriod1(InterestRatePayout interestRatePayout, BigDecimal rate, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData) {
			if (exists(MapperS.of(calculationPeriodData)).getOrDefault(false)) {
				return MapperS.of(calculationPeriodData);
			}
			return MapperS.of(calculationPeriod0.evaluate(MapperS.of(interestRatePayout).<CalculationPeriodDates>map("getCalculationPeriodDates", _interestRatePayout -> _interestRatePayout.getCalculationPeriodDates()).get(), date));
		}
		
		@Override
		protected MapperS<? extends CalculationPeriodBase> calcPeriodBase(InterestRatePayout interestRatePayout, BigDecimal rate, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData) {
			return MapperS.of(create_CalculationPeriodBase.evaluate(calculationPeriod1(interestRatePayout, rate, notional, date, calculationPeriodData).get()));
		}
		
		@Override
		protected MapperS<? extends FloatingAmountCalculationDetails> floatingCalc(InterestRatePayout interestRatePayout, BigDecimal rate, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData) {
			return MapperS.of(floatingAmountCalculation.evaluate(interestRatePayout, calcPeriodBase(interestRatePayout, rate, notional, date, calculationPeriodData).get(), false, notional, rate));
		}
	}
}
