package cdm.product.asset.floatingrate.functions;

import cdm.base.math.functions.Max;
import cdm.observable.asset.Price;
import cdm.product.asset.NegativeInterestRateTreatmentEnum;
import cdm.product.asset.RateTreatmentEnum;
import cdm.product.asset.floatingrate.FloatingRateProcessingDetails;
import cdm.product.asset.floatingrate.FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder;
import cdm.product.asset.floatingrate.FloatingRateProcessingParameters;
import cdm.product.common.schedule.CalculationPeriodBase;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ApplyFloatingRateProcessing.ApplyFloatingRateProcessingDefault.class)
public abstract class ApplyFloatingRateProcessing implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected ApplyFloatingRatePostSpreadProcessing applyFloatingRatePostSpreadProcessing;
	@Inject protected ApplyUSRateTreatment applyUSRateTreatment;
	@Inject protected Max max;

	/**
	* @param processing THe parameters to be used for processing, such as multipliers, spreads, cap rates, etc.
	* @param rawRate The floating rate prior to treatment, either a single term rate, or a calculated rate such as an OIS or lookback compounded rate.
	* @param calculationPeriod The calculation period for with the processing need to be performed.
	* @param isInitialPeriod Is this the initial calculation period of the payout?
	* @return details Results are details of the rate treatment.
	*/
	public FloatingRateProcessingDetails evaluate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
		FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder detailsBuilder = doEvaluate(processing, rawRate, calculationPeriod, isInitialPeriod);
		
		final FloatingRateProcessingDetails details;
		if (detailsBuilder == null) {
			details = null;
		} else {
			details = detailsBuilder.build();
			objectValidator.validate(FloatingRateProcessingDetails.class, details);
		}
		
		return details;
	}

	protected abstract FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder doEvaluate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	protected abstract MapperS<BigDecimal> multiplier(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	protected abstract MapperS<BigDecimal> multiplied(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	protected abstract MapperS<BigDecimal> multipliedRate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	protected abstract MapperS<BigDecimal> treatedRate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	protected abstract MapperS<NegativeInterestRateTreatmentEnum> negativeTreatment(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	protected abstract MapperS<BigDecimal> negativeTreatedRate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	protected abstract MapperS<BigDecimal> spreadRate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	protected abstract MapperS<BigDecimal> added(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	protected abstract MapperS<BigDecimal> ratePlusSpread(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	protected abstract MapperS<BigDecimal> negativeTreatedRatePlusSpread(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	protected abstract MapperS<Boolean> doInitialRate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	protected abstract MapperS<BigDecimal> initialRate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	protected abstract MapperS<BigDecimal> initialRatePluSpread(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	protected abstract MapperS<BigDecimal> initialRatePlusSpread(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod);

	public static class ApplyFloatingRateProcessingDefault extends ApplyFloatingRateProcessing {
		@Override
		protected FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder doEvaluate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder details = FloatingRateProcessingDetails.builder();
			return assignOutput(details, processing, rawRate, calculationPeriod, isInitialPeriod);
		}
		
		protected FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder assignOutput(FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder details, FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			details
				.setProcessingParameters(processing);
			
			details
				.setRawRate(rawRate);
			
			final BigDecimal ifThenElseResult0;
			if (areEqual(doInitialRate(processing, rawRate, calculationPeriod, isInitialPeriod), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				ifThenElseResult0 = initialRatePluSpread(processing, rawRate, calculationPeriod, isInitialPeriod).get();
			} else {
				ifThenElseResult0 = applyFloatingRatePostSpreadProcessing.evaluate(ratePlusSpread(processing, rawRate, calculationPeriod, isInitialPeriod).get(), processing);
			}
			details
				.setProcessedRate(ifThenElseResult0);
			
			final BigDecimal ifThenElseResult1;
			if (areEqual(doInitialRate(processing, rawRate, calculationPeriod, isInitialPeriod), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
				ifThenElseResult1 = initialRate(processing, rawRate, calculationPeriod, isInitialPeriod).get();
			} else {
				ifThenElseResult1 = applyFloatingRatePostSpreadProcessing.evaluate(negativeTreatedRate(processing, rawRate, calculationPeriod, isInitialPeriod).get(), processing);
			}
			details
				.setSpreadExclusiveRate(ifThenElseResult1);
			
			return Optional.ofNullable(details)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<BigDecimal> multiplier(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			return MapperS.of(processing).<BigDecimal>map("getMultiplier", floatingRateProcessingParameters -> floatingRateProcessingParameters.getMultiplier());
		}
		
		@Override
		protected MapperS<BigDecimal> multiplied(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(MapperS.of(rawRate), multiplier(processing, rawRate, calculationPeriod, isInitialPeriod));
		}
		
		@Override
		protected MapperS<BigDecimal> multipliedRate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			if (exists(multiplier(processing, rawRate, calculationPeriod, isInitialPeriod)).getOrDefault(false)) {
				return multiplied(processing, rawRate, calculationPeriod, isInitialPeriod);
			}
			return MapperS.of(rawRate);
		}
		
		@Override
		protected MapperS<BigDecimal> treatedRate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(MapperS.of(applyUSRateTreatment.evaluate(multipliedRate(processing, rawRate, calculationPeriod, isInitialPeriod).get(), MapperS.of(processing).<RateTreatmentEnum>map("getTreatment", floatingRateProcessingParameters -> floatingRateProcessingParameters.getTreatment()).get(), calculationPeriod)), MapperS.of(new BigDecimal("1.0")));
		}
		
		@Override
		protected MapperS<NegativeInterestRateTreatmentEnum> negativeTreatment(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			return MapperS.of(processing).<NegativeInterestRateTreatmentEnum>map("getNegativeTreatment", floatingRateProcessingParameters -> floatingRateProcessingParameters.getNegativeTreatment());
		}
		
		@Override
		protected MapperS<BigDecimal> negativeTreatedRate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			if (areEqual(negativeTreatment(processing, rawRate, calculationPeriod, isInitialPeriod), MapperS.of(NegativeInterestRateTreatmentEnum.ZERO_INTEREST_RATE_EXCLUDING_SPREAD_METHOD), CardinalityOperator.All).getOrDefault(false)) {
				return MapperS.of(max.evaluate(new BigDecimal("0.0"), treatedRate(processing, rawRate, calculationPeriod, isInitialPeriod).get()));
			}
			return treatedRate(processing, rawRate, calculationPeriod, isInitialPeriod);
		}
		
		@Override
		protected MapperS<BigDecimal> spreadRate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			return MapperS.of(processing).<BigDecimal>map("getSpread", floatingRateProcessingParameters -> floatingRateProcessingParameters.getSpread());
		}
		
		@Override
		protected MapperS<BigDecimal> added(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>add(negativeTreatedRate(processing, rawRate, calculationPeriod, isInitialPeriod), spreadRate(processing, rawRate, calculationPeriod, isInitialPeriod));
		}
		
		@Override
		protected MapperS<BigDecimal> ratePlusSpread(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			if (exists(spreadRate(processing, rawRate, calculationPeriod, isInitialPeriod)).getOrDefault(false)) {
				return added(processing, rawRate, calculationPeriod, isInitialPeriod);
			}
			return negativeTreatedRate(processing, rawRate, calculationPeriod, isInitialPeriod);
		}
		
		@Override
		protected MapperS<BigDecimal> negativeTreatedRatePlusSpread(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			if (areEqual(negativeTreatment(processing, rawRate, calculationPeriod, isInitialPeriod), MapperS.of(NegativeInterestRateTreatmentEnum.ZERO_INTEREST_RATE_METHOD), CardinalityOperator.All).getOrDefault(false)) {
				return MapperS.of(max.evaluate(new BigDecimal("0.0"), ratePlusSpread(processing, rawRate, calculationPeriod, isInitialPeriod).get()));
			}
			return ratePlusSpread(processing, rawRate, calculationPeriod, isInitialPeriod);
		}
		
		@Override
		protected MapperS<Boolean> doInitialRate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			if (areEqual(MapperS.of(isInitialPeriod), MapperS.of(true), CardinalityOperator.All).and(exists(MapperS.of(processing).<Price>map("getInitialRate", floatingRateProcessingParameters -> floatingRateProcessingParameters.getInitialRate()))).getOrDefault(false)) {
				return MapperS.of(true);
			}
			return MapperS.of(false);
		}
		
		@Override
		protected MapperS<BigDecimal> initialRate(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			return MapperS.of(processing).<Price>map("getInitialRate", floatingRateProcessingParameters -> floatingRateProcessingParameters.getInitialRate()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue());
		}
		
		@Override
		protected MapperS<BigDecimal> initialRatePluSpread(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			return MapperMaths.<BigDecimal, BigDecimal, BigDecimal>add(initialRate(processing, rawRate, calculationPeriod, isInitialPeriod), spreadRate(processing, rawRate, calculationPeriod, isInitialPeriod));
		}
		
		@Override
		protected MapperS<BigDecimal> initialRatePlusSpread(FloatingRateProcessingParameters processing, BigDecimal rawRate, CalculationPeriodBase calculationPeriod, Boolean isInitialPeriod) {
			if (exists(spreadRate(processing, rawRate, calculationPeriod, isInitialPeriod)).getOrDefault(false)) {
				return initialRatePluSpread(processing, rawRate, calculationPeriod, isInitialPeriod);
			}
			return initialRate(processing, rawRate, calculationPeriod, isInitialPeriod);
		}
	}
}
