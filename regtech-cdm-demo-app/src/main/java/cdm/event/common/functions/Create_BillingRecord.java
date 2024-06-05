package cdm.event.common.functions;

import cdm.event.common.BillingRecord;
import cdm.event.common.BillingRecord.BillingRecordBuilder;
import cdm.event.common.BillingRecordInstruction;
import cdm.event.common.Reset;
import cdm.event.common.TradeState;
import cdm.event.common.Transfer;
import cdm.observable.event.functions.Create_AssetPayoutTradeStateWithObservations;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_BillingRecord.Create_BillingRecordDefault.class)
public abstract class Create_BillingRecord implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_AssetPayoutTradeStateWithObservations create_AssetPayoutTradeStateWithObservations;
	@Inject protected ResolveSecurityFinanceBillingAmount resolveSecurityFinanceBillingAmount;

	/**
	* @param billingInstruction Instruction for creating the billing records contained within the invoice
	* @return billingRecord The billing record
	*/
	public BillingRecord evaluate(BillingRecordInstruction billingInstruction) {
		BillingRecord.BillingRecordBuilder billingRecordBuilder = doEvaluate(billingInstruction);
		
		final BillingRecord billingRecord;
		if (billingRecordBuilder == null) {
			billingRecord = null;
		} else {
			billingRecord = billingRecordBuilder.build();
			objectValidator.validate(BillingRecord.class, billingRecord);
		}
		
		return billingRecord;
	}

	protected abstract BillingRecord.BillingRecordBuilder doEvaluate(BillingRecordInstruction billingInstruction);

	protected abstract MapperS<? extends TradeState> tradeState(BillingRecordInstruction billingInstruction);

	protected abstract MapperS<? extends Transfer> billingAmount(BillingRecordInstruction billingInstruction);

	public static class Create_BillingRecordDefault extends Create_BillingRecord {
		@Override
		protected BillingRecord.BillingRecordBuilder doEvaluate(BillingRecordInstruction billingInstruction) {
			BillingRecord.BillingRecordBuilder billingRecord = BillingRecord.builder();
			return assignOutput(billingRecord, billingInstruction);
		}
		
		protected BillingRecord.BillingRecordBuilder assignOutput(BillingRecord.BillingRecordBuilder billingRecord, BillingRecordInstruction billingInstruction) {
			billingRecord
				.setRecordStartDate(MapperS.of(billingInstruction).<Date>map("getRecordStartDate", billingRecordInstruction -> billingRecordInstruction.getRecordStartDate()).get());
			
			billingRecord
				.setRecordEndDate(MapperS.of(billingInstruction).<Date>map("getRecordEndDate", billingRecordInstruction -> billingRecordInstruction.getRecordEndDate()).get());
			
			billingRecord
				.setTradeStateValue(tradeState(billingInstruction).get());
			
			billingRecord
				.setRecordTransfer(billingAmount(billingInstruction).get());
			
			return Optional.ofNullable(billingRecord)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends TradeState> tradeState(BillingRecordInstruction billingInstruction) {
			return MapperS.of(create_AssetPayoutTradeStateWithObservations.evaluate(billingInstruction));
		}
		
		@Override
		protected MapperS<? extends Transfer> billingAmount(BillingRecordInstruction billingInstruction) {
			return MapperS.of(resolveSecurityFinanceBillingAmount.evaluate(tradeState(billingInstruction).get(), tradeState(billingInstruction).<Reset>mapC("getResetHistory", _tradeState -> _tradeState.getResetHistory()).get(), MapperS.of(billingInstruction).<Date>map("getRecordStartDate", billingRecordInstruction -> billingRecordInstruction.getRecordStartDate()).get(), MapperS.of(billingInstruction).<Date>map("getRecordEndDate", billingRecordInstruction -> billingRecordInstruction.getRecordEndDate()).get(), MapperS.of(billingInstruction).<Date>map("getSettlementDate", billingRecordInstruction -> billingRecordInstruction.getSettlementDate()).get()));
		}
	}
}
