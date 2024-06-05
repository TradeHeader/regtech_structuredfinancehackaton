package cdm.product.asset.calculation.functions;

import cdm.base.datetime.AdjustableDate;
import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.CalculationPeriodFrequency;
import cdm.base.datetime.daycount.DayCountFractionEnum;
import cdm.base.datetime.daycount.functions.YearFraction;
import cdm.product.asset.InterestRatePayout;
import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.functions.PeriodsInYear;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import javax.inject.Inject;


@ImplementedBy(CalculateYearFraction.CalculateYearFractionDefault.class)
public abstract class CalculateYearFraction implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected PeriodsInYear periodsInYear0;
	@Inject protected YearFraction yearFraction;

	/**
	* @param interestRatePayout The interest rate payout for which the year fraction is needed
	* @param dcf The day count fraction convention to use
	* @param calculationPeriod The calculation period for which the year fraction is needed
	* @return yearFrac 
	*/
	public BigDecimal evaluate(InterestRatePayout interestRatePayout, DayCountFractionEnum dcf, CalculationPeriodBase calculationPeriod) {
		BigDecimal yearFrac = doEvaluate(interestRatePayout, dcf, calculationPeriod);
		
		return yearFrac;
	}

	protected abstract BigDecimal doEvaluate(InterestRatePayout interestRatePayout, DayCountFractionEnum dcf, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<Date> start(InterestRatePayout interestRatePayout, DayCountFractionEnum dcf, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<Date> end(InterestRatePayout interestRatePayout, DayCountFractionEnum dcf, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<Date> termination(InterestRatePayout interestRatePayout, DayCountFractionEnum dcf, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<Integer> periodsInYear1(InterestRatePayout interestRatePayout, DayCountFractionEnum dcf, CalculationPeriodBase calculationPeriod);

	public static class CalculateYearFractionDefault extends CalculateYearFraction {
		@Override
		protected BigDecimal doEvaluate(InterestRatePayout interestRatePayout, DayCountFractionEnum dcf, CalculationPeriodBase calculationPeriod) {
			BigDecimal yearFrac = null;
			return assignOutput(yearFrac, interestRatePayout, dcf, calculationPeriod);
		}
		
		protected BigDecimal assignOutput(BigDecimal yearFrac, InterestRatePayout interestRatePayout, DayCountFractionEnum dcf, CalculationPeriodBase calculationPeriod) {
			yearFrac = yearFraction.evaluate(dcf, start(interestRatePayout, dcf, calculationPeriod).get(), end(interestRatePayout, dcf, calculationPeriod).get(), termination(interestRatePayout, dcf, calculationPeriod).get(), periodsInYear1(interestRatePayout, dcf, calculationPeriod).get());
			
			return yearFrac;
		}
		
		@Override
		protected MapperS<Date> start(InterestRatePayout interestRatePayout, DayCountFractionEnum dcf, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(calculationPeriod).<Date>map("getAdjustedStartDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedStartDate());
		}
		
		@Override
		protected MapperS<Date> end(InterestRatePayout interestRatePayout, DayCountFractionEnum dcf, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(calculationPeriod).<Date>map("getAdjustedEndDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedEndDate());
		}
		
		@Override
		protected MapperS<Date> termination(InterestRatePayout interestRatePayout, DayCountFractionEnum dcf, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(interestRatePayout).<CalculationPeriodDates>map("getCalculationPeriodDates", _interestRatePayout -> _interestRatePayout.getCalculationPeriodDates()).<AdjustableOrRelativeDate>map("getTerminationDate", calculationPeriodDates -> calculationPeriodDates.getTerminationDate()).<AdjustableDate>map("getAdjustableDate", adjustableOrRelativeDate -> adjustableOrRelativeDate.getAdjustableDate()).<Date>map("getUnadjustedDate", adjustableDate -> adjustableDate.getUnadjustedDate());
		}
		
		@Override
		protected MapperS<Integer> periodsInYear1(InterestRatePayout interestRatePayout, DayCountFractionEnum dcf, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(periodsInYear0.evaluate(MapperS.of(interestRatePayout).<CalculationPeriodDates>map("getCalculationPeriodDates", _interestRatePayout -> _interestRatePayout.getCalculationPeriodDates()).<CalculationPeriodFrequency>map("getCalculationPeriodFrequency", calculationPeriodDates -> calculationPeriodDates.getCalculationPeriodFrequency()).get()));
		}
	}
}
