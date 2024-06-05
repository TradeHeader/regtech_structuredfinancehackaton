package cdm.event.common.functions;

import cdm.event.common.BillingRecord;
import cdm.event.common.BillingSummary;
import cdm.event.common.BillingSummary.BillingSummaryBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_BillingSummary.Create_BillingSummaryDefault.class)
public abstract class Create_BillingSummary implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param billingRecord 
	* @return billingSummary 
	*/
	public BillingSummary evaluate(List<? extends BillingRecord> billingRecord) {
		BillingSummary.BillingSummaryBuilder billingSummaryBuilder = doEvaluate(billingRecord);
		
		final BillingSummary billingSummary;
		if (billingSummaryBuilder == null) {
			billingSummary = null;
		} else {
			billingSummary = billingSummaryBuilder.build();
			objectValidator.validate(BillingSummary.class, billingSummary);
		}
		
		return billingSummary;
	}

	protected abstract BillingSummary.BillingSummaryBuilder doEvaluate(List<? extends BillingRecord> billingRecord);

	public static class Create_BillingSummaryDefault extends Create_BillingSummary {
		@Override
		protected BillingSummary.BillingSummaryBuilder doEvaluate(List<? extends BillingRecord> billingRecord) {
			if (billingRecord == null) {
				billingRecord = Collections.emptyList();
			}
			BillingSummary.BillingSummaryBuilder billingSummary = BillingSummary.builder();
			return assignOutput(billingSummary, billingRecord);
		}
		
		protected BillingSummary.BillingSummaryBuilder assignOutput(BillingSummary.BillingSummaryBuilder billingSummary, List<? extends BillingRecord> billingRecord) {
			return Optional.ofNullable(billingSummary)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
