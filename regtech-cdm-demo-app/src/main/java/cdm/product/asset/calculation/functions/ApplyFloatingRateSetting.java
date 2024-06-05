package cdm.product.asset.calculation.functions;

import cdm.base.math.UnitType;
import cdm.observable.asset.Money;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails;
import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder;
import cdm.product.asset.floatingrate.FloatingRateProcessingDetails;
import cdm.product.asset.floatingrate.FloatingRateProcessingParameters;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails;
import cdm.product.asset.floatingrate.functions.ApplyFloatingRateProcessing;
import cdm.product.asset.floatingrate.functions.GetFloatingRateProcessingParameters;
import cdm.product.common.schedule.CalculationPeriodBase;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ApplyFloatingRateSetting.ApplyFloatingRateSettingDefault.class)
public abstract class ApplyFloatingRateSetting implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected ApplyFloatingRateProcessing applyFloatingRateProcessing;
	@Inject protected CalculateFloatingCashFlow calculateFloatingCashFlow;
	@Inject protected DefaultFloatingRate defaultFloatingRate;
	@Inject protected GetFloatingRateProcessingParameters getFloatingRateProcessingParameters;
	@Inject protected GetNotionalAmount getNotionalAmount;

	/**
	* @param interestRatePayout The interest rate stream for which the floating amount calculation is being done.
	* @param calculationPeriod The calculation period for which the floating rate calculation is being done.
	* @param isInitialPeriod Is this the initial calculation period?.
	* @param suppliedNotional 
	* @param suppliedRate 
	* @param floatingRateSetting Details of the rate observation/calculation corresonding to the supplied rate definition and calculation period.
	* @return result The details of the floating rate calculation, including raw rate, rate treatment details, notional, and calculated cashlow amount.
	*/
	public FloatingAmountCalculationDetails evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting) {
		FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder resultBuilder = doEvaluate(interestRatePayout, calculationPeriod, isInitialPeriod, suppliedNotional, suppliedRate, floatingRateSetting);
		
		final FloatingAmountCalculationDetails result;
		if (resultBuilder == null) {
			result = null;
		} else {
			result = resultBuilder.build();
			objectValidator.validate(FloatingAmountCalculationDetails.class, result);
		}
		
		return result;
	}

	protected abstract FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting);

	protected abstract MapperS<BigDecimal> floatingRate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting);

	protected abstract MapperS<? extends FloatingRateProcessingParameters> processingParameters(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting);

	protected abstract MapperS<? extends FloatingRateProcessingDetails> processedRateDetails(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting);

	protected abstract MapperS<? extends Money> periodNotional(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting);

	protected abstract MapperS<BigDecimal> notional(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting);

	protected abstract MapperS<String> currency(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting);

	public static class ApplyFloatingRateSettingDefault extends ApplyFloatingRateSetting {
		@Override
		protected FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting) {
			FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder result = FloatingAmountCalculationDetails.builder();
			return assignOutput(result, interestRatePayout, calculationPeriod, isInitialPeriod, suppliedNotional, suppliedRate, floatingRateSetting);
		}
		
		protected FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder assignOutput(FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder result, InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting) {
			result = toBuilder(calculateFloatingCashFlow.evaluate(interestRatePayout, calculationPeriod, notional(interestRatePayout, calculationPeriod, isInitialPeriod, suppliedNotional, suppliedRate, floatingRateSetting).get(), currency(interestRatePayout, calculationPeriod, isInitialPeriod, suppliedNotional, suppliedRate, floatingRateSetting).get(), floatingRateSetting, processedRateDetails(interestRatePayout, calculationPeriod, isInitialPeriod, suppliedNotional, suppliedRate, floatingRateSetting).get()));
			
			return Optional.ofNullable(result)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<BigDecimal> floatingRate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting) {
			return MapperS.of(floatingRateSetting).<BigDecimal>map("getFloatingRate", floatingRateSettingDetails -> floatingRateSettingDetails.getFloatingRate());
		}
		
		@Override
		protected MapperS<? extends FloatingRateProcessingParameters> processingParameters(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting) {
			return MapperS.of(getFloatingRateProcessingParameters.evaluate(interestRatePayout, calculationPeriod));
		}
		
		@Override
		protected MapperS<? extends FloatingRateProcessingDetails> processedRateDetails(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting) {
			if (exists(MapperS.of(suppliedRate)).getOrDefault(false)) {
				return MapperS.of(defaultFloatingRate.evaluate(suppliedRate));
			}
			return MapperS.of(applyFloatingRateProcessing.evaluate(processingParameters(interestRatePayout, calculationPeriod, isInitialPeriod, suppliedNotional, suppliedRate, floatingRateSetting).get(), floatingRate(interestRatePayout, calculationPeriod, isInitialPeriod, suppliedNotional, suppliedRate, floatingRateSetting).get(), calculationPeriod, isInitialPeriod));
		}
		
		@Override
		protected MapperS<? extends Money> periodNotional(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting) {
			return MapperS.of(getNotionalAmount.evaluate(interestRatePayout, calculationPeriod));
		}
		
		@Override
		protected MapperS<BigDecimal> notional(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting) {
			if (exists(MapperS.of(suppliedNotional)).getOrDefault(false)) {
				return MapperS.of(suppliedNotional);
			}
			return periodNotional(interestRatePayout, calculationPeriod, isInitialPeriod, suppliedNotional, suppliedRate, floatingRateSetting).<BigDecimal>map("getValue", measureBase -> measureBase.getValue());
		}
		
		@Override
		protected MapperS<String> currency(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod, BigDecimal suppliedNotional, BigDecimal suppliedRate, FloatingRateSettingDetails floatingRateSetting) {
			return periodNotional(interestRatePayout, calculationPeriod, isInitialPeriod, suppliedNotional, suppliedRate, floatingRateSetting).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue());
		}
	}
}
