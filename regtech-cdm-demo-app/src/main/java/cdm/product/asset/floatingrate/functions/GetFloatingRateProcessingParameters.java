package cdm.product.asset.floatingrate.functions;

import cdm.base.math.Rounding;
import cdm.observable.asset.Price;
import cdm.product.asset.FloatingRateSpecification;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.NegativeInterestRateTreatmentEnum;
import cdm.product.asset.RateSpecification;
import cdm.product.asset.RateTreatmentEnum;
import cdm.product.asset.floatingrate.FloatingRateProcessingParameters;
import cdm.product.asset.floatingrate.FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder;
import cdm.product.common.schedule.CalculationPeriodBase;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(GetFloatingRateProcessingParameters.GetFloatingRateProcessingParametersDefault.class)
public abstract class GetFloatingRateProcessingParameters implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected CapRateAmount capRateAmount;
	@Inject protected FloorRateAmount floorRateAmount;
	@Inject protected MultiplierAmount multiplierAmount;
	@Inject protected SpreadAmount spreadAmount;

	/**
	* @param interestRatePayout An interest rate stream.
	* @param calculationPeriod The calculation period for which the calculation is being perfmored (needed to look up paramters).
	* @return processingParameters The processing parameters.
	*/
	public FloatingRateProcessingParameters evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
		FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder processingParametersBuilder = doEvaluate(interestRatePayout, calculationPeriod);
		
		final FloatingRateProcessingParameters processingParameters;
		if (processingParametersBuilder == null) {
			processingParameters = null;
		} else {
			processingParameters = processingParametersBuilder.build();
			objectValidator.validate(FloatingRateProcessingParameters.class, processingParameters);
		}
		
		return processingParameters;
	}

	protected abstract FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<BigDecimal> spreadRate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<BigDecimal> multiplier(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<BigDecimal> cap(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<BigDecimal> floor(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<? extends Rounding> rounding(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<NegativeInterestRateTreatmentEnum> negativeTreatment(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<RateTreatmentEnum> treatment(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod);

	public static class GetFloatingRateProcessingParametersDefault extends GetFloatingRateProcessingParameters {
		@Override
		protected FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder processingParameters = FloatingRateProcessingParameters.builder();
			return assignOutput(processingParameters, interestRatePayout, calculationPeriod);
		}
		
		protected FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder assignOutput(FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder processingParameters, InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			processingParameters
				.setInitialRate(MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate()).<Price>map("getInitialRate", floatingRateSpecification -> floatingRateSpecification.getInitialRate()).get());
			
			processingParameters
				.setSpread(spreadRate(interestRatePayout, calculationPeriod).get());
			
			processingParameters
				.setMultiplier(multiplier(interestRatePayout, calculationPeriod).get());
			
			processingParameters
				.setTreatment(treatment(interestRatePayout, calculationPeriod).get());
			
			processingParameters
				.setCapRate(cap(interestRatePayout, calculationPeriod).get());
			
			processingParameters
				.setFloorRate(floor(interestRatePayout, calculationPeriod).get());
			
			processingParameters
				.setRounding(rounding(interestRatePayout, calculationPeriod).get());
			
			processingParameters
				.setNegativeTreatment(negativeTreatment(interestRatePayout, calculationPeriod).get());
			
			return Optional.ofNullable(processingParameters)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<BigDecimal> spreadRate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(spreadAmount.evaluate(interestRatePayout, calculationPeriod));
		}
		
		@Override
		protected MapperS<BigDecimal> multiplier(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(multiplierAmount.evaluate(interestRatePayout, calculationPeriod));
		}
		
		@Override
		protected MapperS<BigDecimal> cap(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(capRateAmount.evaluate(interestRatePayout, calculationPeriod));
		}
		
		@Override
		protected MapperS<BigDecimal> floor(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(floorRateAmount.evaluate(interestRatePayout, calculationPeriod));
		}
		
		@Override
		protected MapperS<? extends Rounding> rounding(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate()).<Rounding>map("getFinalRateRounding", floatingRateSpecification -> floatingRateSpecification.getFinalRateRounding());
		}
		
		@Override
		protected MapperS<NegativeInterestRateTreatmentEnum> negativeTreatment(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate()).<NegativeInterestRateTreatmentEnum>map("getNegativeInterestRateTreatment", floatingRateSpecification -> floatingRateSpecification.getNegativeInterestRateTreatment());
		}
		
		@Override
		protected MapperS<RateTreatmentEnum> treatment(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate()).<RateTreatmentEnum>map("getRateTreatment", floatingRate -> floatingRate.getRateTreatment());
		}
	}
}
