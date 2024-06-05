package cdm.product.asset.floatingrate.functions;

import cdm.product.asset.FloatingRateSpecification;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.RateSpecification;
import cdm.product.asset.floatingrate.FloatingRateIndexProcessingTypeEnum;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder;
import cdm.product.common.schedule.CalculationPeriodBase;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(DetermineFloatingRateReset.DetermineFloatingRateResetDefault.class)
public abstract class DetermineFloatingRateReset implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected GetFloatingRateProcessingType getFloatingRateProcessingType;
	@Inject protected ProcessFloatingRateReset processFloatingRateReset;

	/**
	* @param interestRatePayout Floating rate stream definition.
	* @param calcPeriod The calculation period for which you want the rate.
	* @return floatingRate Details of the rate observation/calculation corresonding to the supplied rate definition and calculation period.
	*/
	public FloatingRateSettingDetails evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod) {
		FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRateBuilder = doEvaluate(interestRatePayout, calcPeriod);
		
		final FloatingRateSettingDetails floatingRate;
		if (floatingRateBuilder == null) {
			floatingRate = null;
		} else {
			floatingRate = floatingRateBuilder.build();
			objectValidator.validate(FloatingRateSettingDetails.class, floatingRate);
		}
		
		return floatingRate;
	}

	protected abstract FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod);

	protected abstract MapperS<? extends FloatingRateSpecification> rateDef(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod);

	protected abstract MapperS<FloatingRateIndexProcessingTypeEnum> processingType(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod);

	public static class DetermineFloatingRateResetDefault extends DetermineFloatingRateReset {
		@Override
		protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod) {
			FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRate = FloatingRateSettingDetails.builder();
			return assignOutput(floatingRate, interestRatePayout, calcPeriod);
		}
		
		protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder assignOutput(FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRate, InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod) {
			floatingRate = toBuilder(processFloatingRateReset.evaluate(interestRatePayout, calcPeriod, processingType(interestRatePayout, calcPeriod).get()));
			
			return Optional.ofNullable(floatingRate)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends FloatingRateSpecification> rateDef(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod) {
			return MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate());
		}
		
		@Override
		protected MapperS<FloatingRateIndexProcessingTypeEnum> processingType(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod) {
			return MapperS.of(getFloatingRateProcessingType.evaluate(rateDef(interestRatePayout, calcPeriod).get()));
		}
	}
}
