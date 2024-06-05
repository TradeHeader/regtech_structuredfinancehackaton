package cdm.product.asset.calculation.functions;

import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails;
import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails;
import cdm.product.asset.floatingrate.functions.DetermineFloatingRateReset;
import cdm.product.common.schedule.CalculationPeriodBase;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(FloatingAmountCalculation.FloatingAmountCalculationDefault.class)
public abstract class FloatingAmountCalculation implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected ApplyFloatingRateSetting applyFloatingRateSetting;
	@Inject protected DetermineFloatingRateReset determineFloatingRateReset;

	/**
	* @param interestRatePayout The interest rate stream for which the floating amount calculation is being done.
	* @param calculationPeriod The calculation period for which the floating rate calculation is being done.
	* @param isInitialPeriod Is this the initial calculation period?.
	* @param suppliedNotional 
	* @param suppliedRate 
	* @return result The details of the floating rate calculation, including raw rate, rate treatment details, notional, and calculated cashlow amount.
	*/
	public FloatingAmountCalculationDetails evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate) {
		FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder resultBuilder = doEvaluate(interestRatePayout, calculationPeriod, isInitialPeriod, suppliedNotional, suppliedRate);
		
		final FloatingAmountCalculationDetails result;
		if (resultBuilder == null) {
			result = null;
		} else {
			result = resultBuilder.build();
			objectValidator.validate(FloatingAmountCalculationDetails.class, result);
		}
		
		return result;
	}

	protected abstract FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate);

	protected abstract MapperS<? extends FloatingRateSettingDetails> floatingRateSetting(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate);

	public static class FloatingAmountCalculationDefault extends FloatingAmountCalculation {
		@Override
		protected FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate) {
			FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder result = FloatingAmountCalculationDetails.builder();
			return assignOutput(result, interestRatePayout, calculationPeriod, isInitialPeriod, suppliedNotional, suppliedRate);
		}
		
		protected FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder assignOutput(FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder result, InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate) {
			result = toBuilder(applyFloatingRateSetting.evaluate(interestRatePayout, calculationPeriod, isInitialPeriod, suppliedNotional, suppliedRate, floatingRateSetting(interestRatePayout, calculationPeriod, isInitialPeriod, suppliedNotional, suppliedRate).get()));
			
			return Optional.ofNullable(result)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends FloatingRateSettingDetails> floatingRateSetting(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate) {
			if (notExists(MapperS.of(suppliedRate)).getOrDefault(false)) {
				return MapperS.of(determineFloatingRateReset.evaluate(interestRatePayout, calculationPeriod));
			}
			return MapperS.<FloatingRateSettingDetails>ofNull();
		}
	}
}
