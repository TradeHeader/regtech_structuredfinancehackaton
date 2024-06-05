package cdm.product.asset.floatingrate.functions;

import cdm.product.asset.FloatingRateSpecification;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.RateSpecification;
import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.template.StrikeSchedule;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import javax.inject.Inject;


@ImplementedBy(CapRateAmount.CapRateAmountDefault.class)
public abstract class CapRateAmount implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected GetRateScheduleAmount getRateScheduleAmount;

	/**
	* @param interestRatePayout An interest rate stream.
	* @param calculationPeriod The calculation period for which you want the cap rate.
	* @return capRate The cap rate in effect from the calculation period start date.
	*/
	public BigDecimal evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
		BigDecimal capRate = doEvaluate(interestRatePayout, calculationPeriod);
		
		return capRate;
	}

	protected abstract BigDecimal doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod);

	public static class CapRateAmountDefault extends CapRateAmount {
		@Override
		protected BigDecimal doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			BigDecimal capRate = null;
			return assignOutput(capRate, interestRatePayout, calculationPeriod);
		}
		
		protected BigDecimal assignOutput(BigDecimal capRate, InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			capRate = getRateScheduleAmount.evaluate(MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate()).<StrikeSchedule>map("getCapRateSchedule", floatingRateBase -> floatingRateBase.getCapRateSchedule()).get(), MapperS.of(calculationPeriod).<Date>map("getAdjustedStartDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedStartDate()).get());
			
			return capRate;
		}
	}
}
