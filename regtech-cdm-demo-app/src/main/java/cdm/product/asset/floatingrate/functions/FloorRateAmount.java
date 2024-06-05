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


@ImplementedBy(FloorRateAmount.FloorRateAmountDefault.class)
public abstract class FloorRateAmount implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected GetRateScheduleAmount getRateScheduleAmount;

	/**
	* @param interestRatePayout An interest rate stream.
	* @param calculationPeriod The calculation period for which you want the floor rate.
	* @return floorRate The cap rate in effect from the calculation period start date.
	*/
	public BigDecimal evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
		BigDecimal floorRate = doEvaluate(interestRatePayout, calculationPeriod);
		
		return floorRate;
	}

	protected abstract BigDecimal doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod);

	public static class FloorRateAmountDefault extends FloorRateAmount {
		@Override
		protected BigDecimal doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			BigDecimal floorRate = null;
			return assignOutput(floorRate, interestRatePayout, calculationPeriod);
		}
		
		protected BigDecimal assignOutput(BigDecimal floorRate, InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			floorRate = getRateScheduleAmount.evaluate(MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate()).<StrikeSchedule>map("getFloorRateSchedule", floatingRateBase -> floatingRateBase.getFloorRateSchedule()).get(), MapperS.of(calculationPeriod).<Date>map("getAdjustedStartDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedStartDate()).get());
			
			return floorRate;
		}
	}
}
