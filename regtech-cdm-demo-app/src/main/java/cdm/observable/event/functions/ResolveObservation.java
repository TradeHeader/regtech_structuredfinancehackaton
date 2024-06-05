package cdm.observable.event.functions;

import cdm.base.math.AveragingCalculationMethod;
import cdm.observable.event.Observation;
import cdm.observable.event.Observation.ObservationBuilder;
import cdm.observable.event.ObservationIdentifier;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(ResolveObservation.ResolveObservationDefault.class)
public abstract class ResolveObservation implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param identifiers 
	* @param averagingMethod 
	* @return observation 
	*/
	public Observation evaluate(List<? extends ObservationIdentifier> identifiers, AveragingCalculationMethod averagingMethod) {
		Observation.ObservationBuilder observationBuilder = doEvaluate(identifiers, averagingMethod);
		
		final Observation observation;
		if (observationBuilder == null) {
			observation = null;
		} else {
			observation = observationBuilder.build();
			objectValidator.validate(Observation.class, observation);
		}
		
		return observation;
	}

	protected abstract Observation.ObservationBuilder doEvaluate(List<? extends ObservationIdentifier> identifiers, AveragingCalculationMethod averagingMethod);

	public static class ResolveObservationDefault extends ResolveObservation {
		@Override
		protected Observation.ObservationBuilder doEvaluate(List<? extends ObservationIdentifier> identifiers, AveragingCalculationMethod averagingMethod) {
			if (identifiers == null) {
				identifiers = Collections.emptyList();
			}
			Observation.ObservationBuilder observation = Observation.builder();
			return assignOutput(observation, identifiers, averagingMethod);
		}
		
		protected Observation.ObservationBuilder assignOutput(Observation.ObservationBuilder observation, List<? extends ObservationIdentifier> identifiers, AveragingCalculationMethod averagingMethod) {
			return Optional.ofNullable(observation)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
