package cdm.event.common.validation.datarule;

import cdm.base.staticdata.party.Account;
import cdm.base.staticdata.party.PartyReferencePayerReceiver;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaAccount;
import cdm.event.common.BillingSummary;
import cdm.event.common.RecordAmountTypeEnum;
import cdm.event.common.Transfer;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("BillingSummaryAccountTotal")
@ImplementedBy(BillingSummaryAccountTotal.Default.class)
public interface BillingSummaryAccountTotal extends Validator<BillingSummary> {
	
	String NAME = "BillingSummaryAccountTotal";
	String DEFINITION = "if summaryAmountType = RecordAmountTypeEnum -> AccountTotal then summaryTransfer -> payerReceiver -> payerAccountReference exists and summaryTransfer -> payerReceiver -> receiverAccountReference exists";
	
	ValidationResult<BillingSummary> validate(RosettaPath path, BillingSummary billingSummary);
	
	class Default implements BillingSummaryAccountTotal {
	
		@Override
		public ValidationResult<BillingSummary> validate(RosettaPath path, BillingSummary billingSummary) {
			ComparisonResult result = executeDataRule(billingSummary);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "BillingSummary", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "BillingSummary", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(BillingSummary billingSummary) {
			try {
				if (areEqual(MapperS.of(billingSummary).<RecordAmountTypeEnum>map("getSummaryAmountType", _billingSummary -> _billingSummary.getSummaryAmountType()), MapperS.of(RecordAmountTypeEnum.ACCOUNT_TOTAL), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(billingSummary).<Transfer>map("getSummaryTransfer", _billingSummary -> _billingSummary.getSummaryTransfer()).<PartyReferencePayerReceiver>map("getPayerReceiver", transferBase -> transferBase.getPayerReceiver()).<ReferenceWithMetaAccount>map("getPayerAccountReference", partyReferencePayerReceiver -> partyReferencePayerReceiver.getPayerAccountReference()).<Account>map("getValue", _f->_f.getValue())).and(exists(MapperS.of(billingSummary).<Transfer>map("getSummaryTransfer", _billingSummary -> _billingSummary.getSummaryTransfer()).<PartyReferencePayerReceiver>map("getPayerReceiver", transferBase -> transferBase.getPayerReceiver()).<ReferenceWithMetaAccount>map("getReceiverAccountReference", partyReferencePayerReceiver -> partyReferencePayerReceiver.getReceiverAccountReference()).<Account>map("getValue", _f->_f.getValue())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements BillingSummaryAccountTotal {
	
		@Override
		public ValidationResult<BillingSummary> validate(RosettaPath path, BillingSummary billingSummary) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "BillingSummary", path, DEFINITION);
		}
	}
}
