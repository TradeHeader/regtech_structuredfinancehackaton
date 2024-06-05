package cdm.product.asset.floatingrate.functions;

import cdm.product.asset.FloatingRateSpecification;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.RateSpecification;
import cdm.product.asset.SpreadSchedule;
import cdm.product.common.schedule.CalculationPeriodBase;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import javax.inject.Inject;


@ImplementedBy(SpreadAmount.SpreadAmountDefault.class)
public abstract class SpreadAmount implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected GetRateScheduleAmount getRateScheduleAmount;

	/**
	* @param interestRatePayout An interest rate stream.
	* @param calculationPeriod The calculation period for which you want the spread.
	* @return spread The spread value for the period.
	*/
	public BigDecimal evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
		BigDecimal spread = doEvaluate(interestRatePayout, calculationPeriod);
		
		return spread;
	}

	protected abstract BigDecimal doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod);

	public static class SpreadAmountDefault extends SpreadAmount {
		@Override
		protected BigDecimal doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			BigDecimal spread = null;
			return assignOutput(spread, interestRatePayout, calculationPeriod);
		}
		
		protected BigDecimal assignOutput(BigDecimal spread, InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			spread = getRateScheduleAmount.evaluate(MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate()).<SpreadSchedule>map("getSpreadSchedule", floatingRateBase -> floatingRateBase.getSpreadSchedule()).get(), MapperS.of(calculationPeriod).<Date>map("getAdjustedStartDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedStartDate()).get());
			
			return spread;
		}
	}
}
