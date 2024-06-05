package cdm.product.asset.calculation.functions;

import cdm.base.datetime.daycount.DayCountFractionEnum;
import cdm.base.datetime.daycount.metafields.FieldWithMetaDayCountFractionEnum;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails;
import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder;
import cdm.product.asset.floatingrate.FloatingRateProcessingDetails;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails;
import cdm.product.common.schedule.CalculationPeriodBase;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(CalculateFloatingCashFlow.CalculateFloatingCashFlowDefault.class)
public abstract class CalculateFloatingCashFlow implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected CalculateYearFraction calculateYearFraction;

	/**
	* @param interestRatePayout The interest rate stream for which the floating amount calculation is being done.
	* @param calculationPeriod The calculation period for which the floating rate calculation is being done.
	* @param notional 
	* @param currency 
	* @param floatingRateSetting Details of the rate observation/calculation corresponding to the supplied rate definition and calculation period.
	* @param processedRateDetails Results are details of the rate treatment.
	* @return result The details of the floating rate calculation, including raw rate, rate treatment details, notional, and calculated cashflow amount.
	*/
	public FloatingAmountCalculationDetails evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails) {
		FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder resultBuilder = doEvaluate(interestRatePayout, calculationPeriod, notional, currency, floatingRateSetting, processedRateDetails);
		
		final FloatingAmountCalculationDetails result;
		if (resultBuilder == null) {
			result = null;
		} else {
			result = resultBuilder.build();
			objectValidator.validate(FloatingAmountCalculationDetails.class, result);
		}
		
		return result;
	}

	protected abstract FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails);

	protected abstract MapperS<BigDecimal> appliedRate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails);

	protected abstract MapperS<BigDecimal> spreadExclusiveRate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails);

	protected abstract MapperS<DayCountFractionEnum> dcf(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails);

	protected abstract MapperS<BigDecimal> yearFraction(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails);

	protected abstract MapperS<BigDecimal> annualAccrual(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails);

	protected abstract MapperS<BigDecimal> notionalAccrual(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails);

	protected abstract MapperS<BigDecimal> cashflow(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails);

	protected abstract MapperS<BigDecimal> spreadExclusiveCashflow(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails);

	public static class CalculateFloatingCashFlowDefault extends CalculateFloatingCashFlow {
		@Override
		protected FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails) {
			FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder result = FloatingAmountCalculationDetails.builder();
			return assignOutput(result, interestRatePayout, calculationPeriod, notional, currency, floatingRateSetting, processedRateDetails);
		}
		
		protected FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder assignOutput(FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder result, InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails) {
			result
				.setCalculationPeriod(calculationPeriod);
			
			result
				.getOrCreateCalculationPeriodNotionalAmount()
				.setValue(notional);
			
			result
				.getOrCreateCalculationPeriodNotionalAmount()
				.getOrCreateUnit()
				.setCurrencyValue(currency);
			
			FloatingRateSettingDetails ifThenElseResult = null;
			if (exists(MapperS.of(floatingRateSetting)).getOrDefault(false)) {
				ifThenElseResult = floatingRateSetting;
			}
			result
				.setFloatingRate(ifThenElseResult);
			
			result
				.setProcessingDetails(processedRateDetails);
			
			result
				.setAppliedRate(appliedRate(interestRatePayout, calculationPeriod, notional, currency, floatingRateSetting, processedRateDetails).get());
			
			result
				.setYearFraction(yearFraction(interestRatePayout, calculationPeriod, notional, currency, floatingRateSetting, processedRateDetails).get());
			
			result
				.setCalculatedAmount(cashflow(interestRatePayout, calculationPeriod, notional, currency, floatingRateSetting, processedRateDetails).get());
			
			result
				.setSpreadExclusiveCalculatedAMount(spreadExclusiveCashflow(interestRatePayout, calculationPeriod, notional, currency, floatingRateSetting, processedRateDetails).get());
			
			return Optional.ofNullable(result)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<BigDecimal> appliedRate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails) {
			return MapperS.of(processedRateDetails).<BigDecimal>map("getProcessedRate", floatingRateProcessingDetails -> floatingRateProcessingDetails.getProcessedRate());
		}
		
		@Override
		protected MapperS<BigDecimal> spreadExclusiveRate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails) {
			return MapperS.of(processedRateDetails).<BigDecimal>map("getSpreadExclusiveRate", floatingRateProcessingDetails -> floatingRateProcessingDetails.getSpreadExclusiveRate());
		}
		
		@Override
		protected MapperS<DayCountFractionEnum> dcf(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails) {
			return MapperS.of(interestRatePayout).<FieldWithMetaDayCountFractionEnum>map("getDayCountFraction", _interestRatePayout -> _interestRatePayout.getDayCountFraction()).<DayCountFractionEnum>map("getValue", _f->_f.getValue());
		}
		
		@Override
		protected MapperS<BigDecimal> yearFraction(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails) {
			return MapperS.of(calculateYearFraction.evaluate(interestRatePayout, dcf(interestRatePayout, calculationPeriod, notional, currency, floatingRateSetting, processedRateDetails).get(), calculationPeriod));
		}
		
		@Override
		protected MapperS<BigDecimal> annualAccrual(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(MapperS.of(notional), appliedRate(interestRatePayout, calculationPeriod, notional, currency, floatingRateSetting, processedRateDetails));
		}
		
		@Override
		protected MapperS<BigDecimal> notionalAccrual(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(MapperS.of(notional), yearFraction(interestRatePayout, calculationPeriod, notional, currency, floatingRateSetting, processedRateDetails));
		}
		
		@Override
		protected MapperS<BigDecimal> cashflow(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(notionalAccrual(interestRatePayout, calculationPeriod, notional, currency, floatingRateSetting, processedRateDetails), appliedRate(interestRatePayout, calculationPeriod, notional, currency, floatingRateSetting, processedRateDetails));
		}
		
		@Override
		protected MapperS<BigDecimal> spreadExclusiveCashflow(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional, String currency, FloatingRateSettingDetails floatingRateSetting, FloatingRateProcessingDetails processedRateDetails) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(notionalAccrual(interestRatePayout, calculationPeriod, notional, currency, floatingRateSetting, processedRateDetails), spreadExclusiveRate(interestRatePayout, calculationPeriod, notional, currency, floatingRateSetting, processedRateDetails));
		}
	}
}
