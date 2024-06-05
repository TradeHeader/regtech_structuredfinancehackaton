package cdm.event.common.functions;

import cdm.base.staticdata.party.Party;
import cdm.event.common.BillingInstruction;
import cdm.event.common.BillingRecord;
import cdm.event.common.BillingRecordInstruction;
import cdm.event.common.BillingSummary;
import cdm.event.common.SecurityLendingInvoice;
import cdm.event.common.SecurityLendingInvoice.SecurityLendingInvoiceBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_SecurityLendingInvoice.Create_SecurityLendingInvoiceDefault.class)
public abstract class Create_SecurityLendingInvoice implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_BillingRecords create_BillingRecords;
	@Inject protected Create_BillingSummary create_BillingSummary;

	/**
	* @param instruction Specifies the instructions for creation of a Security Lending billing invoice.
	* @return invoice Produces the Security Lending Invoice
	*/
	public SecurityLendingInvoice evaluate(BillingInstruction instruction) {
		SecurityLendingInvoice.SecurityLendingInvoiceBuilder invoiceBuilder = doEvaluate(instruction);
		
		final SecurityLendingInvoice invoice;
		if (invoiceBuilder == null) {
			invoice = null;
		} else {
			invoice = invoiceBuilder.build();
			objectValidator.validate(SecurityLendingInvoice.class, invoice);
		}
		
		return invoice;
	}

	protected abstract SecurityLendingInvoice.SecurityLendingInvoiceBuilder doEvaluate(BillingInstruction instruction);

	public static class Create_SecurityLendingInvoiceDefault extends Create_SecurityLendingInvoice {
		@Override
		protected SecurityLendingInvoice.SecurityLendingInvoiceBuilder doEvaluate(BillingInstruction instruction) {
			SecurityLendingInvoice.SecurityLendingInvoiceBuilder invoice = SecurityLendingInvoice.builder();
			return assignOutput(invoice, instruction);
		}
		
		protected SecurityLendingInvoice.SecurityLendingInvoiceBuilder assignOutput(SecurityLendingInvoice.SecurityLendingInvoiceBuilder invoice, BillingInstruction instruction) {
			invoice
				.setSendingParty(MapperS.of(instruction).<Party>map("getSendingParty", billingInstruction -> billingInstruction.getSendingParty()).get());
			
			invoice
				.setReceivingParty(MapperS.of(instruction).<Party>map("getReceivingParty", billingInstruction -> billingInstruction.getReceivingParty()).get());
			
			invoice
				.setBillingStartDate(MapperS.of(instruction).<Date>map("getBillingStartDate", billingInstruction -> billingInstruction.getBillingStartDate()).get());
			
			invoice
				.setBillingEndDate(MapperS.of(instruction).<Date>map("getBillingEndDate", billingInstruction -> billingInstruction.getBillingEndDate()).get());
			
			invoice
				.addBillingRecord(create_BillingRecords.evaluate(MapperS.of(instruction).<BillingRecordInstruction>mapC("getBillingRecordInstruction", billingInstruction -> billingInstruction.getBillingRecordInstruction()).getMulti()));
			
			final BillingSummary billingSummary = create_BillingSummary.evaluate(MapperS.of(invoice).<BillingRecord>mapC("getBillingRecord", securityLendingInvoice -> securityLendingInvoice.getBillingRecord()).getMulti());
			invoice
				.addBillingSummary((billingSummary == null ? Collections.<BillingSummary>emptyList() : Collections.singletonList(billingSummary)));
			
			return Optional.ofNullable(invoice)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
