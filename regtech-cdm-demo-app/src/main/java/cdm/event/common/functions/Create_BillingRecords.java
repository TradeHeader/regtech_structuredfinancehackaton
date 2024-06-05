package cdm.event.common.functions;

import cdm.event.common.BillingRecord;
import cdm.event.common.BillingRecord.BillingRecordBuilder;
import cdm.event.common.BillingRecordInstruction;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;


@ImplementedBy(Create_BillingRecords.Create_BillingRecordsDefault.class)
public abstract class Create_BillingRecords implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_BillingRecord create_BillingRecord;

	/**
	* @param billingInstruction Instruction for creating the billing records contained within the invoice
	* @return billingRecord 
	*/
	public List<? extends BillingRecord> evaluate(List<? extends BillingRecordInstruction> billingInstruction) {
		List<BillingRecord.BillingRecordBuilder> billingRecordBuilder = doEvaluate(billingInstruction);
		
		final List<? extends BillingRecord> billingRecord;
		if (billingRecordBuilder == null) {
			billingRecord = null;
		} else {
			billingRecord = billingRecordBuilder.stream().map(BillingRecord::build).collect(Collectors.toList());
			objectValidator.validate(BillingRecord.class, billingRecord);
		}
		
		return billingRecord;
	}

	protected abstract List<BillingRecord.BillingRecordBuilder> doEvaluate(List<? extends BillingRecordInstruction> billingInstruction);

	public static class Create_BillingRecordsDefault extends Create_BillingRecords {
		@Override
		protected List<BillingRecord.BillingRecordBuilder> doEvaluate(List<? extends BillingRecordInstruction> billingInstruction) {
			if (billingInstruction == null) {
				billingInstruction = Collections.emptyList();
			}
			List<BillingRecord.BillingRecordBuilder> billingRecord = new ArrayList<>();
			return assignOutput(billingRecord, billingInstruction);
		}
		
		protected List<BillingRecord.BillingRecordBuilder> assignOutput(List<BillingRecord.BillingRecordBuilder> billingRecord, List<? extends BillingRecordInstruction> billingInstruction) {
			billingRecord.addAll(toBuilder(MapperC.<BillingRecordInstruction>of(billingInstruction)
				.mapItem(item -> MapperS.of(create_BillingRecord.evaluate(item.get()))).getMulti()));
			
			return Optional.ofNullable(billingRecord)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
