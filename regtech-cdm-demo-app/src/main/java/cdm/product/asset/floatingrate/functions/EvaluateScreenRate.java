package cdm.product.asset.floatingrate.functions;

import cdm.observable.asset.FloatingRateOption;
import cdm.observable.asset.fro.functions.IndexValueObservation;
import cdm.observable.asset.metafields.ReferenceWithMetaFloatingRateOption;
import cdm.product.asset.FloatingRate;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder;
import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.ResetDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(EvaluateScreenRate.EvaluateScreenRateDefault.class)
public abstract class EvaluateScreenRate implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected DetermineFixingDate determineFixingDate;
	@Inject protected DetermineResetDate determineResetDate;
	@Inject protected IndexValueObservation indexValueObservation;

	/**
	* @param rateDef Floating rate definition.
	* @param resetDates Reset dates for observing the rate.
	* @param calculationPeriod Calculation period for which you want the rate.
	* @return details Resulting details of the rate setting .
	*/
	public FloatingRateSettingDetails evaluate(FloatingRate rateDef, ResetDates resetDates, CalculationPeriodBase calculationPeriod) {
		FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder detailsBuilder = doEvaluate(rateDef, resetDates, calculationPeriod);
		
		final FloatingRateSettingDetails details;
		if (detailsBuilder == null) {
			details = null;
		} else {
			details = detailsBuilder.build();
			objectValidator.validate(FloatingRateSettingDetails.class, details);
		}
		
		return details;
	}

	protected abstract FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(FloatingRate rateDef, ResetDates resetDates, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<Date> resetDate(FloatingRate rateDef, ResetDates resetDates, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<Date> fixingDate(FloatingRate rateDef, ResetDates resetDates, CalculationPeriodBase calculationPeriod);

	protected abstract MapperS<BigDecimal> observedRate(FloatingRate rateDef, ResetDates resetDates, CalculationPeriodBase calculationPeriod);

	public static class EvaluateScreenRateDefault extends EvaluateScreenRate {
		@Override
		protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(FloatingRate rateDef, ResetDates resetDates, CalculationPeriodBase calculationPeriod) {
			FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder details = FloatingRateSettingDetails.builder();
			return assignOutput(details, rateDef, resetDates, calculationPeriod);
		}
		
		protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder assignOutput(FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder details, FloatingRate rateDef, ResetDates resetDates, CalculationPeriodBase calculationPeriod) {
			details
				.setResetDate(resetDate(rateDef, resetDates, calculationPeriod).get());
			
			details
				.setObservationDate(fixingDate(rateDef, resetDates, calculationPeriod).get());
			
			details
				.setFloatingRate(observedRate(rateDef, resetDates, calculationPeriod).get());
			
			return Optional.ofNullable(details)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<Date> resetDate(FloatingRate rateDef, ResetDates resetDates, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(determineResetDate.evaluate(resetDates, calculationPeriod));
		}
		
		@Override
		protected MapperS<Date> fixingDate(FloatingRate rateDef, ResetDates resetDates, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(determineFixingDate.evaluate(resetDates, resetDate(rateDef, resetDates, calculationPeriod).get()));
		}
		
		@Override
		protected MapperS<BigDecimal> observedRate(FloatingRate rateDef, ResetDates resetDates, CalculationPeriodBase calculationPeriod) {
			return MapperS.of(indexValueObservation.evaluate(fixingDate(rateDef, resetDates, calculationPeriod).get(), MapperS.of(rateDef).<ReferenceWithMetaFloatingRateOption>map("getRateOption", floatingRateBase -> floatingRateBase.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue()).get()));
		}
	}
}
