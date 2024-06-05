package cdm.event.common.validation;

import cdm.base.staticdata.party.Party;
import cdm.event.common.BillingRecord;
import cdm.event.common.BillingSummary;
import cdm.event.common.SecurityLendingInvoice;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SecurityLendingInvoiceValidator implements Validator<SecurityLendingInvoice> {

	private List<ComparisonResult> getComparisonResults(SecurityLendingInvoice o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("sendingParty", (Party) o.getSendingParty() != null ? 1 : 0, 1, 1), 
				checkCardinality("receivingParty", (Party) o.getReceivingParty() != null ? 1 : 0, 1, 1), 
				checkCardinality("billingStartDate", (Date) o.getBillingStartDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("billingEndDate", (Date) o.getBillingEndDate() != null ? 1 : 0, 1, 1), 
				checkCardinality("billingRecord", (List<? extends BillingRecord>) o.getBillingRecord() == null ? 0 : ((List<? extends BillingRecord>) o.getBillingRecord()).size(), 1, 0), 
				checkCardinality("billingSummary", (List<? extends BillingSummary>) o.getBillingSummary() == null ? 0 : ((List<? extends BillingSummary>) o.getBillingSummary()).size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<SecurityLendingInvoice> validate(RosettaPath path, SecurityLendingInvoice o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SecurityLendingInvoice", ValidationType.CARDINALITY, "SecurityLendingInvoice", path, "", error);
		}
		return success("SecurityLendingInvoice", ValidationType.CARDINALITY, "SecurityLendingInvoice", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SecurityLendingInvoice o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SecurityLendingInvoice", ValidationType.CARDINALITY, "SecurityLendingInvoice", path, "", res.getError());
				}
				return success("SecurityLendingInvoice", ValidationType.CARDINALITY, "SecurityLendingInvoice", path, "");
			})
			.collect(toList());
	}

}
