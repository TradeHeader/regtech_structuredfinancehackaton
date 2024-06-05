package cdm.observable.event.functions;

import cdm.base.math.UnitType;
import cdm.observable.asset.Price;
import cdm.observable.asset.Price.PriceBuilder;
import cdm.observable.asset.PriceExpressionEnum;
import cdm.observable.asset.PriceTypeEnum;
import cdm.observable.event.Observation;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ResolveObservationAverage.ResolveObservationAverageDefault.class)
public abstract class ResolveObservationAverage implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param observations 
	* @return resetValue 
	*/
	public Price evaluate(List<? extends Observation> observations) {
		// pre-conditions
		conditionValidator.validate(() -> areEqual(MapperC.<Observation>of(observations).<Price>map("getObservedValue", observation -> observation.getObservedValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()), firstObservedValue(observations).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()), CardinalityOperator.All),
			"");
		
		Price.PriceBuilder resetValueBuilder = doEvaluate(observations);
		
		final Price resetValue;
		if (resetValueBuilder == null) {
			resetValue = null;
		} else {
			resetValue = resetValueBuilder.build();
			objectValidator.validate(Price.class, resetValue);
		}
		
		return resetValue;
	}

	protected abstract Price.PriceBuilder doEvaluate(List<? extends Observation> observations);

	protected abstract MapperS<? extends Price> firstObservedValue(List<? extends Observation> observations);

	public static class ResolveObservationAverageDefault extends ResolveObservationAverage {
		@Override
		protected Price.PriceBuilder doEvaluate(List<? extends Observation> observations) {
			if (observations == null) {
				observations = Collections.emptyList();
			}
			Price.PriceBuilder resetValue = Price.builder();
			return assignOutput(resetValue, observations);
		}
		
		protected Price.PriceBuilder assignOutput(Price.PriceBuilder resetValue, List<? extends Observation> observations) {
			resetValue
				.setValue(MapperMaths.<BigDecimal, BigDecimal, BigDecimal>divide(MapperC.<Observation>of(observations).<Price>map("getObservedValue", observation -> observation.getObservedValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue())
					.sumBigDecimal(), MapperS.of(BigDecimal.valueOf(MapperC.<Observation>of(observations).resultCount()))).get());
			
			resetValue
				.setUnit(firstObservedValue(observations).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).get());
			
			resetValue
				.setPerUnitOf(firstObservedValue(observations).<UnitType>map("getPerUnitOf", priceSchedule -> priceSchedule.getPerUnitOf()).get());
			
			resetValue
				.setPriceExpression(firstObservedValue(observations).<PriceExpressionEnum>map("getPriceExpression", priceSchedule -> priceSchedule.getPriceExpression()).get());
			
			resetValue
				.setPriceType(firstObservedValue(observations).<PriceTypeEnum>map("getPriceType", priceSchedule -> priceSchedule.getPriceType()).get());
			
			return Optional.ofNullable(resetValue)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends Price> firstObservedValue(List<? extends Observation> observations) {
			return MapperC.<Observation>of(observations).<Price>map("getObservedValue", observation -> observation.getObservedValue())
				.first();
		}
	}
}
