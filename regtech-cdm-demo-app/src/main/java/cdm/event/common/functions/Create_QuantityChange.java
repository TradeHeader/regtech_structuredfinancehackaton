package cdm.event.common.functions;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.QuantityChangeDirectionEnum;
import cdm.base.math.metafields.FieldWithMetaNonNegativeQuantitySchedule;
import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.AncillaryParty;
import cdm.base.staticdata.party.Counterparty;
import cdm.event.common.QuantityChangeInstruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.TradeState.TradeStateBuilder;
import cdm.event.position.PositionStatusEnum;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.PriceTypeEnum;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.product.common.NotionalAdjustmentEnum;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.common.settlement.functions.UpdateAmountForEachMatchingQuantity;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import cdm.product.template.functions.AddTradeLot;
import cdm.product.template.functions.FilterTradeLot;
import cdm.product.template.functions.MergeTradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_QuantityChange.Create_QuantityChangeDefault.class)
public abstract class Create_QuantityChange implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected AddTradeLot addTradeLot;
	@Inject protected FilterTradeLot filterTradeLot;
	@Inject protected MergeTradeLot mergeTradeLot;
	@Inject protected UpdateAmountForEachMatchingQuantity updateAmountForEachMatchingQuantity;

	/**
	* @param instruction 
	* @param tradeState 
	* @return quantityChange 
	*/
	public TradeState evaluate(QuantityChangeInstruction instruction, TradeState tradeState) {
		// pre-conditions
		conditionValidator.validate(() -> {
			if (areEqual(MapperS.of(instruction).<QuantityChangeDirectionEnum>map("getDirection", quantityChangeInstruction -> quantityChangeInstruction.getDirection()), MapperS.of(QuantityChangeDirectionEnum.DECREASE), CardinalityOperator.All).and(exists(MapperS.of(instruction).<PriceQuantity>mapC("getChange", quantityChangeInstruction -> quantityChangeInstruction.getChange()).<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()))).getOrDefault(false)) {
				return areEqual(MapperS.of(instruction).<PriceQuantity>mapC("getChange", quantityChangeInstruction -> quantityChangeInstruction.getChange()).<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()).<PriceTypeEnum>map("getPriceType", priceSchedule -> priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.CASH_PRICE), CardinalityOperator.All);
			}
			return ComparisonResult.successEmptyOperand("");
		},
			"Only termination where the termination price is specified as a cash price is supported for now.");
		
		TradeState.TradeStateBuilder quantityChangeBuilder = doEvaluate(instruction, tradeState);
		
		final TradeState quantityChange;
		if (quantityChangeBuilder == null) {
			quantityChange = null;
		} else {
			quantityChange = quantityChangeBuilder.build();
			objectValidator.validate(TradeState.class, quantityChange);
		}
		
		return quantityChange;
	}

	protected abstract TradeState.TradeStateBuilder doEvaluate(QuantityChangeInstruction instruction, TradeState tradeState);

	protected abstract MapperS<? extends TradableProduct> tradableProduct(QuantityChangeInstruction instruction, TradeState tradeState);

	protected abstract MapperS<? extends TradeLot> tradeLot(QuantityChangeInstruction instruction, TradeState tradeState);

	protected abstract MapperC<? extends PriceQuantity> newPriceQuantity(QuantityChangeInstruction instruction, TradeState tradeState);

	protected abstract MapperC<? extends TradeLot> newTradeLots(QuantityChangeInstruction instruction, TradeState tradeState);

	public static class Create_QuantityChangeDefault extends Create_QuantityChange {
		@Override
		protected TradeState.TradeStateBuilder doEvaluate(QuantityChangeInstruction instruction, TradeState tradeState) {
			TradeState.TradeStateBuilder quantityChange = TradeState.builder();
			return assignOutput(quantityChange, instruction, tradeState);
		}
		
		protected TradeState.TradeStateBuilder assignOutput(TradeState.TradeStateBuilder quantityChange, QuantityChangeInstruction instruction, TradeState tradeState) {
			quantityChange = toBuilder(tradeState);
			
			quantityChange
				.getOrCreateTrade()
				.setTradableProduct(TradableProduct.builder()
					.setProduct(tradableProduct(instruction, tradeState).<Product>map("getProduct", _tradableProduct -> _tradableProduct.getProduct()).get())
					.setTradeLot(new ArrayList<>(newTradeLots(instruction, tradeState).getMulti()))
					.setCounterparty(tradableProduct(instruction, tradeState).<Counterparty>mapC("getCounterparty", _tradableProduct -> _tradableProduct.getCounterparty()).getMulti())
					.setAncillaryParty(tradableProduct(instruction, tradeState).<AncillaryParty>mapC("getAncillaryParty", _tradableProduct -> _tradableProduct.getAncillaryParty()).getMulti())
					.setAdjustment(tradableProduct(instruction, tradeState).<NotionalAdjustmentEnum>map("getAdjustment", _tradableProduct -> _tradableProduct.getAdjustment()).get())
					.build()
				);
			
			PositionStatusEnum ifThenElseResult = null;
			if (areEqual(newTradeLots(instruction, tradeState).<PriceQuantity>mapC("getPriceQuantity", _tradeLot -> _tradeLot.getPriceQuantity()).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All).getOrDefault(false)) {
				ifThenElseResult = PositionStatusEnum.CLOSED;
			}
			quantityChange
				.getOrCreateState()
				.setPositionState(ifThenElseResult);
			
			return Optional.ofNullable(quantityChange)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends TradableProduct> tradableProduct(QuantityChangeInstruction instruction, TradeState tradeState) {
			return MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct());
		}
		
		@Override
		protected MapperS<? extends TradeLot> tradeLot(QuantityChangeInstruction instruction, TradeState tradeState) {
			if (notEqual(MapperS.of(instruction).<QuantityChangeDirectionEnum>map("getDirection", quantityChangeInstruction -> quantityChangeInstruction.getDirection()), MapperS.of(QuantityChangeDirectionEnum.INCREASE), CardinalityOperator.Any).getOrDefault(false)) {
				if (exists(MapperS.of(instruction).<Identifier>mapC("getLotIdentifier", quantityChangeInstruction -> quantityChangeInstruction.getLotIdentifier())).getOrDefault(false)) {
					return MapperS.of(MapperC.of(filterTradeLot.evaluate(tradableProduct(instruction, tradeState).<TradeLot>mapC("getTradeLot", _tradableProduct -> _tradableProduct.getTradeLot()).getMulti(), MapperS.of(instruction).<Identifier>mapC("getLotIdentifier", quantityChangeInstruction -> quantityChangeInstruction.getLotIdentifier()).getMulti())).get());
				}
				return MapperS.of(tradableProduct(instruction, tradeState).<TradeLot>mapC("getTradeLot", _tradableProduct -> _tradableProduct.getTradeLot()).get());
			}
			return MapperS.<TradeLot>ofNull();
		}
		
		@Override
		protected MapperC<? extends PriceQuantity> newPriceQuantity(QuantityChangeInstruction instruction, TradeState tradeState) {
			if (areEqual(MapperS.of(instruction).<QuantityChangeDirectionEnum>map("getDirection", quantityChangeInstruction -> quantityChangeInstruction.getDirection()), MapperS.of(QuantityChangeDirectionEnum.INCREASE), CardinalityOperator.All).getOrDefault(false)) {
				return MapperS.of(instruction).<PriceQuantity>mapC("getChange", quantityChangeInstruction -> quantityChangeInstruction.getChange());
			}
			return MapperC.<PriceQuantity>of(updateAmountForEachMatchingQuantity.evaluate(tradeLot(instruction, tradeState).<PriceQuantity>mapC("getPriceQuantity", _tradeLot -> _tradeLot.getPriceQuantity()).getMulti(), MapperS.of(instruction).<PriceQuantity>mapC("getChange", quantityChangeInstruction -> quantityChangeInstruction.getChange()).getMulti(), MapperS.of(instruction).<QuantityChangeDirectionEnum>map("getDirection", quantityChangeInstruction -> quantityChangeInstruction.getDirection()).get()));
		}
		
		@Override
		protected MapperC<? extends TradeLot> newTradeLots(QuantityChangeInstruction instruction, TradeState tradeState) {
			if (areEqual(MapperS.of(instruction).<QuantityChangeDirectionEnum>map("getDirection", quantityChangeInstruction -> quantityChangeInstruction.getDirection()), MapperS.of(QuantityChangeDirectionEnum.INCREASE), CardinalityOperator.All).getOrDefault(false)) {
				return MapperS.of(addTradeLot.evaluate(tradableProduct(instruction, tradeState).get(), TradeLot.builder()
					.setLotIdentifier(MapperS.of(instruction).<Identifier>mapC("getLotIdentifier", quantityChangeInstruction -> quantityChangeInstruction.getLotIdentifier()).getMulti())
					.setPriceQuantity(new ArrayList<>(newPriceQuantity(instruction, tradeState).getMulti()))
					.build()
				)).<TradeLot>mapC("getTradeLot", _tradableProduct -> _tradableProduct.getTradeLot());
			}
			return MapperC.<TradeLot>of(mergeTradeLot.evaluate(tradableProduct(instruction, tradeState).<TradeLot>mapC("getTradeLot", _tradableProduct -> _tradableProduct.getTradeLot()).getMulti(), TradeLot.builder()
				.setLotIdentifier(MapperS.of(instruction).<Identifier>mapC("getLotIdentifier", quantityChangeInstruction -> quantityChangeInstruction.getLotIdentifier()).getMulti())
				.setPriceQuantity(new ArrayList<>(newPriceQuantity(instruction, tradeState).getMulti()))
				.build()
			));
		}
	}
}
