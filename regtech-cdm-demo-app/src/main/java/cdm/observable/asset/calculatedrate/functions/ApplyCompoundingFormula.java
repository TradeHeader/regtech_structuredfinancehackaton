package cdm.observable.asset.calculatedrate.functions;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.base.math.functions.VectorGrowthOperation;
import cdm.base.math.functions.VectorOperation;
import cdm.base.math.functions.VectorScalarOperation;
import cdm.observable.asset.calculatedrate.CalculatedRateDetails;
import cdm.observable.asset.calculatedrate.CalculatedRateDetails.CalculatedRateDetailsBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(ApplyCompoundingFormula.ApplyCompoundingFormulaDefault.class)
public abstract class ApplyCompoundingFormula implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected VectorGrowthOperation vectorGrowthOperation;
	@Inject protected VectorOperation vectorOperation;
	@Inject protected VectorScalarOperation vectorScalarOperation;

	/**
	* @param observations A vector of observation value.
	* @param weights A vector of weights (should be same size as observations, 1 weight per observation.
	* @param yearFrac Year fraction of a single day (i.e. 1/basis.
	* @return results Details of the compounding calculation.
	*/
	public CalculatedRateDetails evaluate(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac) {
		CalculatedRateDetails.CalculatedRateDetailsBuilder resultsBuilder = doEvaluate(observations, weights, yearFrac);
		
		final CalculatedRateDetails results;
		if (resultsBuilder == null) {
			results = null;
		} else {
			results = resultsBuilder.build();
			objectValidator.validate(CalculatedRateDetails.class, results);
		}
		
		return results;
	}

	protected abstract CalculatedRateDetails.CalculatedRateDetailsBuilder doEvaluate(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac);

	protected abstract MapperC<BigDecimal> weightedObservations(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac);

	protected abstract MapperC<BigDecimal> scaledAndWeightedObservations(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac);

	protected abstract MapperC<BigDecimal> growthFactors(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac);

	protected abstract MapperC<BigDecimal> growthCurve(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac);

	protected abstract MapperS<BigDecimal> finalValue(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac);

	protected abstract MapperS<BigDecimal> totalWeight(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac);

	protected abstract MapperS<BigDecimal> overallYearFrac(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac);

	protected abstract MapperS<BigDecimal> calculatedRate(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac);

	public static class ApplyCompoundingFormulaDefault extends ApplyCompoundingFormula {
		@Override
		protected CalculatedRateDetails.CalculatedRateDetailsBuilder doEvaluate(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac) {
			if (observations == null) {
				observations = Collections.emptyList();
			}
			if (weights == null) {
				weights = Collections.emptyList();
			}
			CalculatedRateDetails.CalculatedRateDetailsBuilder results = CalculatedRateDetails.builder();
			return assignOutput(results, observations, weights, yearFrac);
		}
		
		protected CalculatedRateDetails.CalculatedRateDetailsBuilder assignOutput(CalculatedRateDetails.CalculatedRateDetailsBuilder results, List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac) {
			results
				.setAggregateValue(finalValue(observations, weights, yearFrac).get());
			
			results
				.setAggregateWeight(totalWeight(observations, weights, yearFrac).get());
			
			results
				.setCalculatedRate(calculatedRate(observations, weights, yearFrac).get());
			
			results
				.addCompoundedGrowth(growthCurve(observations, weights, yearFrac).getMulti());
			
			results
				.addGrowthFactor(growthFactors(observations, weights, yearFrac).getMulti());
			
			results
				.addWeightedRates(weightedObservations(observations, weights, yearFrac).getMulti());
			
			return Optional.ofNullable(results)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperC<BigDecimal> weightedObservations(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac) {
			return MapperC.<BigDecimal>of(vectorOperation.evaluate(ArithmeticOperationEnum.MULTIPLY, observations, weights));
		}
		
		@Override
		protected MapperC<BigDecimal> scaledAndWeightedObservations(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac) {
			return MapperC.<BigDecimal>of(vectorScalarOperation.evaluate(ArithmeticOperationEnum.MULTIPLY, weightedObservations(observations, weights, yearFrac).getMulti(), yearFrac));
		}
		
		@Override
		protected MapperC<BigDecimal> growthFactors(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac) {
			return MapperC.<BigDecimal>of(vectorScalarOperation.evaluate(ArithmeticOperationEnum.ADD, scaledAndWeightedObservations(observations, weights, yearFrac).getMulti(), new BigDecimal("1.0")));
		}
		
		@Override
		protected MapperC<BigDecimal> growthCurve(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac) {
			return MapperC.<BigDecimal>of(vectorGrowthOperation.evaluate(new BigDecimal("1.0"), growthFactors(observations, weights, yearFrac).getMulti()));
		}
		
		@Override
		protected MapperS<BigDecimal> finalValue(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac) {
			return growthCurve(observations, weights, yearFrac)
				.last();
		}
		
		@Override
		protected MapperS<BigDecimal> totalWeight(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac) {
			return MapperC.<BigDecimal>of(weights)
				.sumBigDecimal();
		}
		
		@Override
		protected MapperS<BigDecimal> overallYearFrac(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(totalWeight(observations, weights, yearFrac), MapperS.of(yearFrac));
		}
		
		@Override
		protected MapperS<BigDecimal> calculatedRate(List<BigDecimal> observations, List<BigDecimal> weights, BigDecimal yearFrac) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>divide(MapperMaths.<BigDecimal, BigDecimal, BigDecimal>subtract(finalValue(observations, weights, yearFrac), MapperS.of(BigDecimal.valueOf(1))), overallYearFrac(observations, weights, yearFrac));
		}
	}
}
