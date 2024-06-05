package cdm.event.common.functions;

import cdm.event.common.BusinessEvent;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.TermsChangeInstruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.product.common.settlement.Cashflow;
import cdm.product.common.settlement.CashflowType;
import cdm.product.common.settlement.ScheduledTransferEnum;
import cdm.product.template.ContractualProduct;
import cdm.product.template.EconomicTerms;
import cdm.product.template.Payout;
import cdm.product.template.Product;
import cdm.product.template.TradableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Arrays;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_OnDemandPayment.Qualify_OnDemandPaymentDefault.class)
public abstract class Qualify_OnDemandPayment implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
	@Inject protected FilterClosedTradeStates filterClosedTradeStates;
	@Inject protected FilterOpenTradeStates filterOpenTradeStates;

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

	protected abstract MapperC<? extends TradeState> afterTradeStates(BusinessEvent businessEvent);

	protected abstract MapperC<? extends Cashflow> beforeCashFlow(BusinessEvent businessEvent);

	protected abstract MapperC<? extends Cashflow> afterCashFlow(BusinessEvent businessEvent);

	public static class Qualify_OnDemandPaymentDefault extends Qualify_OnDemandPayment {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = areEqual(MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<ReferenceWithMetaTradeState>map("getBefore", instruction -> instruction.getBefore()).<TradeState>map("getValue", _f->_f.getValue()).resultCount()), MapperS.of(1), CardinalityOperator.All).and(areEqual(MapperS.of(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).resultCount()), MapperS.of(1), CardinalityOperator.All)).and(areEqual(MapperS.of(afterTradeStates(businessEvent).resultCount()), MapperS.of(1), CardinalityOperator.All)).and(onlyExists(Arrays.asList(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", eventInstruction -> eventInstruction.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).<TermsChangeInstruction>map("getTermsChange", primitiveInstruction -> primitiveInstruction.getTermsChange())))).and(greaterThan(MapperS.of(afterCashFlow(businessEvent).resultCount()), MapperS.of(beforeCashFlow(businessEvent).resultCount()), CardinalityOperator.All)).and(areEqual(afterCashFlow(businessEvent).<CashflowType>map("getCashflowType", cashflow -> cashflow.getCashflowType()).<ScheduledTransferEnum>map("getCashflowType", cashflowType -> cashflowType.getCashflowType()), MapperS.of(ScheduledTransferEnum.NET_INTEREST), CardinalityOperator.All)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperC<? extends TradeState> afterTradeStates(BusinessEvent businessEvent) {
			return MapperC.<TradeState>of(filterOpenTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti()));
		}
		
		@Override
		protected MapperC<? extends Cashflow> beforeCashFlow(BusinessEvent businessEvent) {
			return MapperS.of(MapperC.of(filterClosedTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti())).get()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<Cashflow>mapC("getCashflow", payout -> payout.getCashflow());
		}
		
		@Override
		protected MapperC<? extends Cashflow> afterCashFlow(BusinessEvent businessEvent) {
			return MapperS.of(MapperC.of(filterOpenTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti())).get()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradableProduct>map("getTradableProduct", trade -> trade.getTradableProduct()).<Product>map("getProduct", tradableProduct -> tradableProduct.getProduct()).<ContractualProduct>map("getContractualProduct", product -> product.getContractualProduct()).<EconomicTerms>map("getEconomicTerms", contractualProduct -> contractualProduct.getEconomicTerms()).<Payout>map("getPayout", economicTerms -> economicTerms.getPayout()).<Cashflow>mapC("getCashflow", payout -> payout.getCashflow());
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
