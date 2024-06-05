package cdm.product.collateral.functions;

import cdm.base.datetime.Period;
import cdm.base.datetime.PeriodBound;
import cdm.base.datetime.PeriodRange;
import cdm.product.collateral.EligibilityQuery;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(CheckMaturity.CheckMaturityDefault.class)
public abstract class CheckMaturity implements RosettaFunction {

	/**
	* @param maturityRange 
	* @param query 
	* @return isEqual 
	*/
	public Boolean evaluate(PeriodRange maturityRange, EligibilityQuery query) {
		Boolean isEqual = doEvaluate(maturityRange, query);
		
		return isEqual;
	}

	protected abstract Boolean doEvaluate(PeriodRange maturityRange, EligibilityQuery query);

	protected abstract MapperS<Boolean> upperBoundCheck(PeriodRange maturityRange, EligibilityQuery query);

	protected abstract MapperS<Boolean> lowerBoundCheck(PeriodRange maturityRange, EligibilityQuery query);

	public static class CheckMaturityDefault extends CheckMaturity {
		@Override
		protected Boolean doEvaluate(PeriodRange maturityRange, EligibilityQuery query) {
			Boolean isEqual = null;
			return assignOutput(isEqual, maturityRange, query);
		}
		
		protected Boolean assignOutput(Boolean isEqual, PeriodRange maturityRange, EligibilityQuery query) {
			isEqual = notExists(MapperS.of(maturityRange)).or(ComparisonResult.of(upperBoundCheck(maturityRange, query)).and(ComparisonResult.of(lowerBoundCheck(maturityRange, query)))).get();
			
			return isEqual;
		}
		
		@Override
		protected MapperS<Boolean> upperBoundCheck(PeriodRange maturityRange, EligibilityQuery query) {
			if (notExists(MapperS.of(maturityRange).<PeriodBound>map("getUpperBound", periodRange -> periodRange.getUpperBound()).<Period>map("getPeriod", periodBound -> periodBound.getPeriod())).getOrDefault(false)) {
				return MapperS.of(true);
			}
			if (ComparisonResult.of(MapperS.of(maturityRange).<PeriodBound>map("getUpperBound", periodRange -> periodRange.getUpperBound()).<Boolean>map("getInclusive", periodBound -> periodBound.getInclusive())).or(notExists(MapperS.of(maturityRange).<PeriodBound>map("getUpperBound", periodRange -> periodRange.getUpperBound()).<Boolean>map("getInclusive", periodBound -> periodBound.getInclusive()))).getOrDefault(false)) {
				return greaterThanEquals(MapperS.of(maturityRange).<PeriodBound>map("getUpperBound", periodRange -> periodRange.getUpperBound()).<Period>map("getPeriod", periodBound -> periodBound.getPeriod()).<Integer>map("getPeriodMultiplier", period -> period.getPeriodMultiplier()).<BigDecimal>map("Type coercion", integer -> integer == null ? null : BigDecimal.valueOf(integer)), MapperS.of(query).<BigDecimal>map("getMaturity", eligibilityQuery -> eligibilityQuery.getMaturity()), CardinalityOperator.All).asMapper();
			}
			return greaterThan(MapperS.of(maturityRange).<PeriodBound>map("getUpperBound", periodRange -> periodRange.getUpperBound()).<Period>map("getPeriod", periodBound -> periodBound.getPeriod()).<Integer>map("getPeriodMultiplier", period -> period.getPeriodMultiplier()).<BigDecimal>map("Type coercion", integer -> integer == null ? null : BigDecimal.valueOf(integer)), MapperS.of(query).<BigDecimal>map("getMaturity", eligibilityQuery -> eligibilityQuery.getMaturity()), CardinalityOperator.All).asMapper();
		}
		
		@Override
		protected MapperS<Boolean> lowerBoundCheck(PeriodRange maturityRange, EligibilityQuery query) {
			if (notExists(MapperS.of(maturityRange).<PeriodBound>map("getLowerBound", periodRange -> periodRange.getLowerBound()).<Period>map("getPeriod", periodBound -> periodBound.getPeriod())).getOrDefault(false)) {
				return MapperS.of(true);
			}
			if (ComparisonResult.of(MapperS.of(maturityRange).<PeriodBound>map("getLowerBound", periodRange -> periodRange.getLowerBound()).<Boolean>map("getInclusive", periodBound -> periodBound.getInclusive())).or(notExists(MapperS.of(maturityRange).<PeriodBound>map("getLowerBound", periodRange -> periodRange.getLowerBound()).<Boolean>map("getInclusive", periodBound -> periodBound.getInclusive()))).getOrDefault(false)) {
				return lessThanEquals(MapperS.of(maturityRange).<PeriodBound>map("getLowerBound", periodRange -> periodRange.getLowerBound()).<Period>map("getPeriod", periodBound -> periodBound.getPeriod()).<Integer>map("getPeriodMultiplier", period -> period.getPeriodMultiplier()).<BigDecimal>map("Type coercion", integer -> integer == null ? null : BigDecimal.valueOf(integer)), MapperS.of(query).<BigDecimal>map("getMaturity", eligibilityQuery -> eligibilityQuery.getMaturity()), CardinalityOperator.All).asMapper();
			}
			return greaterThan(MapperS.of(maturityRange).<PeriodBound>map("getLowerBound", periodRange -> periodRange.getLowerBound()).<Period>map("getPeriod", periodBound -> periodBound.getPeriod()).<Integer>map("getPeriodMultiplier", period -> period.getPeriodMultiplier()).<BigDecimal>map("Type coercion", integer -> integer == null ? null : BigDecimal.valueOf(integer)), MapperS.of(query).<BigDecimal>map("getMaturity", eligibilityQuery -> eligibilityQuery.getMaturity()), CardinalityOperator.All).asMapper();
		}
	}
}
