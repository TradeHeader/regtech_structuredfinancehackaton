package cdm.event.common.functions;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.math.QuantityChangeDirectionEnum;
import cdm.base.staticdata.identifier.Identifier;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.PrimitiveInstruction.PrimitiveInstructionBuilder;
import cdm.event.common.QuantityChangeInstruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.ContractualProduct;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import cdm.product.template.TradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_CancellationPrimitiveInstruction.Create_CancellationPrimitiveInstructionDefault.class)
public abstract class Create_CancellationPrimitiveInstruction implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_CancellationTermChangeInstruction create_CancellationTermChangeInstruction;
	@Inject protected Create_TerminationInstruction create_TerminationInstruction;

	/**
	* @param tradeState The original trade to be rolled.
	* @param newRepurchasePrice The new repurchase price after the new termination date is set.
	* @param cancellationDate The new termination date.
	* @return instruction 
	*/
	public PrimitiveInstruction evaluate(TradeState tradeState, BigDecimal newRepurchasePrice, AdjustableOrRelativeDate cancellationDate) {
		// pre-conditions
		conditionValidator.validate(() -> exists(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct())),
			"If a contratual product can be cancelled.");
		
		PrimitiveInstruction.PrimitiveInstructionBuilder instructionBuilder = doEvaluate(tradeState, newRepurchasePrice, cancellationDate);
		
		final PrimitiveInstruction instruction;
		if (instructionBuilder == null) {
			instruction = null;
		} else {
			instruction = instructionBuilder.build();
			objectValidator.validate(PrimitiveInstruction.class, instruction);
		}
		
		return instruction;
	}

	protected abstract PrimitiveInstruction.PrimitiveInstructionBuilder doEvaluate(TradeState tradeState, BigDecimal newRepurchasePrice, AdjustableOrRelativeDate cancellationDate);

	public static class Create_CancellationPrimitiveInstructionDefault extends Create_CancellationPrimitiveInstruction {
		@Override
		protected PrimitiveInstruction.PrimitiveInstructionBuilder doEvaluate(TradeState tradeState, BigDecimal newRepurchasePrice, AdjustableOrRelativeDate cancellationDate) {
			PrimitiveInstruction.PrimitiveInstructionBuilder instruction = PrimitiveInstruction.builder();
			return assignOutput(instruction, tradeState, newRepurchasePrice, cancellationDate);
		}
		
		protected PrimitiveInstruction.PrimitiveInstructionBuilder assignOutput(PrimitiveInstruction.PrimitiveInstructionBuilder instruction, TradeState tradeState, BigDecimal newRepurchasePrice, AdjustableOrRelativeDate cancellationDate) {
			instruction
				.getOrCreateSplit()
				.setBreakdown(MapperC.<PrimitiveInstruction>of(MapperS.of(create_TerminationInstruction.evaluate(tradeState))).getMulti());
			
			instruction
				.getOrCreateSplit()
				.addBreakdown(MapperC.<PrimitiveInstruction>of(MapperS.of(PrimitiveInstruction.builder()
					.setQuantityChange(QuantityChangeInstruction.builder()
						.setChange(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<TradeLot>mapC("getTradeLot", tradableProduct -> tradableProduct.getTradeLot()).<PriceQuantity>mapC("getPriceQuantity", tradeLot -> tradeLot.getPriceQuantity()).getMulti())
						.setDirection(QuantityChangeDirectionEnum.REPLACE)
						.setLotIdentifier(Collections.<Identifier>emptyList())
						.build()
					)
					.setTermsChange(create_CancellationTermChangeInstruction.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).get(), cancellationDate))
					.build()
				)).getMulti());
			
			return Optional.ofNullable(instruction)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
