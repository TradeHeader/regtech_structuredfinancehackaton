package cdm.product.common.schedule.functions;

import cdm.base.datetime.BusinessDayAdjustments;
import cdm.product.common.schedule.CalculationPeriodData;
import cdm.product.common.schedule.CalculationPeriodData.CalculationPeriodDataBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(CalculationPeriodRange.CalculationPeriodRangeDefault.class)
public abstract class CalculationPeriodRange implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param startDate 
	* @param endDate 
	* @param dateAdjustments 
	* @return result 
	*/
	public CalculationPeriodData evaluate(Date startDate, Date endDate, BusinessDayAdjustments dateAdjustments) {
		CalculationPeriodData.CalculationPeriodDataBuilder resultBuilder = doEvaluate(startDate, endDate, dateAdjustments);
		
		final CalculationPeriodData result;
		if (resultBuilder == null) {
			result = null;
		} else {
			result = resultBuilder.build();
			objectValidator.validate(CalculationPeriodData.class, result);
		}
		
		return result;
	}

	protected abstract CalculationPeriodData.CalculationPeriodDataBuilder doEvaluate(Date startDate, Date endDate, BusinessDayAdjustments dateAdjustments);

	public static class CalculationPeriodRangeDefault extends CalculationPeriodRange {
		@Override
		protected CalculationPeriodData.CalculationPeriodDataBuilder doEvaluate(Date startDate, Date endDate, BusinessDayAdjustments dateAdjustments) {
			CalculationPeriodData.CalculationPeriodDataBuilder result = CalculationPeriodData.builder();
			return assignOutput(result, startDate, endDate, dateAdjustments);
		}
		
		protected CalculationPeriodData.CalculationPeriodDataBuilder assignOutput(CalculationPeriodData.CalculationPeriodDataBuilder result, Date startDate, Date endDate, BusinessDayAdjustments dateAdjustments) {
			return Optional.ofNullable(result)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
