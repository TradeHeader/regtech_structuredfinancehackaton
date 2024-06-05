package cdm.product.asset.functions;

import cdm.event.common.functions.AdjustedValuationDates;
import cdm.observable.asset.Observable;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.PriceSchedule.PriceScheduleBuilder;
import cdm.observable.asset.ValuationDates;
import cdm.product.asset.PriceReturnTerms;
import cdm.product.template.PerformancePayout;
import cdm.product.template.ReturnTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ResolvePerformancePeriodStartPrice.ResolvePerformancePeriodStartPriceDefault.class)
public abstract class ResolvePerformancePeriodStartPrice implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected AdjustedValuationDates adjustedValuationDates0;
	@Inject protected ResolveEquityInitialPrice resolveEquityInitialPrice;

	/**
	* @param performancePayout 
	* @param price 
	* @param observable 
	* @param adjustedDate 
	* @return startPrice 
	*/
	public PriceSchedule evaluate(PerformancePayout performancePayout, List<? extends PriceSchedule> price, Observable observable, Date adjustedDate) {
		PriceSchedule.PriceScheduleBuilder startPriceBuilder = doEvaluate(performancePayout, price, observable, adjustedDate);
		
		final PriceSchedule startPrice;
		if (startPriceBuilder == null) {
			startPrice = null;
		} else {
			startPrice = startPriceBuilder.build();
			objectValidator.validate(PriceSchedule.class, startPrice);
		}
		
		return startPrice;
	}

	protected abstract PriceSchedule.PriceScheduleBuilder doEvaluate(PerformancePayout performancePayout, List<? extends PriceSchedule> price, Observable observable, Date adjustedDate);

	protected abstract MapperS<? extends PriceReturnTerms> priceReturnTerms(PerformancePayout performancePayout, List<? extends PriceSchedule> price, Observable observable, Date adjustedDate);

	protected abstract MapperC<Date> adjustedValuationDates1(PerformancePayout performancePayout, List<? extends PriceSchedule> price, Observable observable, Date adjustedDate);

	public static class ResolvePerformancePeriodStartPriceDefault extends ResolvePerformancePeriodStartPrice {
		@Override
		protected PriceSchedule.PriceScheduleBuilder doEvaluate(PerformancePayout performancePayout, List<? extends PriceSchedule> price, Observable observable, Date adjustedDate) {
			if (price == null) {
				price = Collections.emptyList();
			}
			PriceSchedule.PriceScheduleBuilder startPrice = PriceSchedule.builder();
			return assignOutput(startPrice, performancePayout, price, observable, adjustedDate);
		}
		
		protected PriceSchedule.PriceScheduleBuilder assignOutput(PriceSchedule.PriceScheduleBuilder startPrice, PerformancePayout performancePayout, List<? extends PriceSchedule> price, Observable observable, Date adjustedDate) {
			if (lessThan(MapperS.of(adjustedDate), adjustedValuationDates1(performancePayout, price, observable, adjustedDate)
				.first(), CardinalityOperator.All).getOrDefault(false)) {
				startPrice = toBuilder(priceReturnTerms(performancePayout, price, observable, adjustedDate).<PriceSchedule>map("getValuationPriceInitial", _priceReturnTerms -> _priceReturnTerms.getValuationPriceInitial()).get());
			} else {
				startPrice = toBuilder(resolveEquityInitialPrice.evaluate(price));
			}
			
			return Optional.ofNullable(startPrice)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends PriceReturnTerms> priceReturnTerms(PerformancePayout performancePayout, List<? extends PriceSchedule> price, Observable observable, Date adjustedDate) {
			return MapperS.of(performancePayout).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<PriceReturnTerms>map("getPriceReturnTerms", returnTerms -> returnTerms.getPriceReturnTerms());
		}
		
		@Override
		protected MapperC<Date> adjustedValuationDates1(PerformancePayout performancePayout, List<? extends PriceSchedule> price, Observable observable, Date adjustedDate) {
			return MapperC.<Date>of(adjustedValuationDates0.evaluate(MapperS.of(performancePayout).<ValuationDates>map("getValuationDates", _performancePayout -> _performancePayout.getValuationDates()).get()));
		}
	}
}
