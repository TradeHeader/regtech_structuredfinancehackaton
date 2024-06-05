package cdm.observable.asset.calculatedrate.functions;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.base.math.functions.VectorOperation;
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


@ImplementedBy(ApplyAveragingFormula.ApplyAveragingFormulaDefault.class)
public abstract class ApplyAveragingFormula implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected VectorOperation vectorOperation;

	/**
	* @param observations a vector of observation value.
	* @param weights a vector of weights (should be same size as observations, 1 weight per observation.
	* @return results Details of the averaging calculation.
	*/
	public CalculatedRateDetails evaluate(List<BigDecimal> observations, List<BigDecimal> weights) {
		CalculatedRateDetails.CalculatedRateDetailsBuilder resultsBuilder = doEvaluate(observations, weights);
		
		final CalculatedRateDetails results;
		if (resultsBuilder == null) {
			results = null;
		} else {
			results = resultsBuilder.build();
			objectValidator.validate(CalculatedRateDetails.class, results);
		}
		
		return results;
	}

	protected abstract CalculatedRateDetails.CalculatedRateDetailsBuilder doEvaluate(List<BigDecimal> observations, List<BigDecimal> weights);

	protected abstract MapperC<BigDecimal> weightedObservations(List<BigDecimal> observations, List<BigDecimal> weights);

	protected abstract MapperS<BigDecimal> totalWeightedObservations(List<BigDecimal> observations, List<BigDecimal> weights);

	protected abstract MapperS<BigDecimal> totalWeight(List<BigDecimal> observations, List<BigDecimal> weights);

	protected abstract MapperS<BigDecimal> calculatedRate(List<BigDecimal> observations, List<BigDecimal> weights);

	public static class ApplyAveragingFormulaDefault extends ApplyAveragingFormula {
		@Override
		protected CalculatedRateDetails.CalculatedRateDetailsBuilder doEvaluate(List<BigDecimal> observations, List<BigDecimal> weights) {
			if (observations == null) {
				observations = Collections.emptyList();
			}
			if (weights == null) {
				weights = Collections.emptyList();
			}
			CalculatedRateDetails.CalculatedRateDetailsBuilder results = CalculatedRateDetails.builder();
			return assignOutput(results, observations, weights);
		}
		
		protected CalculatedRateDetails.CalculatedRateDetailsBuilder assignOutput(CalculatedRateDetails.CalculatedRateDetailsBuilder results, List<BigDecimal> observations, List<BigDecimal> weights) {
			results
				.setAggregateValue(totalWeightedObservations(observations, weights).get());
			
			results
				.setAggregateWeight(totalWeight(observations, weights).get());
			
			results
				.setCalculatedRate(calculatedRate(observations, weights).get());
			
			results
				.addWeightedRates(weightedObservations(observations, weights).getMulti());
			
			return Optional.ofNullable(results)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperC<BigDecimal> weightedObservations(List<BigDecimal> observations, List<BigDecimal> weights) {
			return MapperC.<BigDecimal>of(vectorOperation.evaluate(ArithmeticOperationEnum.MULTIPLY, observations, weights));
		}
		
		@Override
		protected MapperS<BigDecimal> totalWeightedObservations(List<BigDecimal> observations, List<BigDecimal> weights) {
			return weightedObservations(observations, weights)
				.sumBigDecimal();
		}
		
		@Override
		protected MapperS<BigDecimal> totalWeight(List<BigDecimal> observations, List<BigDecimal> weights) {
			return MapperC.<BigDecimal>of(weights)
				.sumBigDecimal();
		}
		
		@Override
		protected MapperS<BigDecimal> calculatedRate(List<BigDecimal> observations, List<BigDecimal> weights) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>divide(totalWeightedObservations(observations, weights), totalWeight(observations, weights));
		}
	}
}
