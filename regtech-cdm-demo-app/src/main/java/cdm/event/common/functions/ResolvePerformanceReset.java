package cdm.event.common.functions;

import cdm.event.common.Reset;
import cdm.event.common.Reset.ResetBuilder;
import cdm.observable.asset.Price;
import cdm.observable.event.Observation;
import cdm.product.template.PerformancePayout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(ResolvePerformanceReset.ResolvePerformanceResetDefault.class)
public abstract class ResolvePerformanceReset implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param performancePayout Represents the PerformancePayout to which the reset will apply.
	* @param observation Represents the observation that will be used to compute the reset value.
	* @param date Specifies the date of the reset.
	* @return reset 
	*/
	public Reset evaluate(PerformancePayout performancePayout, Observation observation, Date date) {
		Reset.ResetBuilder resetBuilder = doEvaluate(performancePayout, observation, date);
		
		final Reset reset;
		if (resetBuilder == null) {
			reset = null;
		} else {
			reset = resetBuilder.build();
			objectValidator.validate(Reset.class, reset);
		}
		
		return reset;
	}

	protected abstract Reset.ResetBuilder doEvaluate(PerformancePayout performancePayout, Observation observation, Date date);

	public static class ResolvePerformanceResetDefault extends ResolvePerformanceReset {
		@Override
		protected Reset.ResetBuilder doEvaluate(PerformancePayout performancePayout, Observation observation, Date date) {
			Reset.ResetBuilder reset = Reset.builder();
			return assignOutput(reset, performancePayout, observation, date);
		}
		
		protected Reset.ResetBuilder assignOutput(Reset.ResetBuilder reset, PerformancePayout performancePayout, Observation observation, Date date) {
			reset
				.setResetValue(MapperS.of(observation).<Price>map("getObservedValue", _observation -> _observation.getObservedValue()).get());
			
			reset
				.setResetDate(date);
			
			reset
				.addObservationsValue((observation == null ? Collections.<Observation>emptyList() : Collections.singletonList(observation)));
			
			return Optional.ofNullable(reset)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
