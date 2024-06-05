package cdm.product.asset.calculation.functions;

import cdm.product.asset.FixedRateSpecification;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.RateSpecification;
import cdm.product.asset.floatingrate.functions.GetRateScheduleAmount;
import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.RateSchedule;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import javax.inject.Inject;


@ImplementedBy(GetFixedRate.GetFixedRateDefault.class)
public abstract class GetFixedRate implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected GetRateScheduleAmount getRateScheduleAmount;

	/**
	* @param interestRatePayout An interest rate stream.
	* @param calculationPeriod The calculation period for which you want the spread.
	* @return fixedRate the fixed rate value value for the period.
	*/
	public BigDecimal evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
		BigDecimal fixedRate = doEvaluate(interestRatePayout, calculationPeriod);
		
		return fixedRate;
	}

	protected abstract BigDecimal doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod);

	public static class GetFixedRateDefault extends GetFixedRate {
		@Override
		protected BigDecimal doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			BigDecimal fixedRate = null;
			return assignOutput(fixedRate, interestRatePayout, calculationPeriod);
		}
		
		protected BigDecimal assignOutput(BigDecimal fixedRate, InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			fixedRate = getRateScheduleAmount.evaluate(MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FixedRateSpecification>map("getFixedRate", rateSpecification -> rateSpecification.getFixedRate()).<RateSchedule>map("getRateSchedule", fixedRateSpecification -> fixedRateSpecification.getRateSchedule()).get(), MapperS.of(calculationPeriod).<Date>map("getAdjustedStartDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedStartDate()).get());
			
			return fixedRate;
		}
	}
}
