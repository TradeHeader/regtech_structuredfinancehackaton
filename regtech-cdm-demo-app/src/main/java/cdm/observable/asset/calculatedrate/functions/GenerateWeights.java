package cdm.observable.asset.calculatedrate.functions;

import cdm.base.datetime.functions.DateDifference;
import cdm.base.datetime.functions.PopOffDateList;
import cdm.base.math.functions.AppendToVector;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(GenerateWeights.GenerateWeightsDefault.class)
public abstract class GenerateWeights implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected AppendToVector appendToVector;
	@Inject protected DateDifference dateDifference;
	@Inject protected cdm.observable.asset.calculatedrate.functions.GenerateWeights generateWeights;
	@Inject protected PopOffDateList popOffDateList;

	/**
	* @param weightingDates A list of dates for which weightings are require.
	* @return weights A vector of weights, typically numbers between 1 and 3.
	*/
	public List<BigDecimal> evaluate(List<Date> weightingDates) {
		List<BigDecimal> weights = doEvaluate(weightingDates);
		
		return weights;
	}

	protected abstract List<BigDecimal> doEvaluate(List<Date> weightingDates);

	protected abstract MapperS<Boolean> active(List<Date> weightingDates);

	protected abstract MapperS<Date> refDate(List<Date> weightingDates);

	protected abstract MapperC<Date> remainingDates(List<Date> weightingDates);

	protected abstract MapperS<Date> prevDate(List<Date> weightingDates);

	protected abstract MapperS<Integer> diff(List<Date> weightingDates);

	protected abstract MapperC<BigDecimal> remainingWeights(List<Date> weightingDates);

	public static class GenerateWeightsDefault extends GenerateWeights {
		@Override
		protected List<BigDecimal> doEvaluate(List<Date> weightingDates) {
			if (weightingDates == null) {
				weightingDates = Collections.emptyList();
			}
			List<BigDecimal> weights = new ArrayList<>();
			return assignOutput(weights, weightingDates);
		}
		
		protected List<BigDecimal> assignOutput(List<BigDecimal> weights, List<Date> weightingDates) {
			if (active(weightingDates).getOrDefault(false)) {
				weights.addAll(appendToVector.evaluate(remainingWeights(weightingDates).getMulti(), MapperMaths.<BigDecimal, BigDecimal, BigDecimal>multiply(diff(weightingDates).<BigDecimal>map("Type coercion", integer -> integer == null ? null : BigDecimal.valueOf(integer)), MapperS.of(new BigDecimal("1.0"))).get()));
			} else {
				weights.addAll(Collections.<BigDecimal>emptyList());
			}
			
			return weights;
		}
		
		@Override
		protected MapperS<Boolean> active(List<Date> weightingDates) {
			return greaterThan(MapperS.of(MapperC.<Date>of(weightingDates).resultCount()), MapperS.of(1), CardinalityOperator.All).asMapper();
		}
		
		@Override
		protected MapperS<Date> refDate(List<Date> weightingDates) {
			return MapperC.<Date>of(weightingDates)
				.last();
		}
		
		@Override
		protected MapperC<Date> remainingDates(List<Date> weightingDates) {
			return MapperC.<Date>of(popOffDateList.evaluate(weightingDates));
		}
		
		@Override
		protected MapperS<Date> prevDate(List<Date> weightingDates) {
			return remainingDates(weightingDates)
				.last();
		}
		
		@Override
		protected MapperS<Integer> diff(List<Date> weightingDates) {
			return MapperS.of(dateDifference.evaluate(prevDate(weightingDates).get(), refDate(weightingDates).get()));
		}
		
		@Override
		protected MapperC<BigDecimal> remainingWeights(List<Date> weightingDates) {
			return MapperC.<BigDecimal>of(generateWeights.evaluate(remainingDates(weightingDates).getMulti()));
		}
	}
}
