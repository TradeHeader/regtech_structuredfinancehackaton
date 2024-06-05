package cdm.base.datetime.daycount.functions;

import cdm.base.datetime.daycount.DayCountFractionEnum;
import cdm.base.datetime.functions.DateDifference;
import cdm.base.datetime.functions.LeapYearDateDifference;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.IsLeapYear;
import com.rosetta.model.lib.functions.Min;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * The fraction of a year represented by a date range
 * @version ${project.version}
 */
public class YearFraction implements RosettaFunction {
	
	@Inject protected YearFraction.YearFraction_1_1 yearFraction_1_1;
	@Inject protected YearFraction.YearFractionACT_ACT_ISDA yearFractionACT_ACT_ISDA;
	@Inject protected YearFraction.YearFractionACT_ACT_ICMA yearFractionACT_ACT_ICMA;
	@Inject protected YearFraction.YearFractionACT_365_FIXED yearFractionACT_365_FIXED;
	@Inject protected YearFraction.YearFractionACT_360 yearFractionACT_360;
	@Inject protected YearFraction.YearFraction_30_360 yearFraction_30_360;
	@Inject protected YearFraction.YearFraction_30E_360 yearFraction_30E_360;
	@Inject protected YearFraction.YearFraction_30E_360_ISDA yearFraction_30E_360_ISDA;
	@Inject protected YearFraction.YearFractionACT_364 yearFractionACT_364;
	@Inject protected YearFraction.YearFractionACT_365L yearFractionACT_365L;
	@Inject protected YearFraction.YearFractionCAL_252 yearFractionCAL_252;
	
	public BigDecimal evaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
		switch (dayCountFractionEnum) {
			case _1_1:
				return yearFraction_1_1.evaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			case ACT_ACT_ISDA:
				return yearFractionACT_ACT_ISDA.evaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			case ACT_ACT_ICMA:
				return yearFractionACT_ACT_ICMA.evaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			case ACT_365_FIXED:
				return yearFractionACT_365_FIXED.evaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			case ACT_360:
				return yearFractionACT_360.evaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			case _30_360:
				return yearFraction_30_360.evaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			case _30E_360:
				return yearFraction_30E_360.evaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			case _30E_360_ISDA:
				return yearFraction_30E_360_ISDA.evaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			case ACT_364:
				return yearFractionACT_364.evaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			case ACT_365L:
				return yearFractionACT_365L.evaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			case CAL_252:
				return yearFractionCAL_252.evaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			default:
				throw new IllegalArgumentException("Enum value not implemented: " + dayCountFractionEnum);
		}
	}
	
	@ImplementedBy(YearFraction_1_1.YearFraction_1_1Default.class)
	public static abstract class YearFraction_1_1 implements RosettaFunction {
	
		/**
		* @param dayCountFractionEnum The day count fraction to use
		* @param startDate The start date of the range for which the year fraction is required
		* @param endDate The end date of the range for which the year fraction is required
		* @param terminationDate The termination date of the payout; this is needed for some day count fractions
		* @param periodsInYear The number of periods in a year in the payout; this is needed for some day count fractions
		* @return result The fraction of a year represented by period from the startDate to the endDate
		*/
		public BigDecimal evaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
			BigDecimal result = doEvaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			
			return result;
		}
	
		protected abstract BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		public static class YearFraction_1_1Default extends YearFraction_1_1 {
			@Override
			protected BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				BigDecimal result = null;
				return assignOutput(result, dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			}
			
			protected BigDecimal assignOutput(BigDecimal result, DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				result = new BigDecimal("1.0");
				
				return result;
			}
		}
	}
	@ImplementedBy(YearFractionACT_ACT_ISDA.YearFractionACT_ACT_ISDADefault.class)
	public static abstract class YearFractionACT_ACT_ISDA implements RosettaFunction {
		
		// RosettaFunction dependencies
		//
		@Inject protected DateDifference dateDifference;
		@Inject protected LeapYearDateDifference leapYearDateDifference;
	
		/**
		* @param dayCountFractionEnum The day count fraction to use
		* @param startDate The start date of the range for which the year fraction is required
		* @param endDate The end date of the range for which the year fraction is required
		* @param terminationDate The termination date of the payout; this is needed for some day count fractions
		* @param periodsInYear The number of periods in a year in the payout; this is needed for some day count fractions
		* @return result The fraction of a year represented by period from the startDate to the endDate
		*/
		public BigDecimal evaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
			BigDecimal result = doEvaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			
			return result;
		}
	
		protected abstract BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> daysInPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> daysInLeapYearPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> daysInNonLeapPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		public static class YearFractionACT_ACT_ISDADefault extends YearFractionACT_ACT_ISDA {
			@Override
			protected BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				BigDecimal result = null;
				return assignOutput(result, dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			}
			
			protected BigDecimal assignOutput(BigDecimal result, DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				result = MapperMaths.<BigDecimal, BigDecimal, BigDecimal>add(MapperMaths.<BigDecimal, Integer, Integer>divide(daysInNonLeapPeriod(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), MapperS.of(365)), MapperMaths.<BigDecimal, Integer, Integer>divide(daysInLeapYearPeriod(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), MapperS.of(366))).get();
				
				return result;
			}
			
			@Override
			protected MapperS<Integer> daysInPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(dateDifference.evaluate(startDate, endDate));
			}
			
			@Override
			protected MapperS<Integer> daysInLeapYearPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(leapYearDateDifference.evaluate(startDate, endDate));
			}
			
			@Override
			protected MapperS<Integer> daysInNonLeapPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperMaths.<Integer, Integer, Integer>subtract(daysInPeriod(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), daysInLeapYearPeriod(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear));
			}
		}
	}
	@ImplementedBy(YearFractionACT_ACT_ICMA.YearFractionACT_ACT_ICMADefault.class)
	public static abstract class YearFractionACT_ACT_ICMA implements RosettaFunction {
		
		// RosettaFunction dependencies
		//
		@Inject protected DateDifference dateDifference;
	
		/**
		* @param dayCountFractionEnum The day count fraction to use
		* @param startDate The start date of the range for which the year fraction is required
		* @param endDate The end date of the range for which the year fraction is required
		* @param terminationDate The termination date of the payout; this is needed for some day count fractions
		* @param periodsInYear The number of periods in a year in the payout; this is needed for some day count fractions
		* @return result The fraction of a year represented by period from the startDate to the endDate
		*/
		public BigDecimal evaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
			BigDecimal result = doEvaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			
			return result;
		}
	
		protected abstract BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> daysInPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		public static class YearFractionACT_ACT_ICMADefault extends YearFractionACT_ACT_ICMA {
			@Override
			protected BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				BigDecimal result = null;
				return assignOutput(result, dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			}
			
			protected BigDecimal assignOutput(BigDecimal result, DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				result = MapperMaths.<BigDecimal, Integer, Integer>divide(daysInPeriod(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), MapperMaths.<Integer, Integer, Integer>multiply(daysInPeriod(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), MapperS.of(periodsInYear))).get();
				
				return result;
			}
			
			@Override
			protected MapperS<Integer> daysInPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(dateDifference.evaluate(startDate, endDate));
			}
		}
	}
	@ImplementedBy(YearFractionACT_365_FIXED.YearFractionACT_365_FIXEDDefault.class)
	public static abstract class YearFractionACT_365_FIXED implements RosettaFunction {
		
		// RosettaFunction dependencies
		//
		@Inject protected DateDifference dateDifference;
	
		/**
		* @param dayCountFractionEnum The day count fraction to use
		* @param startDate The start date of the range for which the year fraction is required
		* @param endDate The end date of the range for which the year fraction is required
		* @param terminationDate The termination date of the payout; this is needed for some day count fractions
		* @param periodsInYear The number of periods in a year in the payout; this is needed for some day count fractions
		* @return result The fraction of a year represented by period from the startDate to the endDate
		*/
		public BigDecimal evaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
			BigDecimal result = doEvaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			
			return result;
		}
	
		protected abstract BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> daysInPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		public static class YearFractionACT_365_FIXEDDefault extends YearFractionACT_365_FIXED {
			@Override
			protected BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				BigDecimal result = null;
				return assignOutput(result, dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			}
			
			protected BigDecimal assignOutput(BigDecimal result, DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				result = MapperMaths.<BigDecimal, Integer, Integer>divide(daysInPeriod(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), MapperS.of(365)).get();
				
				return result;
			}
			
			@Override
			protected MapperS<Integer> daysInPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(dateDifference.evaluate(startDate, endDate));
			}
		}
	}
	@ImplementedBy(YearFractionACT_360.YearFractionACT_360Default.class)
	public static abstract class YearFractionACT_360 implements RosettaFunction {
		
		// RosettaFunction dependencies
		//
		@Inject protected DateDifference dateDifference;
	
		/**
		* @param dayCountFractionEnum The day count fraction to use
		* @param startDate The start date of the range for which the year fraction is required
		* @param endDate The end date of the range for which the year fraction is required
		* @param terminationDate The termination date of the payout; this is needed for some day count fractions
		* @param periodsInYear The number of periods in a year in the payout; this is needed for some day count fractions
		* @return result The fraction of a year represented by period from the startDate to the endDate
		*/
		public BigDecimal evaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
			BigDecimal result = doEvaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			
			return result;
		}
	
		protected abstract BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> daysInPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		public static class YearFractionACT_360Default extends YearFractionACT_360 {
			@Override
			protected BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				BigDecimal result = null;
				return assignOutput(result, dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			}
			
			protected BigDecimal assignOutput(BigDecimal result, DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				result = MapperMaths.<BigDecimal, Integer, Integer>divide(daysInPeriod(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), MapperS.of(360)).get();
				
				return result;
			}
			
			@Override
			protected MapperS<Integer> daysInPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(dateDifference.evaluate(startDate, endDate));
			}
		}
	}
	@ImplementedBy(YearFraction_30_360.YearFraction_30_360Default.class)
	public static abstract class YearFraction_30_360 implements RosettaFunction {
	
		/**
		* @param dayCountFractionEnum The day count fraction to use
		* @param startDate The start date of the range for which the year fraction is required
		* @param endDate The end date of the range for which the year fraction is required
		* @param terminationDate The termination date of the payout; this is needed for some day count fractions
		* @param periodsInYear The number of periods in a year in the payout; this is needed for some day count fractions
		* @return result The fraction of a year represented by period from the startDate to the endDate
		*/
		public BigDecimal evaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
			BigDecimal result = doEvaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			
			return result;
		}
	
		protected abstract BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> startYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> endYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> startMonth(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> endMonth(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> endDay(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> startDay(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		public static class YearFraction_30_360Default extends YearFraction_30_360 {
			@Override
			protected BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				BigDecimal result = null;
				return assignOutput(result, dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			}
			
			protected BigDecimal assignOutput(BigDecimal result, DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				result = MapperMaths.<BigDecimal, Integer, Integer>divide(MapperMaths.<Integer, Integer, Integer>add(MapperMaths.<Integer, Integer, Integer>add(MapperMaths.<Integer, Integer, Integer>multiply(MapperS.of(360), MapperMaths.<Integer, Integer, Integer>subtract(endYear(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), startYear(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear))), MapperMaths.<Integer, Integer, Integer>multiply(MapperS.of(30), MapperMaths.<Integer, Integer, Integer>subtract(endMonth(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), startMonth(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear)))), MapperMaths.<Integer, Integer, Integer>subtract(endDay(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), startDay(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear))), MapperS.of(360)).get();
				
				return result;
			}
			
			@Override
			protected MapperS<Integer> startYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(startDate).<Integer>map("Year", Date::getYear);
			}
			
			@Override
			protected MapperS<Integer> endYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(endDate).<Integer>map("Year", Date::getYear);
			}
			
			@Override
			protected MapperS<Integer> startMonth(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(startDate).<Integer>map("Month", Date::getMonth);
			}
			
			@Override
			protected MapperS<Integer> endMonth(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(endDate).<Integer>map("Month", Date::getMonth);
			}
			
			@Override
			protected MapperS<Integer> endDay(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				if (greaterThan(MapperS.of(startDate).<Integer>map("Day", Date::getDay), MapperS.of(29), CardinalityOperator.All).getOrDefault(false)) {
					return MapperS.of(new Min().execute(MapperS.of(endDate).<Integer>map("Day", Date::getDay).get(), 30));
				}
				return MapperS.of(endDate).<Integer>map("Day", Date::getDay);
			}
			
			@Override
			protected MapperS<Integer> startDay(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(new Min().execute(MapperS.of(startDate).<Integer>map("Day", Date::getDay).get(), 30));
			}
		}
	}
	@ImplementedBy(YearFraction_30E_360.YearFraction_30E_360Default.class)
	public static abstract class YearFraction_30E_360 implements RosettaFunction {
	
		/**
		* @param dayCountFractionEnum The day count fraction to use
		* @param startDate The start date of the range for which the year fraction is required
		* @param endDate The end date of the range for which the year fraction is required
		* @param terminationDate The termination date of the payout; this is needed for some day count fractions
		* @param periodsInYear The number of periods in a year in the payout; this is needed for some day count fractions
		* @return result The fraction of a year represented by period from the startDate to the endDate
		*/
		public BigDecimal evaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
			BigDecimal result = doEvaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			
			return result;
		}
	
		protected abstract BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> startYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> endYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> startMonth(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> endMonth(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> endDay(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> startDay(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		public static class YearFraction_30E_360Default extends YearFraction_30E_360 {
			@Override
			protected BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				BigDecimal result = null;
				return assignOutput(result, dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			}
			
			protected BigDecimal assignOutput(BigDecimal result, DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				result = MapperMaths.<BigDecimal, Integer, Integer>divide(MapperMaths.<Integer, Integer, Integer>add(MapperMaths.<Integer, Integer, Integer>add(MapperMaths.<Integer, Integer, Integer>multiply(MapperS.of(360), MapperMaths.<Integer, Integer, Integer>subtract(endYear(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), startYear(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear))), MapperMaths.<Integer, Integer, Integer>multiply(MapperS.of(30), MapperMaths.<Integer, Integer, Integer>subtract(endMonth(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), startMonth(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear)))), MapperMaths.<Integer, Integer, Integer>subtract(endDay(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), startDay(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear))), MapperS.of(360)).get();
				
				return result;
			}
			
			@Override
			protected MapperS<Integer> startYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(startDate).<Integer>map("Year", Date::getYear);
			}
			
			@Override
			protected MapperS<Integer> endYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(endDate).<Integer>map("Year", Date::getYear);
			}
			
			@Override
			protected MapperS<Integer> startMonth(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(startDate).<Integer>map("Month", Date::getMonth);
			}
			
			@Override
			protected MapperS<Integer> endMonth(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(endDate).<Integer>map("Month", Date::getMonth);
			}
			
			@Override
			protected MapperS<Integer> endDay(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(new Min().execute(MapperS.of(endDate).<Integer>map("Day", Date::getDay).get(), 30));
			}
			
			@Override
			protected MapperS<Integer> startDay(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(new Min().execute(MapperS.of(startDate).<Integer>map("Day", Date::getDay).get(), 30));
			}
		}
	}
	@ImplementedBy(YearFraction_30E_360_ISDA.YearFraction_30E_360_ISDADefault.class)
	public static abstract class YearFraction_30E_360_ISDA implements RosettaFunction {
	
		/**
		* @param dayCountFractionEnum The day count fraction to use
		* @param startDate The start date of the range for which the year fraction is required
		* @param endDate The end date of the range for which the year fraction is required
		* @param terminationDate The termination date of the payout; this is needed for some day count fractions
		* @param periodsInYear The number of periods in a year in the payout; this is needed for some day count fractions
		* @return result The fraction of a year represented by period from the startDate to the endDate
		*/
		public BigDecimal evaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
			BigDecimal result = doEvaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			
			return result;
		}
	
		protected abstract BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Boolean> startDateIsInLeapYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Boolean> endDateIsInLeapYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> startYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> endYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> startMonth(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> endMonth(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> startDay(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> endDay(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		public static class YearFraction_30E_360_ISDADefault extends YearFraction_30E_360_ISDA {
			@Override
			protected BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				BigDecimal result = null;
				return assignOutput(result, dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			}
			
			protected BigDecimal assignOutput(BigDecimal result, DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				result = MapperMaths.<BigDecimal, Integer, Integer>divide(MapperMaths.<Integer, Integer, Integer>add(MapperMaths.<Integer, Integer, Integer>add(MapperMaths.<Integer, Integer, Integer>multiply(MapperS.of(360), MapperMaths.<Integer, Integer, Integer>subtract(endYear(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), startYear(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear))), MapperMaths.<Integer, Integer, Integer>multiply(MapperS.of(30), MapperMaths.<Integer, Integer, Integer>subtract(endMonth(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), startMonth(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear)))), MapperMaths.<Integer, Integer, Integer>subtract(endDay(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), startDay(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear))), MapperS.of(360)).get();
				
				return result;
			}
			
			@Override
			protected MapperS<Boolean> startDateIsInLeapYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				final Integer integer = MapperS.of(startDate).<Integer>map("Year", Date::getYear).get();
				return integer == null ? MapperS.of(null) : MapperS.of(new IsLeapYear().execute(BigDecimal.valueOf(integer)));
			}
			
			@Override
			protected MapperS<Boolean> endDateIsInLeapYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				final Integer integer = MapperS.of(endDate).<Integer>map("Year", Date::getYear).get();
				return integer == null ? MapperS.of(null) : MapperS.of(new IsLeapYear().execute(BigDecimal.valueOf(integer)));
			}
			
			@Override
			protected MapperS<Integer> startYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(startDate).<Integer>map("Year", Date::getYear);
			}
			
			@Override
			protected MapperS<Integer> endYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(endDate).<Integer>map("Year", Date::getYear);
			}
			
			@Override
			protected MapperS<Integer> startMonth(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(startDate).<Integer>map("Month", Date::getMonth);
			}
			
			@Override
			protected MapperS<Integer> endMonth(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(endDate).<Integer>map("Month", Date::getMonth);
			}
			
			@Override
			protected MapperS<Integer> startDay(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				if (areEqual(startDateIsInLeapYear(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), MapperS.of(false), CardinalityOperator.All).and(areEqual(MapperS.of(startDate).<Integer>map("Month", Date::getMonth), MapperS.of(2), CardinalityOperator.All)).and(areEqual(MapperS.of(startDate).<Integer>map("Day", Date::getDay), MapperS.of(28), CardinalityOperator.All)).or(areEqual(startDateIsInLeapYear(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), MapperS.of(true), CardinalityOperator.All).and(areEqual(MapperS.of(startDate).<Integer>map("Month", Date::getMonth), MapperS.of(2), CardinalityOperator.All)).and(areEqual(MapperS.of(startDate).<Integer>map("Day", Date::getDay), MapperS.of(29), CardinalityOperator.All))).or(areEqual(MapperS.of(startDate).<Integer>map("Day", Date::getDay), MapperS.of(31), CardinalityOperator.All)).getOrDefault(false)) {
					return MapperS.of(30);
				}
				return MapperS.of(endDate).<Integer>map("Day", Date::getDay);
			}
			
			@Override
			protected MapperS<Integer> endDay(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				if (areEqual(endDateIsInLeapYear(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), MapperS.of(false), CardinalityOperator.All).and(areEqual(MapperS.of(endDate).<Integer>map("Month", Date::getMonth), MapperS.of(2), CardinalityOperator.All)).and(areEqual(MapperS.of(endDate).<Integer>map("Day", Date::getDay), MapperS.of(28), CardinalityOperator.All)).or(areEqual(endDateIsInLeapYear(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), MapperS.of(true), CardinalityOperator.All).and(areEqual(MapperS.of(endDate).<Integer>map("Month", Date::getMonth), MapperS.of(2), CardinalityOperator.All)).and(areEqual(MapperS.of(endDate).<Integer>map("Day", Date::getDay), MapperS.of(29), CardinalityOperator.All))).or(areEqual(MapperS.of(endDate).<Integer>map("Day", Date::getDay), MapperS.of(31), CardinalityOperator.All)).or(areEqual(MapperS.of(endDate), MapperS.of(terminationDate), CardinalityOperator.All)).getOrDefault(false)) {
					return MapperS.of(30);
				}
				return MapperS.of(endDate).<Integer>map("Day", Date::getDay);
			}
		}
	}
	@ImplementedBy(YearFractionACT_364.YearFractionACT_364Default.class)
	public static abstract class YearFractionACT_364 implements RosettaFunction {
		
		// RosettaFunction dependencies
		//
		@Inject protected DateDifference dateDifference;
	
		/**
		* @param dayCountFractionEnum The day count fraction to use
		* @param startDate The start date of the range for which the year fraction is required
		* @param endDate The end date of the range for which the year fraction is required
		* @param terminationDate The termination date of the payout; this is needed for some day count fractions
		* @param periodsInYear The number of periods in a year in the payout; this is needed for some day count fractions
		* @return result The fraction of a year represented by period from the startDate to the endDate
		*/
		public BigDecimal evaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
			BigDecimal result = doEvaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			
			return result;
		}
	
		protected abstract BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> daysInPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		public static class YearFractionACT_364Default extends YearFractionACT_364 {
			@Override
			protected BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				BigDecimal result = null;
				return assignOutput(result, dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			}
			
			protected BigDecimal assignOutput(BigDecimal result, DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				result = MapperMaths.<BigDecimal, Integer, Integer>divide(daysInPeriod(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), MapperS.of(364)).get();
				
				return result;
			}
			
			@Override
			protected MapperS<Integer> daysInPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(dateDifference.evaluate(startDate, endDate));
			}
		}
	}
	@ImplementedBy(YearFractionACT_365L.YearFractionACT_365LDefault.class)
	public static abstract class YearFractionACT_365L implements RosettaFunction {
		
		// RosettaFunction dependencies
		//
		@Inject protected DateDifference dateDifference;
	
		/**
		* @param dayCountFractionEnum The day count fraction to use
		* @param startDate The start date of the range for which the year fraction is required
		* @param endDate The end date of the range for which the year fraction is required
		* @param terminationDate The termination date of the payout; this is needed for some day count fractions
		* @param periodsInYear The number of periods in a year in the payout; this is needed for some day count fractions
		* @return result The fraction of a year represented by period from the startDate to the endDate
		*/
		public BigDecimal evaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
			BigDecimal result = doEvaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			
			return result;
		}
	
		protected abstract BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Boolean> endDateIsInLeapYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> daysInPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> daysInYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		public static class YearFractionACT_365LDefault extends YearFractionACT_365L {
			@Override
			protected BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				BigDecimal result = null;
				return assignOutput(result, dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			}
			
			protected BigDecimal assignOutput(BigDecimal result, DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				result = MapperMaths.<BigDecimal, Integer, Integer>divide(daysInPeriod(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), daysInYear(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear)).get();
				
				return result;
			}
			
			@Override
			protected MapperS<Boolean> endDateIsInLeapYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				final Integer integer = MapperS.of(endDate).<Integer>map("Year", Date::getYear).get();
				return integer == null ? MapperS.of(null) : MapperS.of(new IsLeapYear().execute(BigDecimal.valueOf(integer)));
			}
			
			@Override
			protected MapperS<Integer> daysInPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(dateDifference.evaluate(startDate, endDate));
			}
			
			@Override
			protected MapperS<Integer> daysInYear(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				if (areEqual(endDateIsInLeapYear(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
					return MapperS.of(366);
				}
				return MapperS.of(365);
			}
		}
	}
	@ImplementedBy(YearFractionCAL_252.YearFractionCAL_252Default.class)
	public static abstract class YearFractionCAL_252 implements RosettaFunction {
		
		// RosettaFunction dependencies
		//
		@Inject protected DateDifference dateDifference;
	
		/**
		* @param dayCountFractionEnum The day count fraction to use
		* @param startDate The start date of the range for which the year fraction is required
		* @param endDate The end date of the range for which the year fraction is required
		* @param terminationDate The termination date of the payout; this is needed for some day count fractions
		* @param periodsInYear The number of periods in a year in the payout; this is needed for some day count fractions
		* @return result The fraction of a year represented by period from the startDate to the endDate
		*/
		public BigDecimal evaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
			BigDecimal result = doEvaluate(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			
			return result;
		}
	
		protected abstract BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		protected abstract MapperS<Integer> daysInPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear);
	
		public static class YearFractionCAL_252Default extends YearFractionCAL_252 {
			@Override
			protected BigDecimal doEvaluate(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				BigDecimal result = null;
				return assignOutput(result, dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear);
			}
			
			protected BigDecimal assignOutput(BigDecimal result, DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				result = MapperMaths.<BigDecimal, Integer, Integer>divide(daysInPeriod(dayCountFractionEnum, startDate, endDate, terminationDate, periodsInYear), MapperS.of(252)).get();
				
				return result;
			}
			
			@Override
			protected MapperS<Integer> daysInPeriod(DayCountFractionEnum dayCountFractionEnum, Date startDate, Date endDate, Date terminationDate, Integer periodsInYear) {
				return MapperS.of(dateDifference.evaluate(startDate, endDate));
			}
		}
	}
}
