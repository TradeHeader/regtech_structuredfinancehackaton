package cdm.product.asset.floatingrate.functions;

import cdm.base.math.Rounding;
import cdm.product.asset.floatingrate.FloatingRateProcessingParameters;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import javax.inject.Inject;


@ImplementedBy(ApplyFloatingRatePostSpreadProcessing.ApplyFloatingRatePostSpreadProcessingDefault.class)
public abstract class ApplyFloatingRatePostSpreadProcessing implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected ApplyCapsAndFloors applyCapsAndFloors;
	@Inject protected ApplyFinalRateRounding applyFinalRateRounding;

	/**
	* @param inputRate The floating rate prior to post-sprad, either a single term rate, or a calculated rate such as an OIS or lookback compounded rate.
	* @param processing 
	* @return processedRate rate after post-spread processing.
	*/
	public BigDecimal evaluate(BigDecimal inputRate, FloatingRateProcessingParameters processing) {
		BigDecimal processedRate = doEvaluate(inputRate, processing);
		
		return processedRate;
	}

	protected abstract BigDecimal doEvaluate(BigDecimal inputRate, FloatingRateProcessingParameters processing);

	protected abstract MapperS<BigDecimal> cappedAndFlooredRate(BigDecimal inputRate, FloatingRateProcessingParameters processing);

	public static class ApplyFloatingRatePostSpreadProcessingDefault extends ApplyFloatingRatePostSpreadProcessing {
		@Override
		protected BigDecimal doEvaluate(BigDecimal inputRate, FloatingRateProcessingParameters processing) {
			BigDecimal processedRate = null;
			return assignOutput(processedRate, inputRate, processing);
		}
		
		protected BigDecimal assignOutput(BigDecimal processedRate, BigDecimal inputRate, FloatingRateProcessingParameters processing) {
			processedRate = applyFinalRateRounding.evaluate(cappedAndFlooredRate(inputRate, processing).get(), MapperS.of(processing).<Rounding>map("getRounding", floatingRateProcessingParameters -> floatingRateProcessingParameters.getRounding()).get());
			
			return processedRate;
		}
		
		@Override
		protected MapperS<BigDecimal> cappedAndFlooredRate(BigDecimal inputRate, FloatingRateProcessingParameters processing) {
			return MapperS.of(applyCapsAndFloors.evaluate(processing, inputRate));
		}
	}
}
