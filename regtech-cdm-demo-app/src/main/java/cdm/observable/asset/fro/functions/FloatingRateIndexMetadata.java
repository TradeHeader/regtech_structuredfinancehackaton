package cdm.observable.asset.fro.functions;

import cdm.base.staticdata.asset.rates.FloatingRateIndexEnum;
import cdm.observable.asset.fro.FloatingRateIndexDefinition;
import cdm.observable.asset.fro.FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(FloatingRateIndexMetadata.FloatingRateIndexMetadataDefault.class)
public abstract class FloatingRateIndexMetadata implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param floatingRateIndexName The name of the floating rate index.
	* @return floatingRateIndexDescription The detailed description of the index if available, null otherwise.
	*/
	public FloatingRateIndexDefinition evaluate(FloatingRateIndexEnum floatingRateIndexName) {
		FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder floatingRateIndexDescriptionBuilder = doEvaluate(floatingRateIndexName);
		
		final FloatingRateIndexDefinition floatingRateIndexDescription;
		if (floatingRateIndexDescriptionBuilder == null) {
			floatingRateIndexDescription = null;
		} else {
			floatingRateIndexDescription = floatingRateIndexDescriptionBuilder.build();
			objectValidator.validate(FloatingRateIndexDefinition.class, floatingRateIndexDescription);
		}
		
		return floatingRateIndexDescription;
	}

	protected abstract FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder doEvaluate(FloatingRateIndexEnum floatingRateIndexName);

	public static class FloatingRateIndexMetadataDefault extends FloatingRateIndexMetadata {
		@Override
		protected FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder doEvaluate(FloatingRateIndexEnum floatingRateIndexName) {
			FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder floatingRateIndexDescription = FloatingRateIndexDefinition.builder();
			return assignOutput(floatingRateIndexDescription, floatingRateIndexName);
		}
		
		protected FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder assignOutput(FloatingRateIndexDefinition.FloatingRateIndexDefinitionBuilder floatingRateIndexDescription, FloatingRateIndexEnum floatingRateIndexName) {
			return Optional.ofNullable(floatingRateIndexDescription)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
