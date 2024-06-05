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
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_RollPrimitiveInstruction.Create_RollPrimitiveInstructionDefault.class)
public abstract class Create_RollPrimitiveInstruction implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_RollTermChangeInstruction create_RollTermChangeInstruction;
	@Inject protected Create_TerminationInstruction create_TerminationInstruction;

	/**
	* @param tradeState The original trade to be rolled.
	* @param effectiveRollDate The date to close and open a new position.
	* @param terminationDate The new termination date.
	* @param priceQuantity The price and quantity of the trade to roll into.
	* @return instruction 
	*/
	public PrimitiveInstruction evaluate(TradeState tradeState, AdjustableOrRelativeDate effectiveRollDate, AdjustableOrRelativeDate terminationDate, List<? extends PriceQuantity> priceQuantity) {
		// pre-conditions
		conditionValidator.validate(() -> exists(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct())),
			"Only a contractual product can be rolled.");
		
		PrimitiveInstruction.PrimitiveInstructionBuilder instructionBuilder = doEvaluate(tradeState, effectiveRollDate, terminationDate, priceQuantity);
		
		final PrimitiveInstruction instruction;
		if (instructionBuilder == null) {
			instruction = null;
		} else {
			instruction = instructionBuilder.build();
			objectValidator.validate(PrimitiveInstruction.class, instruction);
		}
		
		return instruction;
	}

	protected abstract PrimitiveInstruction.PrimitiveInstructionBuilder doEvaluate(TradeState tradeState, AdjustableOrRelativeDate effectiveRollDate, AdjustableOrRelativeDate terminationDate, List<? extends PriceQuantity> priceQuantity);

	public static class Create_RollPrimitiveInstructionDefault extends Create_RollPrimitiveInstruction {
		@Override
		protected PrimitiveInstruction.PrimitiveInstructionBuilder doEvaluate(TradeState tradeState, AdjustableOrRelativeDate effectiveRollDate, AdjustableOrRelativeDate terminationDate, List<? extends PriceQuantity> priceQuantity) {
			if (priceQuantity == null) {
				priceQuantity = Collections.emptyList();
			}
			PrimitiveInstruction.PrimitiveInstructionBuilder instruction = PrimitiveInstruction.builder();
			return assignOutput(instruction, tradeState, effectiveRollDate, terminationDate, priceQuantity);
		}
		
		protected PrimitiveInstruction.PrimitiveInstructionBuilder assignOutput(PrimitiveInstruction.PrimitiveInstructionBuilder instruction, TradeState tradeState, AdjustableOrRelativeDate effectiveRollDate, AdjustableOrRelativeDate terminationDate, List<? extends PriceQuantity> priceQuantity) {
			instruction
				.getOrCreateSplit()
				.setBreakdown(MapperC.<PrimitiveInstruction>of(MapperS.of(create_TerminationInstruction.evaluate(tradeState))).getMulti());
			
			instruction
				.getOrCreateSplit()
				.addBreakdown(MapperC.<PrimitiveInstruction>of(MapperS.of(PrimitiveInstruction.builder()
					.setQuantityChange(QuantityChangeInstruction.builder()
						.setChange(new ArrayList(priceQuantity))
						.setDirection(QuantityChangeDirectionEnum.REPLACE)
						.setLotIdentifier(Collections.<Identifier>emptyList())
						.build()
					)
					.setTermsChange(create_RollTermChangeInstruction.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).get(), effectiveRollDate, terminationDate))
					.build()
				)).getMulti());
			
			return Optional.ofNullable(instruction)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
