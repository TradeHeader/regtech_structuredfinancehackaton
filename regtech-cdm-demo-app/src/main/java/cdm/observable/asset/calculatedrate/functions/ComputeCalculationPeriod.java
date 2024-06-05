package cdm.observable.asset.calculatedrate.functions;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.RelativeDateOffset;
import cdm.base.datetime.functions.AddBusinessDays;
import cdm.base.datetime.functions.GetAllBusinessCenters;
import cdm.observable.asset.calculatedrate.ObservationPeriodDatesEnum;
import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.CalculationPeriodBase.CalculationPeriodBaseBuilder;
import cdm.product.common.schedule.ResetDates;
import cdm.product.common.schedule.ResetRelativeToEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * Determine the calculation period to use for computing the calculated rate (it may not be the same as the normal calculation period, for instance if the rate is set in advance.
 * @version ${project.version}
 */
public class ComputeCalculationPeriod implements RosettaFunction {
	
	@Inject protected ComputeCalculationPeriod.ComputeCalculationPeriodSET_IN_ADVANCE computeCalculationPeriodSetInAdvance;
	@Inject protected ComputeCalculationPeriod.ComputeCalculationPeriodSTANDARD computeCalculationPeriodStandard;
	@Inject protected ComputeCalculationPeriod.ComputeCalculationPeriodFIXING_DATE computeCalculationPeriodFixingDate;
	
	public CalculationPeriodBase evaluate(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
		switch (calculateRelativeTo) {
			case SET_IN_ADVANCE:
				return computeCalculationPeriodSetInAdvance.evaluate(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates);
			case STANDARD:
				return computeCalculationPeriodStandard.evaluate(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates);
			case FIXING_DATE:
				return computeCalculationPeriodFixingDate.evaluate(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates);
			default:
				throw new IllegalArgumentException("Enum value not implemented: " + calculateRelativeTo);
		}
	}
	
	@ImplementedBy(ComputeCalculationPeriodSET_IN_ADVANCE.ComputeCalculationPeriodSET_IN_ADVANCEDefault.class)
	public static abstract class ComputeCalculationPeriodSET_IN_ADVANCE implements RosettaFunction {
		
		@Inject protected ModelObjectValidator objectValidator;
	
		/**
		* @param calculationPeriod The current calculation period for which the rate is neede.
		* @param priorCalculationPeriod The prior actual or deemed calculation period, if neede.
		* @param calculateRelativeTo How the calculation is done with respect to the base calculation perio.
		* @param resetDates The resetDates structure, if needed, e.g. for fallback rate.
		* @return result The calculation period over which the calculated rate should be calculate.
		*/
		public CalculationPeriodBase evaluate(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
			CalculationPeriodBase.CalculationPeriodBaseBuilder resultBuilder = doEvaluate(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates);
			
			final CalculationPeriodBase result;
			if (resultBuilder == null) {
				result = null;
			} else {
				result = resultBuilder.build();
				objectValidator.validate(CalculationPeriodBase.class, result);
			}
			
			return result;
		}
	
		protected abstract CalculationPeriodBase.CalculationPeriodBaseBuilder doEvaluate(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates);
	
		public static class ComputeCalculationPeriodSET_IN_ADVANCEDefault extends ComputeCalculationPeriodSET_IN_ADVANCE {
			@Override
			protected CalculationPeriodBase.CalculationPeriodBaseBuilder doEvaluate(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
				CalculationPeriodBase.CalculationPeriodBaseBuilder result = CalculationPeriodBase.builder();
				return assignOutput(result, calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates);
			}
			
			protected CalculationPeriodBase.CalculationPeriodBaseBuilder assignOutput(CalculationPeriodBase.CalculationPeriodBaseBuilder result, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
				result = toBuilder(priorCalculationPeriod);
				
				return Optional.ofNullable(result)
					.map(o -> o.prune())
					.orElse(null);
			}
		}
	}
	@ImplementedBy(ComputeCalculationPeriodSTANDARD.ComputeCalculationPeriodSTANDARDDefault.class)
	public static abstract class ComputeCalculationPeriodSTANDARD implements RosettaFunction {
		
		@Inject protected ModelObjectValidator objectValidator;
	
		/**
		* @param calculationPeriod The current calculation period for which the rate is neede.
		* @param priorCalculationPeriod The prior actual or deemed calculation period, if neede.
		* @param calculateRelativeTo How the calculation is done with respect to the base calculation perio.
		* @param resetDates The resetDates structure, if needed, e.g. for fallback rate.
		* @return result The calculation period over which the calculated rate should be calculate.
		*/
		public CalculationPeriodBase evaluate(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
			CalculationPeriodBase.CalculationPeriodBaseBuilder resultBuilder = doEvaluate(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates);
			
			final CalculationPeriodBase result;
			if (resultBuilder == null) {
				result = null;
			} else {
				result = resultBuilder.build();
				objectValidator.validate(CalculationPeriodBase.class, result);
			}
			
			return result;
		}
	
		protected abstract CalculationPeriodBase.CalculationPeriodBaseBuilder doEvaluate(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates);
	
		public static class ComputeCalculationPeriodSTANDARDDefault extends ComputeCalculationPeriodSTANDARD {
			@Override
			protected CalculationPeriodBase.CalculationPeriodBaseBuilder doEvaluate(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
				CalculationPeriodBase.CalculationPeriodBaseBuilder result = CalculationPeriodBase.builder();
				return assignOutput(result, calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates);
			}
			
			protected CalculationPeriodBase.CalculationPeriodBaseBuilder assignOutput(CalculationPeriodBase.CalculationPeriodBaseBuilder result, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
				result = toBuilder(calculationPeriod);
				
				return Optional.ofNullable(result)
					.map(o -> o.prune())
					.orElse(null);
			}
		}
	}
	@ImplementedBy(ComputeCalculationPeriodFIXING_DATE.ComputeCalculationPeriodFIXING_DATEDefault.class)
	public static abstract class ComputeCalculationPeriodFIXING_DATE implements RosettaFunction {
		
		@Inject protected ModelObjectValidator objectValidator;
		
		// RosettaFunction dependencies
		//
		@Inject protected AddBusinessDays addBusinessDays;
		@Inject protected GetAllBusinessCenters getAllBusinessCenters;
	
		/**
		* @param calculationPeriod The current calculation period for which the rate is neede.
		* @param priorCalculationPeriod The prior actual or deemed calculation period, if neede.
		* @param calculateRelativeTo How the calculation is done with respect to the base calculation perio.
		* @param resetDates The resetDates structure, if needed, e.g. for fallback rate.
		* @return result The calculation period over which the calculated rate should be calculate.
		*/
		public CalculationPeriodBase evaluate(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
			CalculationPeriodBase.CalculationPeriodBaseBuilder resultBuilder = doEvaluate(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates);
			
			final CalculationPeriodBase result;
			if (resultBuilder == null) {
				result = null;
			} else {
				result = resultBuilder.build();
				objectValidator.validate(CalculationPeriodBase.class, result);
			}
			
			return result;
		}
	
		protected abstract CalculationPeriodBase.CalculationPeriodBaseBuilder doEvaluate(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates);
	
		protected abstract MapperS<ResetRelativeToEnum> resetRelativeTo(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates);
	
		protected abstract MapperS<Boolean> isStart(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates);
	
		protected abstract MapperS<? extends CalculationPeriodBase> calcPd(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates);
	
		protected abstract MapperS<Integer> fixingOffsetDays(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates);
	
		protected abstract MapperC<BusinessCenterEnum> businessCenters(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates);
	
		protected abstract MapperS<Date> endDate(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates);
	
		protected abstract MapperS<Date> startDate(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates);
	
		public static class ComputeCalculationPeriodFIXING_DATEDefault extends ComputeCalculationPeriodFIXING_DATE {
			@Override
			protected CalculationPeriodBase.CalculationPeriodBaseBuilder doEvaluate(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
				CalculationPeriodBase.CalculationPeriodBaseBuilder result = CalculationPeriodBase.builder();
				return assignOutput(result, calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates);
			}
			
			protected CalculationPeriodBase.CalculationPeriodBaseBuilder assignOutput(CalculationPeriodBase.CalculationPeriodBaseBuilder result, CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
				result
					.setAdjustedEndDate(endDate(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates).get());
				
				result
					.setAdjustedStartDate(startDate(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates).get());
				
				return Optional.ofNullable(result)
					.map(o -> o.prune())
					.orElse(null);
			}
			
			@Override
			protected MapperS<ResetRelativeToEnum> resetRelativeTo(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
				return MapperS.of(resetDates).<ResetRelativeToEnum>map("getResetRelativeTo", _resetDates -> _resetDates.getResetRelativeTo());
			}
			
			@Override
			protected MapperS<Boolean> isStart(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
				return areEqual(resetRelativeTo(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates), MapperS.of(ResetRelativeToEnum.CALCULATION_PERIOD_START_DATE), CardinalityOperator.All).asMapper();
			}
			
			@Override
			protected MapperS<? extends CalculationPeriodBase> calcPd(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
				if (isStart(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates).getOrDefault(false)) {
					return MapperS.of(priorCalculationPeriod);
				}
				return MapperS.of(calculationPeriod);
			}
			
			@Override
			protected MapperS<Integer> fixingOffsetDays(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
				return MapperS.of(resetDates).<RelativeDateOffset>map("getFixingDates", _resetDates -> _resetDates.getFixingDates()).<Integer>map("getPeriodMultiplier", period -> period.getPeriodMultiplier());
			}
			
			@Override
			protected MapperC<BusinessCenterEnum> businessCenters(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
				return MapperC.<BusinessCenterEnum>of(getAllBusinessCenters.evaluate(MapperS.of(resetDates).<BusinessDayAdjustments>map("getResetDatesAdjustments", _resetDates -> _resetDates.getResetDatesAdjustments()).<BusinessCenters>map("getBusinessCenters", businessDayAdjustments -> businessDayAdjustments.getBusinessCenters()).get()));
			}
			
			@Override
			protected MapperS<Date> endDate(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
				return MapperS.of(addBusinessDays.evaluate(calcPd(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates).<Date>map("getAdjustedEndDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedEndDate()).get(), fixingOffsetDays(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates).get(), businessCenters(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates).getMulti()));
			}
			
			@Override
			protected MapperS<Date> startDate(CalculationPeriodBase calculationPeriod, CalculationPeriodBase priorCalculationPeriod, ObservationPeriodDatesEnum calculateRelativeTo, ResetDates resetDates) {
				return MapperS.of(addBusinessDays.evaluate(calcPd(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates).<Date>map("getAdjustedStartDate", calculationPeriodBase -> calculationPeriodBase.getAdjustedStartDate()).get(), fixingOffsetDays(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates).get(), businessCenters(calculationPeriod, priorCalculationPeriod, calculateRelativeTo, resetDates).getMulti()));
			}
		}
	}
}
