package cdm.observable.event.functions;

import cdm.base.math.AveragingCalculationMethodEnum;
import cdm.event.common.Reset;
import cdm.event.common.Reset.ResetBuilder;
import cdm.observable.event.Observation;
import cdm.product.template.AssetPayout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.records.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_AssetReset.Create_AssetResetDefault.class)
public abstract class Create_AssetReset implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected ResolveObservationAverage resolveObservationAverage;

	/**
	* @param assetPayout Represents the AssetPayout to which the reset will apply.
	* @param observation Represents the observations that will be used to compute the reset value.
	* @param resetDate Specifies the date of the reset.
	* @return reset 
	*/
	public Reset evaluate(AssetPayout assetPayout, List<? extends Observation> observation, Date resetDate) {
		Reset.ResetBuilder resetBuilder = doEvaluate(assetPayout, observation, resetDate);
		
		final Reset reset;
		if (resetBuilder == null) {
			reset = null;
		} else {
			reset = resetBuilder.build();
			objectValidator.validate(Reset.class, reset);
		}
		
		return reset;
	}

	protected abstract Reset.ResetBuilder doEvaluate(AssetPayout assetPayout, List<? extends Observation> observation, Date resetDate);

	public static class Create_AssetResetDefault extends Create_AssetReset {
		@Override
		protected Reset.ResetBuilder doEvaluate(AssetPayout assetPayout, List<? extends Observation> observation, Date resetDate) {
			if (observation == null) {
				observation = Collections.emptyList();
			}
			Reset.ResetBuilder reset = Reset.builder();
			return assignOutput(reset, assetPayout, observation, resetDate);
		}
		
		protected Reset.ResetBuilder assignOutput(Reset.ResetBuilder reset, AssetPayout assetPayout, List<? extends Observation> observation, Date resetDate) {
			reset
				.setResetValue(resolveObservationAverage.evaluate(observation));
			
			reset
				.setResetDate(resetDate);
			
			reset
				.addObservationsValue(observation);
			
			reset
				.getOrCreateAveragingMethodology()
				.getOrCreateAveragingMethod()
				.setCalculationMethod(AveragingCalculationMethodEnum.ARITHMETIC);
			
			return Optional.ofNullable(reset)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
