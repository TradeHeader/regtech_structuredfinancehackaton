package cdm.event.common.functions;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.math.ArithmeticOperationEnum;
import cdm.base.math.DatedValue;
import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.QuantityChangeDirectionEnum;
import cdm.base.math.UnitType;
import cdm.base.math.metafields.FieldWithMetaNonNegativeQuantitySchedule;
import cdm.base.staticdata.identifier.Identifier;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.PrimitiveInstruction.PrimitiveInstructionBuilder;
import cdm.event.common.QuantityChangeInstruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.observable.asset.CashPrice;
import cdm.observable.asset.Price;
import cdm.observable.asset.PriceComposite;
import cdm.observable.asset.PriceExpressionEnum;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.PriceTypeEnum;
import cdm.observable.asset.metafields.FieldWithMetaPriceSchedule;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.ContractualProduct;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperListOfLists;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_AdjustmentPrimitiveInstruction.Create_AdjustmentPrimitiveInstructionDefault.class)
public abstract class Create_AdjustmentPrimitiveInstruction implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_EffectiveOrTerminationDateTermChangeInstruction create_EffectiveOrTerminationDateTermChangeInstruction;
	@Inject protected Create_TerminationInstruction create_TerminationInstruction;

	/**
	* @param tradeState The original trade state and trade to be repriced.
	* @param newAllinPrice The collateral new all-in price.
	* @param newAssetQuantity The collateral new quantity.
	* @param effectiveRepriceDate The date to reprice the collateral
	* @return instruction 
	*/
	public PrimitiveInstruction evaluate(TradeState tradeState, BigDecimal newAllinPrice, BigDecimal newAssetQuantity, AdjustableOrRelativeDate effectiveRepriceDate) {
		// pre-conditions
		conditionValidator.validate(() -> exists(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct())),
			"This repricing function applies only to contractual products.");
		
		conditionValidator.validate(() -> areEqual(MapperS.of(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).resultCount()), MapperS.of(1), CardinalityOperator.All),
			"This repricing function applies only to trades with a single lot.");
		
		PrimitiveInstruction.PrimitiveInstructionBuilder instructionBuilder = doEvaluate(tradeState, newAllinPrice, newAssetQuantity, effectiveRepriceDate);
		
		final PrimitiveInstruction instruction;
		if (instructionBuilder == null) {
			instruction = null;
		} else {
			instruction = instructionBuilder.build();
			objectValidator.validate(PrimitiveInstruction.class, instruction);
		}
		
		return instruction;
	}

	protected abstract PrimitiveInstruction.PrimitiveInstructionBuilder doEvaluate(TradeState tradeState, BigDecimal newAllinPrice, BigDecimal newAssetQuantity, AdjustableOrRelativeDate effectiveRepriceDate);

	protected abstract MapperC<? extends PriceQuantity> oldPriceQuantity(TradeState tradeState, BigDecimal newAllinPrice, BigDecimal newAssetQuantity, AdjustableOrRelativeDate effectiveRepriceDate);

	protected abstract MapperS<? extends PriceSchedule> currentAssetPrice(TradeState tradeState, BigDecimal newAllinPrice, BigDecimal newAssetQuantity, AdjustableOrRelativeDate effectiveRepriceDate);

	protected abstract MapperS<? extends Price> newPrice(TradeState tradeState, BigDecimal newAllinPrice, BigDecimal newAssetQuantity, AdjustableOrRelativeDate effectiveRepriceDate);

	protected abstract MapperC<? extends NonNegativeQuantitySchedule> changeQuantity(TradeState tradeState, BigDecimal newAllinPrice, BigDecimal newAssetQuantity, AdjustableOrRelativeDate effectiveRepriceDate);

	protected abstract MapperS<? extends PriceQuantity> newPriceQuantity(TradeState tradeState, BigDecimal newAllinPrice, BigDecimal newAssetQuantity, AdjustableOrRelativeDate effectiveRepriceDate);

	public static class Create_AdjustmentPrimitiveInstructionDefault extends Create_AdjustmentPrimitiveInstruction {
		@Override
		protected PrimitiveInstruction.PrimitiveInstructionBuilder doEvaluate(TradeState tradeState, BigDecimal newAllinPrice, BigDecimal newAssetQuantity, AdjustableOrRelativeDate effectiveRepriceDate) {
			PrimitiveInstruction.PrimitiveInstructionBuilder instruction = PrimitiveInstruction.builder();
			return assignOutput(instruction, tradeState, newAllinPrice, newAssetQuantity, effectiveRepriceDate);
		}
		
		protected PrimitiveInstruction.PrimitiveInstructionBuilder assignOutput(PrimitiveInstruction.PrimitiveInstructionBuilder instruction, TradeState tradeState, BigDecimal newAllinPrice, BigDecimal newAssetQuantity, AdjustableOrRelativeDate effectiveRepriceDate) {
			instruction
				.getOrCreateSplit()
				.setBreakdown(MapperC.<PrimitiveInstruction>of(MapperS.of(create_TerminationInstruction.evaluate(tradeState))).getMulti());
			
			instruction
				.getOrCreateSplit()
				.addBreakdown(MapperC.<PrimitiveInstruction>of(MapperS.of(PrimitiveInstruction.builder()
					.setQuantityChange(QuantityChangeInstruction.builder()
						.setChange(MapperC.<PriceQuantity>of(newPriceQuantity(tradeState, newAllinPrice, newAssetQuantity, effectiveRepriceDate)).getMulti())
						.setDirection(QuantityChangeDirectionEnum.REPLACE)
						.setLotIdentifier(Collections.<Identifier>emptyList())
						.build()
					)
					.setTermsChange(create_EffectiveOrTerminationDateTermChangeInstruction.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).get(), effectiveRepriceDate, null))
					.build()
				)).getMulti());
			
			return Optional.ofNullable(instruction)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperC<? extends PriceQuantity> oldPriceQuantity(TradeState tradeState, BigDecimal newAllinPrice, BigDecimal newAssetQuantity, AdjustableOrRelativeDate effectiveRepriceDate) {
			return MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity());
		}
		
		@Override
		protected MapperS<? extends PriceSchedule> currentAssetPrice(TradeState tradeState, BigDecimal newAllinPrice, BigDecimal newAssetQuantity, AdjustableOrRelativeDate effectiveRepriceDate) {
			final MapperListOfLists<PriceSchedule> thenResult0 = oldPriceQuantity(tradeState, newAllinPrice, newAssetQuantity, effectiveRepriceDate)
				.mapItemToList(item -> item.<FieldWithMetaPriceSchedule>mapC("getPrice", priceQuantity -> priceQuantity.getPrice()).<PriceSchedule>map("getValue", _f->_f.getValue()));
			final MapperC<PriceSchedule> thenResult1 = thenResult0
				.flattenList();
			final MapperC<PriceSchedule> thenResult2 = thenResult1
				.filterItemNullSafe(item -> areEqual(item.<PriceTypeEnum>map("getPriceType", priceSchedule -> priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.ASSET_PRICE), CardinalityOperator.All).get());
			return MapperS.of(thenResult2.get());
		}
		
		@Override
		protected MapperS<? extends Price> newPrice(TradeState tradeState, BigDecimal newAllinPrice, BigDecimal newAssetQuantity, AdjustableOrRelativeDate effectiveRepriceDate) {
			return MapperS.of(Price.builder()
				.setValue(newAllinPrice)
				.setUnit(currentAssetPrice(tradeState, newAllinPrice, newAssetQuantity, effectiveRepriceDate).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).get())
				.setPerUnitOf(currentAssetPrice(tradeState, newAllinPrice, newAssetQuantity, effectiveRepriceDate).<UnitType>map("getPerUnitOf", priceSchedule -> priceSchedule.getPerUnitOf()).get())
				.setPriceType(currentAssetPrice(tradeState, newAllinPrice, newAssetQuantity, effectiveRepriceDate).<PriceTypeEnum>map("getPriceType", priceSchedule -> priceSchedule.getPriceType()).get())
				.setPriceExpression(currentAssetPrice(tradeState, newAllinPrice, newAssetQuantity, effectiveRepriceDate).<PriceExpressionEnum>map("getPriceExpression", priceSchedule -> priceSchedule.getPriceExpression()).get())
				.setComposite(currentAssetPrice(tradeState, newAllinPrice, newAssetQuantity, effectiveRepriceDate).<PriceComposite>map("getComposite", priceSchedule -> priceSchedule.getComposite()).get())
				.setArithmeticOperator(currentAssetPrice(tradeState, newAllinPrice, newAssetQuantity, effectiveRepriceDate).<ArithmeticOperationEnum>map("getArithmeticOperator", priceSchedule -> priceSchedule.getArithmeticOperator()).get())
				.setCashPrice(currentAssetPrice(tradeState, newAllinPrice, newAssetQuantity, effectiveRepriceDate).<CashPrice>map("getCashPrice", priceSchedule -> priceSchedule.getCashPrice()).get())
				.setDatedValue(Collections.<DatedValue>emptyList())
				.build()
			);
		}
		
		@Override
		protected MapperC<? extends NonNegativeQuantitySchedule> changeQuantity(TradeState tradeState, BigDecimal newAllinPrice, BigDecimal newAssetQuantity, AdjustableOrRelativeDate effectiveRepriceDate) {
			final MapperC<NonNegativeQuantitySchedule> thenResult = MapperS.of(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue())
				.mapItem(item -> MapperS.of(NonNegativeQuantitySchedule.builder()
					.setValue(newAssetQuantity)
					.setUnit(item.<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).get())
					.build()
				));
			return distinct(thenResult);
		}
		
		@Override
		protected MapperS<? extends PriceQuantity> newPriceQuantity(TradeState tradeState, BigDecimal newAllinPrice, BigDecimal newAssetQuantity, AdjustableOrRelativeDate effectiveRepriceDate) {
			return MapperS.of(PriceQuantity.builder()
				.setPriceValue(MapperC.<Price>of(newPrice(tradeState, newAllinPrice, newAssetQuantity, effectiveRepriceDate)).getMulti())
				.setQuantityValue(new ArrayList<>(changeQuantity(tradeState, newAllinPrice, newAssetQuantity, effectiveRepriceDate).getMulti()))
				.build()
			);
		}
	}
}
