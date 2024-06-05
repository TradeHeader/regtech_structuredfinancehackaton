package cdm.observable.asset.calculatedrate.functions;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.base.math.functions.VectorScalarOperation;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.calculatedrate.ObservationParameters;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ProcessObservations.ProcessObservationsDefault.class)
public abstract class ProcessObservations implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected VectorScalarOperation vectorScalarOperation;

	/**
	* @param calculationParameters Floating rate definition for the calculated rate.
	* @param rawObservations 
	* @return processedObservations 
	*/
	public List<BigDecimal> evaluate(FloatingRateCalculationParameters calculationParameters, List<BigDecimal> rawObservations) {
		List<BigDecimal> processedObservations = doEvaluate(calculationParameters, rawObservations);
		
		return processedObservations;
	}

	protected abstract List<BigDecimal> doEvaluate(FloatingRateCalculationParameters calculationParameters, List<BigDecimal> rawObservations);

	protected abstract MapperS<? extends ObservationParameters> params(FloatingRateCalculationParameters calculationParameters, List<BigDecimal> rawObservations);

	protected abstract MapperS<BigDecimal> cap(FloatingRateCalculationParameters calculationParameters, List<BigDecimal> rawObservations);

	protected abstract MapperS<BigDecimal> floor(FloatingRateCalculationParameters calculationParameters, List<BigDecimal> rawObservations);

	protected abstract MapperC<BigDecimal> cappedObservations(FloatingRateCalculationParameters calculationParameters, List<BigDecimal> rawObservations);

	protected abstract MapperC<BigDecimal> flooredObservations(FloatingRateCalculationParameters calculationParameters, List<BigDecimal> rawObservations);

	public static class ProcessObservationsDefault extends ProcessObservations {
		@Override
		protected List<BigDecimal> doEvaluate(FloatingRateCalculationParameters calculationParameters, List<BigDecimal> rawObservations) {
			if (rawObservations == null) {
				rawObservations = Collections.emptyList();
			}
			List<BigDecimal> processedObservations = new ArrayList<>();
			return assignOutput(processedObservations, calculationParameters, rawObservations);
		}
		
		protected List<BigDecimal> assignOutput(List<BigDecimal> processedObservations, FloatingRateCalculationParameters calculationParameters, List<BigDecimal> rawObservations) {
			processedObservations.addAll(flooredObservations(calculationParameters, rawObservations).getMulti());
			
			return processedObservations;
		}
		
		@Override
		protected MapperS<? extends ObservationParameters> params(FloatingRateCalculationParameters calculationParameters, List<BigDecimal> rawObservations) {
			return MapperS.of(calculationParameters).<ObservationParameters>map("getObservationParameters", floatingRateCalculationParameters -> floatingRateCalculationParameters.getObservationParameters());
		}
		
		@Override
		protected MapperS<BigDecimal> cap(FloatingRateCalculationParameters calculationParameters, List<BigDecimal> rawObservations) {
			if (exists(params(calculationParameters, rawObservations)).getOrDefault(false)) {
				return params(calculationParameters, rawObservations).<BigDecimal>map("getObservationCapRate", observationParameters -> observationParameters.getObservationCapRate());
			}
			return MapperS.<BigDecimal>ofNull();
		}
		
		@Override
		protected MapperS<BigDecimal> floor(FloatingRateCalculationParameters calculationParameters, List<BigDecimal> rawObservations) {
			if (exists(params(calculationParameters, rawObservations)).getOrDefault(false)) {
				return params(calculationParameters, rawObservations).<BigDecimal>map("getObservationFloorRate", observationParameters -> observationParameters.getObservationFloorRate());
			}
			return MapperS.<BigDecimal>ofNull();
		}
		
		@Override
		protected MapperC<BigDecimal> cappedObservations(FloatingRateCalculationParameters calculationParameters, List<BigDecimal> rawObservations) {
			if (exists(cap(calculationParameters, rawObservations)).getOrDefault(false)) {
				return MapperC.<BigDecimal>of(vectorScalarOperation.evaluate(ArithmeticOperationEnum.MIN, rawObservations, cap(calculationParameters, rawObservations).get()));
			}
			return MapperC.<BigDecimal>of(rawObservations);
		}
		
		@Override
		protected MapperC<BigDecimal> flooredObservations(FloatingRateCalculationParameters calculationParameters, List<BigDecimal> rawObservations) {
			if (exists(floor(calculationParameters, rawObservations)).getOrDefault(false)) {
				return MapperC.<BigDecimal>of(vectorScalarOperation.evaluate(ArithmeticOperationEnum.MAX, cappedObservations(calculationParameters, rawObservations).getMulti(), floor(calculationParameters, rawObservations).get()));
			}
			return cappedObservations(calculationParameters, rawObservations);
		}
	}
}
