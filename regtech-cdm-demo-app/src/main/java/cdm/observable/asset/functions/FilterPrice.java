package cdm.observable.asset.functions;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.observable.asset.PriceExpressionEnum;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.PriceSchedule.PriceScheduleBuilder;
import cdm.observable.asset.PriceTypeEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(FilterPrice.FilterPriceDefault.class)
public abstract class FilterPrice implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param prices List of prices to filter.
	* @param priceType The price type to filter by: asset price, cash price, exchange rate etc.
	* @param arithmeticOperators Optionally filter based on the type of operator, e.g. if price is specified as a spread or a multiplier. Several operators can be passed as arguments (e.g. [ Add, Subtract ]).
	* @param priceExpression Optionally filter by type of price expression: percentage of notional, par value fraction
	* @return price 
	*/
	public PriceSchedule evaluate(List<? extends PriceSchedule> prices, PriceTypeEnum priceType, List<ArithmeticOperationEnum> arithmeticOperators, PriceExpressionEnum priceExpression) {
		PriceSchedule.PriceScheduleBuilder priceBuilder = doEvaluate(prices, priceType, arithmeticOperators, priceExpression);
		
		final PriceSchedule price;
		if (priceBuilder == null) {
			price = null;
		} else {
			price = priceBuilder.build();
			objectValidator.validate(PriceSchedule.class, price);
		}
		
		return price;
	}

	protected abstract PriceSchedule.PriceScheduleBuilder doEvaluate(List<? extends PriceSchedule> prices, PriceTypeEnum priceType, List<ArithmeticOperationEnum> arithmeticOperators, PriceExpressionEnum priceExpression);

	public static class FilterPriceDefault extends FilterPrice {
		@Override
		protected PriceSchedule.PriceScheduleBuilder doEvaluate(List<? extends PriceSchedule> prices, PriceTypeEnum priceType, List<ArithmeticOperationEnum> arithmeticOperators, PriceExpressionEnum priceExpression) {
			if (prices == null) {
				prices = Collections.emptyList();
			}
			if (arithmeticOperators == null) {
				arithmeticOperators = Collections.emptyList();
			}
			PriceSchedule.PriceScheduleBuilder price = PriceSchedule.builder();
			return assignOutput(price, prices, priceType, arithmeticOperators, priceExpression);
		}
		
		protected PriceSchedule.PriceScheduleBuilder assignOutput(PriceSchedule.PriceScheduleBuilder price, List<? extends PriceSchedule> prices, PriceTypeEnum priceType, List<ArithmeticOperationEnum> arithmeticOperators, PriceExpressionEnum priceExpression) {
			final MapperC<PriceSchedule> thenResult0 = MapperC.<PriceSchedule>of(prices)
				.filterItemNullSafe(item -> areEqual(item.<PriceTypeEnum>map("getPriceType", priceSchedule -> priceSchedule.getPriceType()), MapperS.of(priceType), CardinalityOperator.All).get());
			final MapperC<PriceSchedule> thenResult1 = thenResult0
				.filterItemNullSafe(item -> {
					if (exists(MapperC.<ArithmeticOperationEnum>of(arithmeticOperators)).getOrDefault(false)) {
						return contains(MapperC.<ArithmeticOperationEnum>of(arithmeticOperators), item.<ArithmeticOperationEnum>map("getArithmeticOperator", priceSchedule -> priceSchedule.getArithmeticOperator())).get();
					}
					return true;
				});
			final MapperC<PriceSchedule> thenResult2 = thenResult1
				.filterItemNullSafe(item -> {
					if (exists(MapperS.of(priceExpression)).getOrDefault(false)) {
						return areEqual(item.<PriceExpressionEnum>map("getPriceExpression", priceSchedule -> priceSchedule.getPriceExpression()), MapperS.of(priceExpression), CardinalityOperator.All).get();
					}
					return true;
				});
			price = toBuilder(MapperS.of(thenResult2.get()).get());
			
			return Optional.ofNullable(price)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
