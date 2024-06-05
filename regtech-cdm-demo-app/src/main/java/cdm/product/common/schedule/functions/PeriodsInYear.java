package cdm.product.common.schedule.functions;

import cdm.base.datetime.CalculationPeriodFrequency;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;


@ImplementedBy(PeriodsInYear.PeriodsInYearDefault.class)
public abstract class PeriodsInYear implements RosettaFunction {

	/**
	* @param frequency 
	* @return numberOfPeriods 
	*/
	public Integer evaluate(CalculationPeriodFrequency frequency) {
		Integer numberOfPeriods = doEvaluate(frequency);
		
		return numberOfPeriods;
	}

	protected abstract Integer doEvaluate(CalculationPeriodFrequency frequency);

	public static class PeriodsInYearDefault extends PeriodsInYear {
		@Override
		protected Integer doEvaluate(CalculationPeriodFrequency frequency) {
			Integer numberOfPeriods = null;
			return assignOutput(numberOfPeriods, frequency);
		}
		
		protected Integer assignOutput(Integer numberOfPeriods, CalculationPeriodFrequency frequency) {
			return numberOfPeriods;
		}
	}
}
