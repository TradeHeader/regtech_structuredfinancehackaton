package cdm.event.qualification.functions;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.base.staticdata.asset.rates.FloatingRateIndexEnum;
import cdm.base.staticdata.asset.rates.metafields.FieldWithMetaFloatingRateIndexEnum;
import cdm.event.common.BusinessEvent;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.Instruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.observable.asset.FloatingRateOption;
import cdm.observable.asset.Observable;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.PriceTypeEnum;
import cdm.observable.asset.functions.FilterPrice;
import cdm.observable.asset.metafields.FieldWithMetaFloatingRateOption;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_IndexTransition.Qualify_IndexTransitionDefault.class)
public abstract class Qualify_IndexTransition implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
	@Inject protected FilterPrice filterPrice;

	/**
	* @param businessEvent 
	* @return is_event 
	*/
	@Override
	public Boolean evaluate(BusinessEvent businessEvent) {
		Boolean is_event = doEvaluate(businessEvent);
		
		return is_event;
	}

	protected abstract Boolean doEvaluate(BusinessEvent businessEvent);

	protected abstract MapperS<? extends TradableProduct> after(BusinessEvent businessEvent);

	protected abstract MapperC<? extends TradableProduct> before(BusinessEvent businessEvent);

	protected abstract MapperS<Boolean> floatingRateIndexChanged(BusinessEvent businessEvent);

	protected abstract MapperS<? extends PriceSchedule> spread(BusinessEvent businessEvent);

	protected abstract MapperS<Boolean> adjustmentSpreadAdded(BusinessEvent businessEvent);

	public static class Qualify_IndexTransitionDefault extends Qualify_IndexTransition {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = areEqual(MapperS.of(businessEvent).<EventIntentEnum>map("getIntent", eventInstruction -> eventInstruction.getIntent()), MapperS.of(EventIntentEnum.INDEX_TRANSITION), CardinalityOperator.All).and(areEqual(floatingRateIndexChanged(businessEvent), MapperS.of(true), CardinalityOperator.All)).and(areEqual(adjustmentSpreadAdded(businessEvent), MapperS.of(true), CardinalityOperator.All)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperS<? extends TradableProduct> after(BusinessEvent businessEvent) {
			return MapperS.of(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).get()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct());
		}
		
		@Override
		protected MapperC<? extends TradableProduct> before(BusinessEvent businessEvent) {
			return MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<ReferenceWithMetaTradeState>map("getBefore", instruction -> instruction.getBefore()).<TradeState>map("getValue", _f->_f.getValue()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct());
		}
		
		@Override
		protected MapperS<Boolean> floatingRateIndexChanged(BusinessEvent businessEvent) {
			return exists(before(businessEvent).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<Observable>map("getObservable", priceQuantity -> priceQuantity.getObservable()).<FieldWithMetaFloatingRateOption>map("getRateOption", observable -> observable.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue()).<FieldWithMetaFloatingRateIndexEnum>map("getFloatingRateIndex", floatingRateOption -> floatingRateOption.getFloatingRateIndex()).<FloatingRateIndexEnum>map("getValue", _f->_f.getValue())).and(disjoint(before(businessEvent).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<Observable>map("getObservable", priceQuantity -> priceQuantity.getObservable()).<FieldWithMetaFloatingRateOption>map("getRateOption", observable -> observable.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue()).<FieldWithMetaFloatingRateIndexEnum>map("getFloatingRateIndex", floatingRateOption -> floatingRateOption.getFloatingRateIndex()).<FloatingRateIndexEnum>map("getValue", _f->_f.getValue()), after(businessEvent).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<Observable>map("getObservable", priceQuantity -> priceQuantity.getObservable()).<FieldWithMetaFloatingRateOption>map("getRateOption", observable -> observable.getRateOption()).<FloatingRateOption>map("getValue", _f->_f.getValue()).<FieldWithMetaFloatingRateIndexEnum>map("getFloatingRateIndex", floatingRateOption -> floatingRateOption.getFloatingRateIndex()).<FloatingRateIndexEnum>map("getValue", _f->_f.getValue()))).asMapper();
		}
		
		@Override
		protected MapperS<? extends PriceSchedule> spread(BusinessEvent businessEvent) {
			return MapperS.of(filterPrice.evaluate(after(businessEvent).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).getMulti(), PriceTypeEnum.INTEREST_RATE, MapperC.<ArithmeticOperationEnum>of(MapperS.of(ArithmeticOperationEnum.ADD), MapperS.of(ArithmeticOperationEnum.SUBTRACT)).getMulti(), null));
		}
		
		@Override
		protected MapperS<Boolean> adjustmentSpreadAdded(BusinessEvent businessEvent) {
			if (exists(spread(businessEvent)).getOrDefault(false)) {
				return notEqual(spread(businessEvent).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.Any).asMapper();
			}
			return MapperS.of(true);
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
