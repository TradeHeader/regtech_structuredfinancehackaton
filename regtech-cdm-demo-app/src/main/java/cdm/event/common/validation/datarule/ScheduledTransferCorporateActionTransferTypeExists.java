package cdm.event.common.validation.datarule;

import cdm.event.common.CorporateActionTypeEnum;
import cdm.event.common.ScheduledTransfer;
import cdm.product.common.settlement.ScheduledTransferEnum;
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
@RosettaDataRule("ScheduledTransferCorporateActionTransferTypeExists")
@ImplementedBy(ScheduledTransferCorporateActionTransferTypeExists.Default.class)
public interface ScheduledTransferCorporateActionTransferTypeExists extends Validator<ScheduledTransfer> {
	
	String NAME = "ScheduledTransferCorporateActionTransferTypeExists";
	String DEFINITION = "if transferType = ScheduledTransferEnum -> CorporateAction then corporateActionTransferType exists";
	
	ValidationResult<ScheduledTransfer> validate(RosettaPath path, ScheduledTransfer scheduledTransfer);
	
	class Default implements ScheduledTransferCorporateActionTransferTypeExists {
	
		@Override
		public ValidationResult<ScheduledTransfer> validate(RosettaPath path, ScheduledTransfer scheduledTransfer) {
			ComparisonResult result = executeDataRule(scheduledTransfer);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ScheduledTransfer", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ScheduledTransfer", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ScheduledTransfer scheduledTransfer) {
			try {
				if (areEqual(MapperS.of(scheduledTransfer).<ScheduledTransferEnum>map("getTransferType", _scheduledTransfer -> _scheduledTransfer.getTransferType()), MapperS.of(ScheduledTransferEnum.CORPORATE_ACTION), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(scheduledTransfer).<CorporateActionTypeEnum>map("getCorporateActionTransferType", _scheduledTransfer -> _scheduledTransfer.getCorporateActionTransferType()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ScheduledTransferCorporateActionTransferTypeExists {
	
		@Override
		public ValidationResult<ScheduledTransfer> validate(RosettaPath path, ScheduledTransfer scheduledTransfer) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ScheduledTransfer", path, DEFINITION);
		}
	}
}
