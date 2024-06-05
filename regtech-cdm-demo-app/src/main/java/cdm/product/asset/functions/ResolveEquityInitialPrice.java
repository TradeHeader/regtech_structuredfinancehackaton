package cdm.product.asset.functions;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.base.math.DatedValue;
import cdm.base.math.FinancialUnitEnum;
import cdm.base.math.UnitType;
import cdm.observable.asset.CashPrice;
import cdm.observable.asset.Price;
import cdm.observable.asset.PriceComposite;
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
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ResolveEquityInitialPrice.ResolveEquityInitialPriceDefault.class)
public abstract class ResolveEquityInitialPrice implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param price 
	* @return initialPrice 
	*/
	public PriceSchedule evaluate(List<? extends PriceSchedule> price) {
		PriceSchedule.PriceScheduleBuilder initialPriceBuilder = doEvaluate(price);
		
		final PriceSchedule initialPrice;
		if (initialPriceBuilder == null) {
			initialPrice = null;
		} else {
			initialPrice = initialPriceBuilder.build();
			objectValidator.validate(PriceSchedule.class, initialPrice);
		}
		
		return initialPrice;
	}

	protected abstract PriceSchedule.PriceScheduleBuilder doEvaluate(List<? extends PriceSchedule> price);

	public static class ResolveEquityInitialPriceDefault extends ResolveEquityInitialPrice {
		@Override
		protected PriceSchedule.PriceScheduleBuilder doEvaluate(List<? extends PriceSchedule> price) {
			if (price == null) {
				price = Collections.emptyList();
			}
			PriceSchedule.PriceScheduleBuilder initialPrice = PriceSchedule.builder();
			return assignOutput(initialPrice, price);
		}
		
		protected PriceSchedule.PriceScheduleBuilder assignOutput(PriceSchedule.PriceScheduleBuilder initialPrice, List<? extends PriceSchedule> price) {
			final MapperC<PriceSchedule> thenResult0 = MapperC.<PriceSchedule>of(price)
				.filterItemNullSafe(item -> areEqual(item.<UnitType>map("getPerUnitOf", priceSchedule -> priceSchedule.getPerUnitOf()).<FinancialUnitEnum>map("getFinancialUnit", unitType -> unitType.getFinancialUnit()), MapperS.of(FinancialUnitEnum.SHARE), CardinalityOperator.All).get());
			final MapperC<Price> thenResult1 = thenResult0
				.mapItem(item -> MapperS.of(Price.builder()
					.setValue(item.<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get())
					.setUnit(item.<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).get())
					.setPerUnitOf(item.<UnitType>map("getPerUnitOf", priceSchedule -> priceSchedule.getPerUnitOf()).get())
					.setPriceType(item.<PriceTypeEnum>map("getPriceType", priceSchedule -> priceSchedule.getPriceType()).get())
					.setPriceExpression(item.<PriceExpressionEnum>map("getPriceExpression", priceSchedule -> priceSchedule.getPriceExpression()).get())
					.setComposite(item.<PriceComposite>map("getComposite", priceSchedule -> priceSchedule.getComposite()).get())
					.setArithmeticOperator(item.<ArithmeticOperationEnum>map("getArithmeticOperator", priceSchedule -> priceSchedule.getArithmeticOperator()).get())
					.setCashPrice(item.<CashPrice>map("getCashPrice", priceSchedule -> priceSchedule.getCashPrice()).get())
					.setDatedValue(Collections.<DatedValue>emptyList())
					.build()
				));
			initialPrice = toBuilder(MapperS.of(thenResult1.get()).get());
			
			return Optional.ofNullable(initialPrice)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
