package cdm.event.common.functions;

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
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_TerminationInstruction.Create_TerminationInstructionDefault.class)
public abstract class Create_TerminationInstruction implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param tradeState The original trade to be termintaed.
	* @return instruction 
	*/
	public PrimitiveInstruction evaluate(TradeState tradeState) {
		PrimitiveInstruction.PrimitiveInstructionBuilder instructionBuilder = doEvaluate(tradeState);
		
		final PrimitiveInstruction instruction;
		if (instructionBuilder == null) {
			instruction = null;
		} else {
			instruction = instructionBuilder.build();
			objectValidator.validate(PrimitiveInstruction.class, instruction);
		}
		
		return instruction;
	}

	protected abstract PrimitiveInstruction.PrimitiveInstructionBuilder doEvaluate(TradeState tradeState);

	protected abstract MapperC<? extends NonNegativeQuantitySchedule> changeQuantity(TradeState tradeState);

	protected abstract MapperS<? extends PriceQuantity> changePriceQuantity(TradeState tradeState);

	public static class Create_TerminationInstructionDefault extends Create_TerminationInstruction {
		@Override
		protected PrimitiveInstruction.PrimitiveInstructionBuilder doEvaluate(TradeState tradeState) {
			PrimitiveInstruction.PrimitiveInstructionBuilder instruction = PrimitiveInstruction.builder();
			return assignOutput(instruction, tradeState);
		}
		
		protected PrimitiveInstruction.PrimitiveInstructionBuilder assignOutput(PrimitiveInstruction.PrimitiveInstructionBuilder instruction, TradeState tradeState) {
			instruction
				.setQuantityChange(QuantityChangeInstruction.builder()
					.setChange(new ArrayList<>(changePriceQuantity(tradeState).getMulti()))
					.setDirection(QuantityChangeDirectionEnum.REPLACE)
					.setLotIdentifier(Collections.<Identifier>emptyList())
					.build()
				);
			
			return Optional.ofNullable(instruction)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperC<? extends NonNegativeQuantitySchedule> changeQuantity(TradeState tradeState) {
			final MapperC<NonNegativeQuantitySchedule> thenResult = MapperS.of(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).get()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).<FieldWithMetaNonNegativeQuantitySchedule>mapC("getQuantity", priceQuantity -> priceQuantity.getQuantity()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue())
				.mapItem(item -> MapperS.of(NonNegativeQuantitySchedule.builder()
					.setValue(new BigDecimal("0.0"))
					.setUnit(item.<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).get())
					.build()
				));
			return distinct(thenResult);
		}
		
		@Override
		protected MapperS<? extends PriceQuantity> changePriceQuantity(TradeState tradeState) {
			return MapperS.of(PriceQuantity.builder()
				.setQuantityValue(new ArrayList<>(changeQuantity(tradeState).getMulti()))
				.build()
			);
		}
	}
}
