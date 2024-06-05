package cdm.product.asset.calculation.functions;

import cdm.base.datetime.daycount.DayCountFractionEnum;
import cdm.base.datetime.daycount.metafields.FieldWithMetaDayCountFractionEnum;
import cdm.base.math.UnitType;
import cdm.observable.asset.Money;
import cdm.product.asset.FixedAmountCalculationDetails;
import cdm.product.asset.FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder;
import cdm.product.asset.InterestRatePayout;
import cdm.product.common.schedule.CalculationPeriodBase;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(FixedAmountCalculation.FixedAmountCalculationDefault.class)
public abstract class FixedAmountCalculation implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected CalculateYearFraction calculateYearFraction;
	@Inject protected GetFixedRate getFixedRate;
	@Inject protected GetNotionalAmount getNotionalAmount;

	/**
	* @param interestRatePayout 
	* @param calculationPeriod 
	* @param notional 
	* @return fixedAmountDetails 
	*/
	public FixedAmountCalculationDetails evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional) {
		FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder fixedAmountDetailsBuilder = doEvaluate(interestRatePayout, calculationPeriod, notional);
		
		final FixedAmountCalculationDetails fixedAmountDetails;
		if (fixedAmountDetailsBuilder == null) {
			fixedAmountDetails = null;
		} else {
			fixedAmountDetails = fixedAmountDetailsBuilder.build();
			objectValidator.validate(FixedAmountCalculationDetails.class, fixedAmountDetails);
		}
		
		return fixedAmountDetails;
	}

	protected abstract FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional);

	protected abstract MapperS<BigDecimal> fixedRate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional);

	protected abstract MapperS<? extends Money> calculationAmount(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional);

	protected abstract MapperS<DayCountFractionEnum> dcf(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional);

	protected abstract MapperS<BigDecimal> yearFraction(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional);

	protected abstract MapperS<BigDecimal> calcAmt(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional);

	public static class FixedAmountCalculationDefault extends FixedAmountCalculation {
		@Override
		protected FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional) {
			FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder fixedAmountDetails = FixedAmountCalculationDetails.builder();
			return assignOutput(fixedAmountDetails, interestRatePayout, calculationPeriod, notional);
		}
		
		protected FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder assignOutput(FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder fixedAmountDetails, InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional) {
			fixedAmountDetails
				.setCalculationPeriod(calculationPeriod);
			
			fixedAmountDetails
				.getOrCreateCalculationPeriodNotionalAmount()
				.setValue(calcAmt(interestRatePayout, calculationPeriod, notional).get());
			
			fixedAmountDetails
				.getOrCreateCalculationPeriodNotionalAmount()
				.getOrCreateUnit()
				.setCurrencyValue(calculationAmount(interestRatePayout, calculationPeriod, notional).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()).get());
			
			fixedAmountDetails
				.setFixedRate(fixedRate(interestRatePayout, calculationPeriod, notional).get());
			
			fixedAmountDetails
				.setYearFraction(yearFraction(interestRatePayout, calculationPeriod, notional).get());
			
			fixedAmountDetails
				.setCalculatedAmount(MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(calcAmt(interestRatePayout, calculationPeriod, notional), MapperS.of(fixedAmountDetails).<BigDecimal>map("getFixedRate", fixedAmountCalculationDetails -> fixedAmountCalculationDetails.getFixedRate())), MapperS.of(fixedAmountDetails).<BigDecimal>map("getYearFraction", fixedAmountCalculationDetails -> fixedAmountCalculationDetails.getYearFraction())).get());
			
			return Optional.ofNullable(fixedAmountDetails)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<BigDecimal> fixedRate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional) {
			return MapperS.of(getFixedRate.evaluate(interestRatePayout, calculationPeriod));
		}
		
		@Override
		protected MapperS<? extends Money> calculationAmount(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional) {
			return MapperS.of(getNotionalAmount.evaluate(interestRatePayout, calculationPeriod));
		}
		
		@Override
		protected MapperS<DayCountFractionEnum> dcf(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional) {
			return MapperS.of(interestRatePayout).<FieldWithMetaDayCountFractionEnum>map("getDayCountFraction", _interestRatePayout -> _interestRatePayout.getDayCountFraction()).<DayCountFractionEnum>map("getValue", _f->_f.getValue());
		}
		
		@Override
		protected MapperS<BigDecimal> yearFraction(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional) {
			return MapperS.of(calculateYearFraction.evaluate(interestRatePayout, dcf(interestRatePayout, calculationPeriod, notional).get(), calculationPeriod));
		}
		
		@Override
		protected MapperS<BigDecimal> calcAmt(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod, BigDecimal notional) {
			if (exists(MapperS.of(notional)).getOrDefault(false)) {
				return MapperS.of(notional);
			}
			return calculationAmount(interestRatePayout, calculationPeriod, notional).<BigDecimal>map("getValue", measureBase -> measureBase.getValue());
		}
	}
}
