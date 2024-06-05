package cdm.product.asset.floatingrate.functions;

import cdm.base.datetime.daycount.DayCountFractionEnum;
import cdm.base.datetime.daycount.metafields.FieldWithMetaDayCountFractionEnum;
import cdm.observable.asset.FloatingRateOption;
import cdm.observable.asset.calculatedrate.CalculationMethodEnum;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.calculatedrate.functions.EvaluateCalculatedRate;
import cdm.observable.asset.metafields.ReferenceWithMetaFloatingRateOption;
import cdm.product.asset.FloatingRateSpecification;
import cdm.product.asset.InterestRatePayout;
import cdm.product.asset.RateSpecification;
import cdm.product.asset.calculation.functions.Create_CalculationPeriodBase;
import cdm.product.asset.floatingrate.FloatingRateIndexProcessingTypeEnum;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder;
import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.ResetDates;
import cdm.product.common.schedule.functions.CalculationPeriod;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Optional;
import javax.inject.Inject;


/**
 * Entry point for the function that performs the floating rate resetting operation.  There are different variations depending on the processing type (e.g. screen rate, OIS, modular calculated rate. .
 * @version ${project.version}
 */
public class ProcessFloatingRateReset implements RosettaFunction {
	
	@Inject protected ProcessFloatingRateReset.ProcessFloatingRateResetSCREEN processFloatingRateResetScreen;
	@Inject protected ProcessFloatingRateReset.ProcessFloatingRateResetMODULAR processFloatingRateResetModular;
	@Inject protected ProcessFloatingRateReset.ProcessFloatingRateResetOIS processFloatingRateResetOIS;
	@Inject protected ProcessFloatingRateReset.ProcessFloatingRateResetOVERNIGHT_AVG processFloatingRateResetOvernightAvg;
	@Inject protected ProcessFloatingRateReset.ProcessFloatingRateResetCOMPOUND_INDEX processFloatingRateResetCompoundIndex;
	
	public FloatingRateSettingDetails evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
		switch (processingType) {
			case SCREEN:
				return processFloatingRateResetScreen.evaluate(interestRatePayout, calcPeriod, processingType);
			case MODULAR:
				return processFloatingRateResetModular.evaluate(interestRatePayout, calcPeriod, processingType);
			case OIS:
				return processFloatingRateResetOIS.evaluate(interestRatePayout, calcPeriod, processingType);
			case OVERNIGHT_AVG:
				return processFloatingRateResetOvernightAvg.evaluate(interestRatePayout, calcPeriod, processingType);
			case COMPOUND_INDEX:
				return processFloatingRateResetCompoundIndex.evaluate(interestRatePayout, calcPeriod, processingType);
			default:
				throw new IllegalArgumentException("Enum value not implemented: " + processingType);
		}
	}
	
	@ImplementedBy(ProcessFloatingRateResetSCREEN.ProcessFloatingRateResetSCREENDefault.class)
	public static abstract class ProcessFloatingRateResetSCREEN implements RosettaFunction {
		
		@Inject protected ModelObjectValidator objectValidator;
		
		// RosettaFunction dependencies
		//
		@Inject protected EvaluateScreenRate evaluateScreenRate;
	
		/**
		* @param interestRatePayout Floating rate stream definition.
		* @param calcPeriod The calculation period for which you want the rate.
		* @param processingType The rate processing type (e.g. Screen Rate, OIS); this drives how the resetting calculation is done. .
		* @return floatingRate Details of the rate observation/calculation.
		*/
		public FloatingRateSettingDetails evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
			FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRateBuilder = doEvaluate(interestRatePayout, calcPeriod, processingType);
			
			final FloatingRateSettingDetails floatingRate;
			if (floatingRateBuilder == null) {
				floatingRate = null;
			} else {
				floatingRate = floatingRateBuilder.build();
				objectValidator.validate(FloatingRateSettingDetails.class, floatingRate);
			}
			
			return floatingRate;
		}
	
		protected abstract FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends ResetDates> resetDates(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends FloatingRateSpecification> rateDef(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		public static class ProcessFloatingRateResetSCREENDefault extends ProcessFloatingRateResetSCREEN {
			@Override
			protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRate = FloatingRateSettingDetails.builder();
				return assignOutput(floatingRate, interestRatePayout, calcPeriod, processingType);
			}
			
			protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder assignOutput(FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRate, InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				floatingRate = toBuilder(evaluateScreenRate.evaluate(rateDef(interestRatePayout, calcPeriod, processingType).get(), resetDates(interestRatePayout, calcPeriod, processingType).get(), calcPeriod));
				
				return Optional.ofNullable(floatingRate)
					.map(o -> o.prune())
					.orElse(null);
			}
			
			@Override
			protected MapperS<? extends ResetDates> resetDates(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return MapperS.of(interestRatePayout).<ResetDates>map("getResetDates", _interestRatePayout -> _interestRatePayout.getResetDates());
			}
			
			@Override
			protected MapperS<? extends FloatingRateSpecification> rateDef(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate());
			}
		}
	}
	@ImplementedBy(ProcessFloatingRateResetMODULAR.ProcessFloatingRateResetMODULARDefault.class)
	public static abstract class ProcessFloatingRateResetMODULAR implements RosettaFunction {
		
		@Inject protected ModelObjectValidator objectValidator;
		
		// RosettaFunction dependencies
		//
		@Inject protected CalculationPeriod calculationPeriod;
		@Inject protected Create_CalculationPeriodBase create_CalculationPeriodBase;
		@Inject protected EvaluateCalculatedRate evaluateCalculatedRate;
	
		/**
		* @param interestRatePayout Floating rate stream definition.
		* @param calcPeriod The calculation period for which you want the rate.
		* @param processingType The rate processing type (e.g. Screen Rate, OIS); this drives how the resetting calculation is done. .
		* @return floatingRate Details of the rate observation/calculation.
		*/
		public FloatingRateSettingDetails evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
			FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRateBuilder = doEvaluate(interestRatePayout, calcPeriod, processingType);
			
			final FloatingRateSettingDetails floatingRate;
			if (floatingRateBuilder == null) {
				floatingRate = null;
			} else {
				floatingRate = floatingRateBuilder.build();
				objectValidator.validate(FloatingRateSettingDetails.class, floatingRate);
			}
			
			return floatingRate;
		}
	
		protected abstract FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends FloatingRateSpecification> rateDef(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends ResetDates> resetDates(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<DayCountFractionEnum> dayCount(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends FloatingRateOption> fro(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends FloatingRateCalculationParameters> calcParams(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends CalculationPeriodBase> priorCalculationPeriod(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		public static class ProcessFloatingRateResetMODULARDefault extends ProcessFloatingRateResetMODULAR {
			@Override
			protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRate = FloatingRateSettingDetails.builder();
				return assignOutput(floatingRate, interestRatePayout, calcPeriod, processingType);
			}
			
			protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder assignOutput(FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRate, InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				floatingRate = toBuilder(evaluateCalculatedRate.evaluate(fro(interestRatePayout, calcPeriod, processingType).get(), calcParams(interestRatePayout, calcPeriod, processingType).get(), resetDates(interestRatePayout, calcPeriod, processingType).get(), calcPeriod, priorCalculationPeriod(interestRatePayout, calcPeriod, processingType).get(), dayCount(interestRatePayout, calcPeriod, processingType).get()));
				
				return Optional.ofNullable(floatingRate)
					.map(o -> o.prune())
					.orElse(null);
			}
			
			@Override
			protected MapperS<? extends FloatingRateSpecification> rateDef(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate());
			}
			
			@Override
			protected MapperS<? extends ResetDates> resetDates(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return MapperS.of(interestRatePayout).<ResetDates>map("getResetDates", _interestRatePayout -> _interestRatePayout.getResetDates());
			}
			
			@Override
			protected MapperS<DayCountFractionEnum> dayCount(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return MapperS.of(interestRatePayout).<FieldWithMetaDayCountFractionEnum>map("getDayCountFraction", _interestRatePayout -> _interestRatePayout.getDayCountFraction()).<DayCountFractionEnum>map("getValue", _f->_f.getValue());
			}
			
			@Override
			protected MapperS<? extends FloatingRateOption> fro(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return rateDef(interestRatePayout, calcPeriod, processingType).<ReferenceWithMetaFloatingRateOption>map("getRateOption", floatingRateBase -> floatingRateBase.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue());
			}
			
			@Override
			protected MapperS<? extends FloatingRateCalculationParameters> calcParams(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return rateDef(interestRatePayout, calcPeriod, processingType).<FloatingRateCalculationParameters>map("getCalculationParameters", floatingRate -> floatingRate.getCalculationParameters());
			}
			
			@Override
			protected MapperS<? extends CalculationPeriodBase> priorCalculationPeriod(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return MapperS.of(create_CalculationPeriodBase.evaluate(calculationPeriod.evaluate(MapperS.of(interestRatePayout).<CalculationPeriodDates>map("getCalculationPeriodDates", _interestRatePayout -> _interestRatePayout.getCalculationPeriodDates()).get(), MapperS.of(calcPeriod).<Date>map("getAdjustedStartDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedStartDate()).get())));
			}
		}
	}
	@ImplementedBy(ProcessFloatingRateResetOIS.ProcessFloatingRateResetOISDefault.class)
	public static abstract class ProcessFloatingRateResetOIS implements RosettaFunction {
		
		@Inject protected ModelObjectValidator objectValidator;
		
		// RosettaFunction dependencies
		//
		@Inject protected EvaluateCalculatedRate evaluateCalculatedRate;
		@Inject protected GetCalculatedFROCalculationParameters getCalculatedFROCalculationParameters;
	
		/**
		* @param interestRatePayout Floating rate stream definition.
		* @param calcPeriod The calculation period for which you want the rate.
		* @param processingType The rate processing type (e.g. Screen Rate, OIS); this drives how the resetting calculation is done. .
		* @return floatingRate Details of the rate observation/calculation.
		*/
		public FloatingRateSettingDetails evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
			FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRateBuilder = doEvaluate(interestRatePayout, calcPeriod, processingType);
			
			final FloatingRateSettingDetails floatingRate;
			if (floatingRateBuilder == null) {
				floatingRate = null;
			} else {
				floatingRate = floatingRateBuilder.build();
				objectValidator.validate(FloatingRateSettingDetails.class, floatingRate);
			}
			
			return floatingRate;
		}
	
		protected abstract FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends FloatingRateSpecification> rateDef(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends ResetDates> resetDates(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<DayCountFractionEnum> dayCount(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends FloatingRateOption> fro(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends FloatingRateCalculationParameters> calcParams(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		public static class ProcessFloatingRateResetOISDefault extends ProcessFloatingRateResetOIS {
			@Override
			protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRate = FloatingRateSettingDetails.builder();
				return assignOutput(floatingRate, interestRatePayout, calcPeriod, processingType);
			}
			
			protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder assignOutput(FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRate, InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				floatingRate = toBuilder(evaluateCalculatedRate.evaluate(fro(interestRatePayout, calcPeriod, processingType).get(), calcParams(interestRatePayout, calcPeriod, processingType).get(), resetDates(interestRatePayout, calcPeriod, processingType).get(), calcPeriod, calcPeriod, dayCount(interestRatePayout, calcPeriod, processingType).get()));
				
				return Optional.ofNullable(floatingRate)
					.map(o -> o.prune())
					.orElse(null);
			}
			
			@Override
			protected MapperS<? extends FloatingRateSpecification> rateDef(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate());
			}
			
			@Override
			protected MapperS<? extends ResetDates> resetDates(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return MapperS.of(interestRatePayout).<ResetDates>map("getResetDates", _interestRatePayout -> _interestRatePayout.getResetDates());
			}
			
			@Override
			protected MapperS<DayCountFractionEnum> dayCount(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return MapperS.of(interestRatePayout).<FieldWithMetaDayCountFractionEnum>map("getDayCountFraction", _interestRatePayout -> _interestRatePayout.getDayCountFraction()).<DayCountFractionEnum>map("getValue", _f->_f.getValue());
			}
			
			@Override
			protected MapperS<? extends FloatingRateOption> fro(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return rateDef(interestRatePayout, calcPeriod, processingType).<ReferenceWithMetaFloatingRateOption>map("getRateOption", floatingRateBase -> floatingRateBase.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue());
			}
			
			@Override
			protected MapperS<? extends FloatingRateCalculationParameters> calcParams(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return MapperS.of(getCalculatedFROCalculationParameters.evaluate(resetDates(interestRatePayout, calcPeriod, processingType).get(), CalculationMethodEnum.COMPOUNDING));
			}
		}
	}
	@ImplementedBy(ProcessFloatingRateResetOVERNIGHT_AVG.ProcessFloatingRateResetOVERNIGHT_AVGDefault.class)
	public static abstract class ProcessFloatingRateResetOVERNIGHT_AVG implements RosettaFunction {
		
		@Inject protected ModelObjectValidator objectValidator;
		
		// RosettaFunction dependencies
		//
		@Inject protected EvaluateCalculatedRate evaluateCalculatedRate;
		@Inject protected GetCalculatedFROCalculationParameters getCalculatedFROCalculationParameters;
	
		/**
		* @param interestRatePayout Floating rate stream definition.
		* @param calcPeriod The calculation period for which you want the rate.
		* @param processingType The rate processing type (e.g. Screen Rate, OIS); this drives how the resetting calculation is done. .
		* @return floatingRate Details of the rate observation/calculation.
		*/
		public FloatingRateSettingDetails evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
			FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRateBuilder = doEvaluate(interestRatePayout, calcPeriod, processingType);
			
			final FloatingRateSettingDetails floatingRate;
			if (floatingRateBuilder == null) {
				floatingRate = null;
			} else {
				floatingRate = floatingRateBuilder.build();
				objectValidator.validate(FloatingRateSettingDetails.class, floatingRate);
			}
			
			return floatingRate;
		}
	
		protected abstract FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends FloatingRateSpecification> rateDef(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends ResetDates> resetDates(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<DayCountFractionEnum> dayCount(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends FloatingRateOption> rateOption(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		protected abstract MapperS<? extends FloatingRateCalculationParameters> calcParams(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		public static class ProcessFloatingRateResetOVERNIGHT_AVGDefault extends ProcessFloatingRateResetOVERNIGHT_AVG {
			@Override
			protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRate = FloatingRateSettingDetails.builder();
				return assignOutput(floatingRate, interestRatePayout, calcPeriod, processingType);
			}
			
			protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder assignOutput(FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRate, InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				floatingRate = toBuilder(evaluateCalculatedRate.evaluate(rateOption(interestRatePayout, calcPeriod, processingType).get(), calcParams(interestRatePayout, calcPeriod, processingType).get(), resetDates(interestRatePayout, calcPeriod, processingType).get(), calcPeriod, calcPeriod, dayCount(interestRatePayout, calcPeriod, processingType).get()));
				
				return Optional.ofNullable(floatingRate)
					.map(o -> o.prune())
					.orElse(null);
			}
			
			@Override
			protected MapperS<? extends FloatingRateSpecification> rateDef(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return MapperS.of(interestRatePayout).<RateSpecification>map("getRateSpecification", _interestRatePayout -> _interestRatePayout.getRateSpecification()).<FloatingRateSpecification>map("getFloatingRate", rateSpecification -> rateSpecification.getFloatingRate());
			}
			
			@Override
			protected MapperS<? extends ResetDates> resetDates(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return MapperS.of(interestRatePayout).<ResetDates>map("getResetDates", _interestRatePayout -> _interestRatePayout.getResetDates());
			}
			
			@Override
			protected MapperS<DayCountFractionEnum> dayCount(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return MapperS.of(interestRatePayout).<FieldWithMetaDayCountFractionEnum>map("getDayCountFraction", _interestRatePayout -> _interestRatePayout.getDayCountFraction()).<DayCountFractionEnum>map("getValue", _f->_f.getValue());
			}
			
			@Override
			protected MapperS<? extends FloatingRateOption> rateOption(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return rateDef(interestRatePayout, calcPeriod, processingType).<ReferenceWithMetaFloatingRateOption>map("getRateOption", floatingRateBase -> floatingRateBase.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue());
			}
			
			@Override
			protected MapperS<? extends FloatingRateCalculationParameters> calcParams(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return MapperS.of(getCalculatedFROCalculationParameters.evaluate(resetDates(interestRatePayout, calcPeriod, processingType).get(), CalculationMethodEnum.AVERAGING));
			}
		}
	}
	@ImplementedBy(ProcessFloatingRateResetCOMPOUND_INDEX.ProcessFloatingRateResetCOMPOUND_INDEXDefault.class)
	public static abstract class ProcessFloatingRateResetCOMPOUND_INDEX implements RosettaFunction {
		
		@Inject protected ModelObjectValidator objectValidator;
	
		/**
		* @param interestRatePayout Floating rate stream definition.
		* @param calcPeriod The calculation period for which you want the rate.
		* @param processingType The rate processing type (e.g. Screen Rate, OIS); this drives how the resetting calculation is done. .
		* @return floatingRate Details of the rate observation/calculation.
		*/
		public FloatingRateSettingDetails evaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
			FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRateBuilder = doEvaluate(interestRatePayout, calcPeriod, processingType);
			
			final FloatingRateSettingDetails floatingRate;
			if (floatingRateBuilder == null) {
				floatingRate = null;
			} else {
				floatingRate = floatingRateBuilder.build();
				objectValidator.validate(FloatingRateSettingDetails.class, floatingRate);
			}
			
			return floatingRate;
		}
	
		protected abstract FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType);
	
		public static class ProcessFloatingRateResetCOMPOUND_INDEXDefault extends ProcessFloatingRateResetCOMPOUND_INDEX {
			@Override
			protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder doEvaluate(InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRate = FloatingRateSettingDetails.builder();
				return assignOutput(floatingRate, interestRatePayout, calcPeriod, processingType);
			}
			
			protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder assignOutput(FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRate, InterestRatePayout interestRatePayout, CalculationPeriodBase calcPeriod, FloatingRateIndexProcessingTypeEnum processingType) {
				return Optional.ofNullable(floatingRate)
					.map(o -> o.prune())
					.orElse(null);
			}
		}
	}
}
