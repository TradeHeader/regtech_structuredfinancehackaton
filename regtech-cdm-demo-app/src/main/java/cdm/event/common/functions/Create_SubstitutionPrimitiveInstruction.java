package cdm.event.common.functions;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.math.QuantityChangeDirectionEnum;
import cdm.base.staticdata.identifier.Identifier;
import cdm.event.common.CollateralPortfolio;
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
import com.rosetta.model.lib.mapper.MapperS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_SubstitutionPrimitiveInstruction.Create_SubstitutionPrimitiveInstructionDefault.class)
public abstract class Create_SubstitutionPrimitiveInstruction implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_SubstitutionInstruction create_SubstitutionInstruction;

	/**
	* @param tradeState The original trade to be for substitution of collateral.
	* @param effectiveDate The date to close and open a new trade with new collateral.
	* @param newCollateralPortfolio New collateral portfolio to subtitute for the original collateral.
	* @param priceQuantity The price and quantity of the substituted product.
	* @return instruction 
	*/
	public PrimitiveInstruction evaluate(TradeState tradeState, AdjustableOrRelativeDate effectiveDate, CollateralPortfolio newCollateralPortfolio, List<? extends PriceQuantity> priceQuantity) {
		// pre-conditions
		conditionValidator.validate(() -> exists(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct())),
			"Only a security finance contractual product can substitute collateral.");
		
		PrimitiveInstruction.PrimitiveInstructionBuilder instructionBuilder = doEvaluate(tradeState, effectiveDate, newCollateralPortfolio, priceQuantity);
		
		final PrimitiveInstruction instruction;
		if (instructionBuilder == null) {
			instruction = null;
		} else {
			instruction = instructionBuilder.build();
			objectValidator.validate(PrimitiveInstruction.class, instruction);
		}
		
		return instruction;
	}

	protected abstract PrimitiveInstruction.PrimitiveInstructionBuilder doEvaluate(TradeState tradeState, AdjustableOrRelativeDate effectiveDate, CollateralPortfolio newCollateralPortfolio, List<? extends PriceQuantity> priceQuantity);

	public static class Create_SubstitutionPrimitiveInstructionDefault extends Create_SubstitutionPrimitiveInstruction {
		@Override
		protected PrimitiveInstruction.PrimitiveInstructionBuilder doEvaluate(TradeState tradeState, AdjustableOrRelativeDate effectiveDate, CollateralPortfolio newCollateralPortfolio, List<? extends PriceQuantity> priceQuantity) {
			if (priceQuantity == null) {
				priceQuantity = Collections.emptyList();
			}
			PrimitiveInstruction.PrimitiveInstructionBuilder instruction = PrimitiveInstruction.builder();
			return assignOutput(instruction, tradeState, effectiveDate, newCollateralPortfolio, priceQuantity);
		}
		
		protected PrimitiveInstruction.PrimitiveInstructionBuilder assignOutput(PrimitiveInstruction.PrimitiveInstructionBuilder instruction, TradeState tradeState, AdjustableOrRelativeDate effectiveDate, CollateralPortfolio newCollateralPortfolio, List<? extends PriceQuantity> priceQuantity) {
			instruction = toBuilder(PrimitiveInstruction.builder()
				.setQuantityChange(QuantityChangeInstruction.builder()
					.setChange(new ArrayList(priceQuantity))
					.setDirection(QuantityChangeDirectionEnum.REPLACE)
					.setLotIdentifier(Collections.<Identifier>emptyList())
					.build()
				)
				.setTermsChange(create_SubstitutionInstruction.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).get(), effectiveDate, newCollateralPortfolio))
				.build()
			);
			
			return Optional.ofNullable(instruction)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
