package cdm.product.common.schedule.functions;

import cdm.product.common.schedule.CalculationPeriodData;
import cdm.product.common.schedule.CalculationPeriodData.CalculationPeriodDataBuilder;
import cdm.product.common.schedule.CalculationPeriodDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(CalculationPeriod.CalculationPeriodDefault.class)
public abstract class CalculationPeriod implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param calculationPeriodDates 
	* @param date 
	* @return result 
	*/
	public CalculationPeriodData evaluate(CalculationPeriodDates calculationPeriodDates, Date date) {
		CalculationPeriodData.CalculationPeriodDataBuilder resultBuilder = doEvaluate(calculationPeriodDates, date);
		
		final CalculationPeriodData result;
		if (resultBuilder == null) {
			result = null;
		} else {
			result = resultBuilder.build();
			objectValidator.validate(CalculationPeriodData.class, result);
		}
		
		return result;
	}

	protected abstract CalculationPeriodData.CalculationPeriodDataBuilder doEvaluate(CalculationPeriodDates calculationPeriodDates, Date date);

	public static class CalculationPeriodDefault extends CalculationPeriod {
		@Override
		protected CalculationPeriodData.CalculationPeriodDataBuilder doEvaluate(CalculationPeriodDates calculationPeriodDates, Date date) {
			CalculationPeriodData.CalculationPeriodDataBuilder result = CalculationPeriodData.builder();
			return assignOutput(result, calculationPeriodDates, date);
		}
		
		protected CalculationPeriodData.CalculationPeriodDataBuilder assignOutput(CalculationPeriodData.CalculationPeriodDataBuilder result, CalculationPeriodDates calculationPeriodDates, Date date) {
			return Optional.ofNullable(result)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
