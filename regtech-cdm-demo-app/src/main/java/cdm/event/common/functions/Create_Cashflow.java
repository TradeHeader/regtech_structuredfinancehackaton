package cdm.event.common.functions;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.UnitType;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.product.common.settlement.Cashflow;
import cdm.product.common.settlement.Cashflow.CashflowBuilder;
import cdm.product.common.settlement.CashflowType;
import cdm.product.common.settlement.PaymentDiscounting;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementDate;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.math.BigDecimal;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_Cashflow.Create_CashflowDefault.class)
public abstract class Create_Cashflow implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param amount 
	* @param currency 
	* @param settlementDate 
	* @param payerReceiver 
	* @param cashflowType 
	* @param paymentDiscounting 
	* @return cashflow 
	*/
	public Cashflow evaluate(BigDecimal amount, String currency, SettlementDate settlementDate, PayerReceiver payerReceiver, CashflowType cashflowType, PaymentDiscounting paymentDiscounting) {
		Cashflow.CashflowBuilder cashflowBuilder = doEvaluate(amount, currency, settlementDate, payerReceiver, cashflowType, paymentDiscounting);
		
		final Cashflow cashflow;
		if (cashflowBuilder == null) {
			cashflow = null;
		} else {
			cashflow = cashflowBuilder.build();
			objectValidator.validate(Cashflow.class, cashflow);
		}
		
		return cashflow;
	}

	protected abstract Cashflow.CashflowBuilder doEvaluate(BigDecimal amount, String currency, SettlementDate settlementDate, PayerReceiver payerReceiver, CashflowType cashflowType, PaymentDiscounting paymentDiscounting);

	public static class Create_CashflowDefault extends Create_Cashflow {
		@Override
		protected Cashflow.CashflowBuilder doEvaluate(BigDecimal amount, String currency, SettlementDate settlementDate, PayerReceiver payerReceiver, CashflowType cashflowType, PaymentDiscounting paymentDiscounting) {
			Cashflow.CashflowBuilder cashflow = Cashflow.builder();
			return assignOutput(cashflow, amount, currency, settlementDate, payerReceiver, cashflowType, paymentDiscounting);
		}
		
		protected Cashflow.CashflowBuilder assignOutput(Cashflow.CashflowBuilder cashflow, BigDecimal amount, String currency, SettlementDate settlementDate, PayerReceiver payerReceiver, CashflowType cashflowType, PaymentDiscounting paymentDiscounting) {
			cashflow
				.getOrCreateSettlementTerms()
				.setSettlementDate(settlementDate);
			
			cashflow
				.setPayerReceiver(payerReceiver);
			
			cashflow
				.setCashflowType(cashflowType);
			
			cashflow
				.setPaymentDiscounting(paymentDiscounting);
			
			cashflow
				.setPriceQuantity(ResolvablePriceQuantity.builder()
					.setQuantityScheduleValue(NonNegativeQuantitySchedule.builder()
						.setValue(amount)
						.setUnit(UnitType.builder()
							.setCurrencyValue(currency)
							.build()
						)
						.build()
					)
					.build()
				);
			
			return Optional.ofNullable(cashflow)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
