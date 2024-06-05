package cdm.product.asset.floatingrate.functions;

import cdm.product.asset.FloatingRateSpecification;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.RateSpecification;
import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.RateSchedule;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import javax.inject.Inject;


@ImplementedBy(MultiplierAmount.MultiplierAmountDefault.class)
public abstract class MultiplierAmount implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected GetRateScheduleAmount getRateScheduleAmount;

	/**
	* @param interestRatePayout An interest rate stream.
	* @param calculationPeriod The calculation period for which you want the multiplier.
	* @return multiplier The multiplier in effect from the calculation period start date.
	*/
	public BigDecimal evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
		BigDecimal multiplier = doEvaluate(interestRatePayout, calculationPeriod);
		
		return multiplier;
	}

	protected abstract BigDecimal doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod);

	public static class MultiplierAmountDefault extends MultiplierAmount {
		@Override
		protected BigDecimal doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			BigDecimal multiplier = null;
			return assignOutput(multiplier, interestRatePayout, calculationPeriod);
		}
		
		protected BigDecimal assignOutput(BigDecimal multiplier, InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			multiplier = getRateScheduleAmount.evaluate(MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate()).<RateSchedule>map("getFloatingRateMultiplierSchedule", floatingRate -> floatingRate.getFloatingRateMultiplierSchedule()).get(), MapperS.of(calculationPeriod).<Date>map("getAdjustedStartDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedStartDate()).get());
			
			return multiplier;
		}
	}
}
