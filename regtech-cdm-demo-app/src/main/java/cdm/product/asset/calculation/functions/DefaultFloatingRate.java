package cdm.product.asset.calculation.functions;

import cdm.product.asset.floatingrate.FloatingRateProcessingDetails;
import cdm.product.asset.floatingrate.FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(DefaultFloatingRate.DefaultFloatingRateDefault.class)
public abstract class DefaultFloatingRate implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param suppliedRate 
	* @return processedRateDetails Results are details of the rate treatment.
	*/
	public FloatingRateProcessingDetails evaluate(BigDecimal suppliedRate) {
		FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder processedRateDetailsBuilder = doEvaluate(suppliedRate);
		
		final FloatingRateProcessingDetails processedRateDetails;
		if (processedRateDetailsBuilder == null) {
			processedRateDetails = null;
		} else {
			processedRateDetails = processedRateDetailsBuilder.build();
			objectValidator.validate(FloatingRateProcessingDetails.class, processedRateDetails);
		}
		
		return processedRateDetails;
	}

	protected abstract FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder doEvaluate(BigDecimal suppliedRate);

	public static class DefaultFloatingRateDefault extends DefaultFloatingRate {
		@Override
		protected FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder doEvaluate(BigDecimal suppliedRate) {
			FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder processedRateDetails = FloatingRateProcessingDetails.builder();
			return assignOutput(processedRateDetails, suppliedRate);
		}
		
		protected FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder assignOutput(FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder processedRateDetails, BigDecimal suppliedRate) {
			processedRateDetails
				.setProcessedRate(suppliedRate);
			
			return Optional.ofNullable(processedRateDetails)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
