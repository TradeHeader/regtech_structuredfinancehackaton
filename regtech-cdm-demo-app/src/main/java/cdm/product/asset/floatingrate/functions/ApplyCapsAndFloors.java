package cdm.product.asset.floatingrate.functions;

import cdm.product.asset.floatingrate.FloatingRateProcessingParameters;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ApplyCapsAndFloors.ApplyCapsAndFloorsDefault.class)
public abstract class ApplyCapsAndFloors implements RosettaFunction {

	/**
	* @param processing 
	* @param inputRate The floating rate prior to treatment, either a single term rate, or a calculated rate such as an OIS or lookback compounded rate.
	* @return cappedAndFlooredRate The rate after application of cap and/or floor.
	*/
	public BigDecimal evaluate(FloatingRateProcessingParameters processing, BigDecimal inputRate) {
		BigDecimal cappedAndFlooredRate = doEvaluate(processing, inputRate);
		
		return cappedAndFlooredRate;
	}

	protected abstract BigDecimal doEvaluate(FloatingRateProcessingParameters processing, BigDecimal inputRate);

	protected abstract MapperS<BigDecimal> cap(FloatingRateProcessingParameters processing, BigDecimal inputRate);

	protected abstract MapperS<BigDecimal> floor(FloatingRateProcessingParameters processing, BigDecimal inputRate);

	protected abstract MapperS<BigDecimal> cappedRate(FloatingRateProcessingParameters processing, BigDecimal inputRate);

	protected abstract MapperS<BigDecimal> flooredRate(FloatingRateProcessingParameters processing, BigDecimal inputRate);

	public static class ApplyCapsAndFloorsDefault extends ApplyCapsAndFloors {
		@Override
		protected BigDecimal doEvaluate(FloatingRateProcessingParameters processing, BigDecimal inputRate) {
			BigDecimal cappedAndFlooredRate = null;
			return assignOutput(cappedAndFlooredRate, processing, inputRate);
		}
		
		protected BigDecimal assignOutput(BigDecimal cappedAndFlooredRate, FloatingRateProcessingParameters processing, BigDecimal inputRate) {
			cappedAndFlooredRate = flooredRate(processing, inputRate).get();
			
			return cappedAndFlooredRate;
		}
		
		@Override
		protected MapperS<BigDecimal> cap(FloatingRateProcessingParameters processing, BigDecimal inputRate) {
			return MapperS.of(processing).<BigDecimal>map("getCapRate", floatingRateProcessingParameters -> floatingRateProcessingParameters.getCapRate());
		}
		
		@Override
		protected MapperS<BigDecimal> floor(FloatingRateProcessingParameters processing, BigDecimal inputRate) {
			return MapperS.of(processing).<BigDecimal>map("getFloorRate", floatingRateProcessingParameters -> floatingRateProcessingParameters.getFloorRate());
		}
		
		@Override
		protected MapperS<BigDecimal> cappedRate(FloatingRateProcessingParameters processing, BigDecimal inputRate) {
			if (exists(cap(processing, inputRate)).and(greaterThan(MapperS.of(inputRate), cap(processing, inputRate), CardinalityOperator.All)).getOrDefault(false)) {
				return cap(processing, inputRate);
			}
			return MapperS.of(inputRate);
		}
		
		@Override
		protected MapperS<BigDecimal> flooredRate(FloatingRateProcessingParameters processing, BigDecimal inputRate) {
			if (exists(floor(processing, inputRate)).and(lessThan(cappedRate(processing, inputRate), floor(processing, inputRate), CardinalityOperator.All)).getOrDefault(false)) {
				return floor(processing, inputRate);
			}
			return cappedRate(processing, inputRate);
		}
	}
}
