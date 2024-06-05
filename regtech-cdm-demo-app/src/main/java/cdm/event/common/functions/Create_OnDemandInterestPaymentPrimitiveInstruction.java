package cdm.event.common.functions;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.UnitType;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.PrimitiveInstruction.PrimitiveInstructionBuilder;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.observable.asset.Money;
import cdm.product.asset.InterestRatePayout;
import cdm.product.common.settlement.Cashflow;
import cdm.product.common.settlement.CashflowType;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.ScheduledTransferEnum;
import cdm.product.common.settlement.SettlementDate;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_OnDemandInterestPaymentPrimitiveInstruction.Create_OnDemandInterestPaymentPrimitiveInstructionDefault.class)
public abstract class Create_OnDemandInterestPaymentPrimitiveInstruction implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_Cashflow create_Cashflow;
	@Inject protected Create_CashflowTermsChangeInstruction create_CashflowTermsChangeInstruction;

	/**
	* @param tradeState The original trade to be modified.
	* @param interestAmount 
	* @param settlementDate 
	* @return instruction Result is a Terms Change Instruction.
	*/
	public PrimitiveInstruction evaluate(TradeState tradeState, Money interestAmount, SettlementDate settlementDate) {
		// pre-conditions
		conditionValidator.validate(() -> exists(interestRatePayout(tradeState, interestAmount, settlementDate)),
			"Only a contractual product with a single interest rate payout can have an on-demand interest payment.");
		
		conditionValidator.validate(() -> areEqual(MapperS.of(interestAmount).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()), interestRatePayout(tradeState, interestAmount, settlementDate).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<ReferenceWithMetaNonNegativeQuantitySchedule>map("getQuantitySchedule", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantitySchedule()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()), CardinalityOperator.All),
			"The currency of the interest amount must match the currency of the original interest rate payout.");
		
		PrimitiveInstruction.PrimitiveInstructionBuilder instructionBuilder = doEvaluate(tradeState, interestAmount, settlementDate);
		
		final PrimitiveInstruction instruction;
		if (instructionBuilder == null) {
			instruction = null;
		} else {
			instruction = instructionBuilder.build();
			objectValidator.validate(PrimitiveInstruction.class, instruction);
		}
		
		return instruction;
	}

	protected abstract PrimitiveInstruction.PrimitiveInstructionBuilder doEvaluate(TradeState tradeState, Money interestAmount, SettlementDate settlementDate);

	protected abstract MapperS<? extends InterestRatePayout> interestRatePayout(TradeState tradeState, Money interestAmount, SettlementDate settlementDate);

	protected abstract MapperS<? extends Cashflow> cashflow(TradeState tradeState, Money interestAmount, SettlementDate settlementDate);

	public static class Create_OnDemandInterestPaymentPrimitiveInstructionDefault extends Create_OnDemandInterestPaymentPrimitiveInstruction {
		@Override
		protected PrimitiveInstruction.PrimitiveInstructionBuilder doEvaluate(TradeState tradeState, Money interestAmount, SettlementDate settlementDate) {
			PrimitiveInstruction.PrimitiveInstructionBuilder instruction = PrimitiveInstruction.builder();
			return assignOutput(instruction, tradeState, interestAmount, settlementDate);
		}
		
		protected PrimitiveInstruction.PrimitiveInstructionBuilder assignOutput(PrimitiveInstruction.PrimitiveInstructionBuilder instruction, TradeState tradeState, Money interestAmount, SettlementDate settlementDate) {
			instruction = toBuilder(PrimitiveInstruction.builder()
				.setTermsChange(create_CashflowTermsChangeInstruction.evaluate(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).get(), cashflow(tradeState, interestAmount, settlementDate).get()))
				.build()
			);
			
			return Optional.ofNullable(instruction)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends InterestRatePayout> interestRatePayout(TradeState tradeState, Money interestAmount, SettlementDate settlementDate) {
			return MapperS.of(MapperS.of(tradeState).<Trade>map("getTrade", _tradeState -> _tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<InterestRatePayout>mapC("getInterestRatePayout", payout -> payout.getInterestRatePayout()).get());
		}
		
		@Override
		protected MapperS<? extends Cashflow> cashflow(TradeState tradeState, Money interestAmount, SettlementDate settlementDate) {
			return MapperS.of(create_Cashflow.evaluate(MapperS.of(interestAmount).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()).get(), interestRatePayout(tradeState, interestAmount, settlementDate).<ResolvablePriceQuantity>map("getPriceQuantity", payoutBase -> payoutBase.getPriceQuantity()).<ReferenceWithMetaNonNegativeQuantitySchedule>map("getQuantitySchedule", resolvablePriceQuantity -> resolvablePriceQuantity.getQuantitySchedule()).<NonNegativeQuantitySchedule>map("getValue", _f->_f.getValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()).get(), settlementDate, interestRatePayout(tradeState, interestAmount, settlementDate).<PayerReceiver>map("getPayerReceiver", payoutBase -> payoutBase.getPayerReceiver()).get(), CashflowType.builder()
				.setCashflowType(ScheduledTransferEnum.NET_INTEREST)
				.build()
			, null));
		}
	}
}
