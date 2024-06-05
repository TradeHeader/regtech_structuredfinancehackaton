package cdm.product.common.schedule.functions;

import cdm.product.common.schedule.CalculationPeriodData;
import cdm.product.common.schedule.CalculationPeriodData.CalculationPeriodDataBuilder;
import cdm.product.common.schedule.CalculationPeriodDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;


@ImplementedBy(CalculationPeriods.CalculationPeriodsDefault.class)
public abstract class CalculationPeriods implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param calculationPeriodDates 
	* @return result 
	*/
	public List<? extends CalculationPeriodData> evaluate(CalculationPeriodDates calculationPeriodDates) {
		List<CalculationPeriodData.CalculationPeriodDataBuilder> resultBuilder = doEvaluate(calculationPeriodDates);
		
		final List<? extends CalculationPeriodData> result;
		if (resultBuilder == null) {
			result = null;
		} else {
			result = resultBuilder.stream().map(CalculationPeriodData::build).collect(Collectors.toList());
			objectValidator.validate(CalculationPeriodData.class, result);
		}
		
		return result;
	}

	protected abstract List<CalculationPeriodData.CalculationPeriodDataBuilder> doEvaluate(CalculationPeriodDates calculationPeriodDates);

	public static class CalculationPeriodsDefault extends CalculationPeriods {
		@Override
		protected List<CalculationPeriodData.CalculationPeriodDataBuilder> doEvaluate(CalculationPeriodDates calculationPeriodDates) {
			List<CalculationPeriodData.CalculationPeriodDataBuilder> result = new ArrayList<>();
			return assignOutput(result, calculationPeriodDates);
		}
		
		protected List<CalculationPeriodData.CalculationPeriodDataBuilder> assignOutput(List<CalculationPeriodData.CalculationPeriodDataBuilder> result, CalculationPeriodDates calculationPeriodDates) {
			return Optional.ofNullable(result)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
