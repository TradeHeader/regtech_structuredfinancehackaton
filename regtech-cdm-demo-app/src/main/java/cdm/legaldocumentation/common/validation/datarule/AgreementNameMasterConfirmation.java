package cdm.legaldocumentation.common.validation.datarule;

import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.master.MasterConfirmationAnnexTypeEnum;
import cdm.legaldocumentation.master.MasterConfirmationTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterConfirmationAnnexTypeEnum;
import cdm.legaldocumentation.master.metafields.FieldWithMetaMasterConfirmationTypeEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
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
@RosettaDataRule("AgreementNameMasterConfirmation")
@ImplementedBy(AgreementNameMasterConfirmation.Default.class)
public interface AgreementNameMasterConfirmation extends Validator<AgreementName> {
	
	String NAME = "AgreementNameMasterConfirmation";
	String DEFINITION = "if masterConfirmationAnnexType exists then masterConfirmationType exists";
	
	ValidationResult<AgreementName> validate(RosettaPath path, AgreementName agreementName);
	
	class Default implements AgreementNameMasterConfirmation {
	
		@Override
		public ValidationResult<AgreementName> validate(RosettaPath path, AgreementName agreementName) {
			ComparisonResult result = executeDataRule(agreementName);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AgreementName", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AgreementName", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AgreementName agreementName) {
			try {
				if (exists(MapperS.of(agreementName).<FieldWithMetaMasterConfirmationAnnexTypeEnum>map("getMasterConfirmationAnnexType", _agreementName -> _agreementName.getMasterConfirmationAnnexType()).<MasterConfirmationAnnexTypeEnum>map("getValue", _f->_f.getValue())).getOrDefault(false)) {
					return exists(MapperS.of(agreementName).<FieldWithMetaMasterConfirmationTypeEnum>map("getMasterConfirmationType", _agreementName -> _agreementName.getMasterConfirmationType()).<MasterConfirmationTypeEnum>map("getValue", _f->_f.getValue()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AgreementNameMasterConfirmation {
	
		@Override
		public ValidationResult<AgreementName> validate(RosettaPath path, AgreementName agreementName) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AgreementName", path, DEFINITION);
		}
	}
}
