package cdm.event.common.functions;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.QuantityChangeDirectionEnum;
import cdm.base.math.UnitType;
import cdm.base.math.metafields.FieldWithMetaNonNegativeQuantitySchedule;
import cdm.base.staticdata.identifier.Identifier;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.QuantityChangeInstruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.workflow.EventInstruction;
import cdm.event.workflow.EventInstruction.EventInstructionBuilder;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(ResolveRepurchaseTransferInstruction.ResolveRepurchaseTransferInstructionDefault.class)
public abstract class ResolveRepurchaseTransferInstruction implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param tradeState 
	* @param repurchaseDate 
	* @return repurchaseInstruction 
	*/
	public EventInstruction evaluate(TradeState tradeState, Date repurchaseDate) {
		EventInstruction.EventInstructionBuilder repurchaseInstructionBuilder = doEvaluate(tradeState, repurchaseDate);
		
		final EventInstruction repurchaseInstruction;
		if (repurchaseInstructionBuilder == null) {
			repurchaseInstruction = null;
		} else {
			repurchaseInstruction = repurchaseInstructionBuilder.build();
			objectValidator.validate(EventInstruction.class, repurchaseInstruction);
		}
		
		return repurchaseInstruction;
	}

	protected abstract EventInstruction.EventInstructionBuilder doEvaluate(TradeState tradeState, Date repurchaseDate);

	protected abstract MapperC<? extends NonNegativeQuantitySchedule> changeQuantity(TradeState tradeState, Date repurchaseDate);

	protected abstract MapperS<? extends PriceQuantity> changePriceQuantity(TradeState tradeState, Date repurchaseDate);

	public static class ResolveRepurchaseTransferInstructionDefault extends ResolveRepurchaseTransferInstruction {
		@Override
		protected EventInstruction.EventInstructionBuilder doEvaluate(TradeState tradeState, Date repurchaseDate) {
			EventInstruction.EventInstructionBuilder repurchaseInstruction = EventInstruction.builder();
			return assignOutput(repurchaseInstruction, tradeState, repurchaseDate);
		}
		
		protected EventInstruction.EventInstructionBuilder assignOutput(EventInstruction.EventInstructionBuilder repurchaseInstruction, TradeState tradeState, Date repurchaseDate) {
			repurchaseInstruction
				.setIntent(EventIntentEnum.REPURCHASE);
			
			repurchaseInstruction
				.getOrCreateInstruction(0)
				.setBeforeValue(tradeState);
			
			repurchaseInstruction
				.getOrCreateInstruction(0)
				.getOrCreatePrimitiveInstruction()
				.setQuantityChange(QuantityChangeInstruction.builder()
					.setChange(new ArrayList<>(changePriceQuantity(tradeState, repurchaseDate).getMulti()))
					.setDirection(QuantityChangeDirectionEnum.REPLACE)
					.setLotIdentifier(Collections.<Identifier>emptyList())
					.build()
				);
			
			return Optional.ofNullable(repurchaseInstruction)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperC<? extends NonNegativeQuantitySchedule> changeQuantity(TradeState tradeState, Date repurchaseDate) {
			final MapperC<NonNegativeQuantitySchedule> thenResult = MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue())
				.mapItem(item -> MapperS.of(NonNegativeQuantitySchedule.builder()
					.setValue(new BigDecimal("0.0"))
					.setUnit(item.<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).get())
					.build()
				));
			return distinct(thenResult);
		}
		
		@Override
		protected MapperS<? extends PriceQuantity> changePriceQuantity(TradeState tradeState, Date repurchaseDate) {
			return MapperS.of(PriceQuantity.builder()
				.setQuantityValue(new ArrayList<>(changeQuantity(tradeState, repurchaseDate).getMulti()))
				.build()
			);
		}
	}
}
