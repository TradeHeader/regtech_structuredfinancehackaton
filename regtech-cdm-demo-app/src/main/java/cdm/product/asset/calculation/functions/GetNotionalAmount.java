package cdm.product.asset.calculation.functions;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.UnitType;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.observable.asset.Money;
import cdm.observable.asset.Money.MoneyBuilder;
import cdm.product.asset.InterestRatePayout;
import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(GetNotionalAmount.GetNotionalAmountDefault.class)
public abstract class GetNotionalAmount implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected GetQuantityScheduleStepValues getQuantityScheduleStepValues;

	/**
	* @param interestRatePayout An interest rate stream.
	* @param calculationPeriod The calculation period for which you want the notional.
	* @return notional The notional that is in effect starting from the adjustedPeriodStartDate.
	*/
	public Money evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
		Money.MoneyBuilder notionalBuilder = doEvaluate(interestRatePayout, calculationPeriod);
		
		final Money notional;
		if (notionalBuilder == null) {
			notional = null;
		} else {
			notional = notionalBuilder.build();
			objectValidator.validate(Money.class, notional);
		}
		
		return notional;
	}

	protected abstract Money.MoneyBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod);

	public static class GetNotionalAmountDefault extends GetNotionalAmount {
		@Override
		protected Money.MoneyBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			Money.MoneyBuilder notional = Money.builder();
			return assignOutput(notional, interestRatePayout, calculationPeriod);
		}
		
		protected Money.MoneyBuilder assignOutput(Money.MoneyBuilder notional, InterestRatePayout interestRatePayout, CalculationPeriodBase calculationPeriod) {
			notional
				.setValue(MapperC.<BigDecimal>of(getQuantityScheduleStepValues.evaluate(MapperS.of(interestRatePayout).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<ReferenceWithMetaNonNegativeQuantitySchedule>map("getQuantitySchedule", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantitySchedule()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).get(), MapperS.of(calculationPeriod).<Date>map("getAdjustedStartDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedStartDate()).get()))
					.last().get());
			
			notional
				.getOrCreateUnit()
				.setCurrencyValue(MapperS.of(interestRatePayout).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<ReferenceWithMetaNonNegativeQuantitySchedule>map("getQuantitySchedule", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantitySchedule()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()).get());
			
			return Optional.ofNullable(notional)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
