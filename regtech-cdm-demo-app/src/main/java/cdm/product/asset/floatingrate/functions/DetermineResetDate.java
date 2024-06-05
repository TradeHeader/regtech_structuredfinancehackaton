package cdm.product.asset.floatingrate.functions;

import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.ResetDates;
import cdm.product.common.schedule.ResetRelativeToEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(DetermineResetDate.DetermineResetDateDefault.class)
public abstract class DetermineResetDate implements RosettaFunction {

	/**
	* @param resetDates Reset dates for observing the rate.
	* @param calculationPeriod Calculation period for which you want the rate.
	* @return resetDate The date upon which the rate should be observed. .
	*/
	public Date evaluate(ResetDates resetDates, CalculationPeriodBase calculationPeriod) {
		Date resetDate = doEvaluate(resetDates, calculationPeriod);
		
		return resetDate;
	}

	protected abstract Date doEvaluate(ResetDates resetDates, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<ResetRelativeToEnum> resetRelativeTo(ResetDates resetDates, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<Boolean> isStart(ResetDates resetDates, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<Date> reset(ResetDates resetDates, CalculationPeriodBase calculationPeriod);

	public static class DetermineResetDateDefault extends DetermineResetDate {
		@Override
		protected Date doEvaluate(ResetDates resetDates, CalculationPeriodBase calculationPeriod) {
			Date resetDate = null;
			return assignOutput(resetDate, resetDates, calculationPeriod);
		}
		
		protected Date assignOutput(Date resetDate, ResetDates resetDates, CalculationPeriodBase calculationPeriod) {
			resetDate = reset(resetDates, calculationPeriod).get();
			
			return resetDate;
		}
		
		@Override
		protected MapperS<ResetRelativeToEnum> resetRelativeTo(ResetDates resetDates, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(resetDates).<ResetRelativeToEnum>map("getResetRelativeTo", _resetDates -> _resetDates.getResetRelativeTo());
		}
		
		@Override
		protected MapperS<Boolean> isStart(ResetDates resetDates, CalculationPeriodBase calculationPeriod) {
			return areEqual(resetRelativeTo(resetDates, calculationPeriod), MapperS.of(ResetRelativeToEnum.CALCULATION_PERIOD_START_DATE), CardinalityOperator.All).asMapper();
		}
		
		@Override
		protected MapperS<Date> reset(ResetDates resetDates, CalculationPeriodBase calculationPeriod) {
			if (isStart(resetDates, calculationPeriod).getOrDefault(false)) {
				return MapperS.of(calculationPeriod).<Date>map("getAdjustedStartDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedStartDate());
			}
			return MapperS.of(calculationPeriod).<Date>map("getAdjustedEndDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedEndDate());
		}
	}
}
