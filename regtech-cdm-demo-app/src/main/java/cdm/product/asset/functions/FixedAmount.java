package cdm.product.asset.functions;

import cdm.product.asset.FixedAmountCalculationDetails;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.calculation.functions.Create_CalculationPeriodBase;
import cdm.product.asset.calculation.functions.FixedAmountCalculation;
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

@ImplementedBy(FixedAmount.FixedAmountDefault.class)
public abstract class FixedAmount implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected CalculationPeriod calculationPeriod0;
	@Inject protected Create_CalculationPeriodBase create_CalculationPeriodBase;
	@Inject protected FixedAmountCalculation fixedAmountCalculation;

	/**
	* @param interestRatePayout description of the interest rate payout
	* @param notional The notional quantity to use
	* @param date The date to use to obtain the calculation period
	* @param calculationPeriodData full details of the calculation period
	* @return fixedAmount 
	*/
	public BigDecimal evaluate(InterestRatePayout interestRatePayout, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData) {
		BigDecimal fixedAmount = doEvaluate(interestRatePayout, notional, date, calculationPeriodData);
		
		return fixedAmount;
	}

	protected abstract BigDecimal doEvaluate(InterestRatePayout interestRatePayout, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData);

	protected abstract MapperS<? extends CalculationPeriodData> calculationPeriod1(InterestRatePayout interestRatePayout, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData);

	protected abstract MapperS<? extends CalculationPeriodBase> calcPeriodBase(InterestRatePayout interestRatePayout, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData);

	protected abstract MapperS<? extends FixedAmountCalculationDetails> fixedAmountCalc(InterestRatePayout interestRatePayout, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData);

	public static class FixedAmountDefault extends FixedAmount {
		@Override
		protected BigDecimal doEvaluate(InterestRatePayout interestRatePayout, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData) {
			BigDecimal fixedAmount = null;
			return assignOutput(fixedAmount, interestRatePayout, notional, date, calculationPeriodData);
		}
		
		protected BigDecimal assignOutput(BigDecimal fixedAmount, InterestRatePayout interestRatePayout, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData) {
			fixedAmount = fixedAmountCalc(interestRatePayout, notional, date, calculationPeriodData).<BigDecimal>map("getCalculatedAmount", fixedAmountCalculationDetails -> fixedAmountCalculationDetails.getCalculatedAmount()).get();
			
			return fixedAmount;
		}
		
		@Override
		protected MapperS<? extends CalculationPeriodData> calculationPeriod1(InterestRatePayout interestRatePayout, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData) {
			if (exists(MapperS.of(calculationPeriodData)).getOrDefault(false)) {
				return MapperS.of(calculationPeriodData);
			}
			return MapperS.of(calculationPeriod0.evaluate(MapperS.of(interestRatePayout).<CalculationPeriodDates>map("getCalculationPeriodDates", _interestRatePayout -> _interestRatePayout.getCalculationPeriodDates()).get(), date));
		}
		
		@Override
		protected MapperS<? extends CalculationPeriodBase> calcPeriodBase(InterestRatePayout interestRatePayout, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData) {
			return MapperS.of(create_CalculationPeriodBase.evaluate(calculationPeriod1(interestRatePayout, notional, date, calculationPeriodData).get()));
		}
		
		@Override
		protected MapperS<? extends FixedAmountCalculationDetails> fixedAmountCalc(InterestRatePayout interestRatePayout, BigDecimal notional, Date date, CalculationPeriodData calculationPeriodData) {
			return MapperS.of(fixedAmountCalculation.evaluate(interestRatePayout, calcPeriodBase(interestRatePayout, notional, date, calculationPeriodData).get(), notional));
		}
	}
}
