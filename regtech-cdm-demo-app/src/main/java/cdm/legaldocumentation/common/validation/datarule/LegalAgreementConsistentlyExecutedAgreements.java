package cdm.legaldocumentation.common.validation.datarule;

import cdm.legaldocumentation.common.LegalAgreement;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("LegalAgreementConsistentlyExecutedAgreements")
@ImplementedBy(LegalAgreementConsistentlyExecutedAgreements.Default.class)
public interface LegalAgreementConsistentlyExecutedAgreements extends Validator<LegalAgreement> {
	
	String NAME = "LegalAgreementConsistentlyExecutedAgreements";
	String DEFINITION = "if relatedAgreements exists and agreementDate exists then relatedAgreements -> agreementDate exists";
	
	ValidationResult<LegalAgreement> validate(RosettaPath path, LegalAgreement legalAgreement);
	
	class Default implements LegalAgreementConsistentlyExecutedAgreements {
	
		@Override
		public ValidationResult<LegalAgreement> validate(RosettaPath path, LegalAgreement legalAgreement) {
			ComparisonResult result = executeDataRule(legalAgreement);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "LegalAgreement", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "LegalAgreement", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(LegalAgreement legalAgreement) {
			try {
				if (exists(MapperS.of(legalAgreement).<LegalAgreement>mapC("getRelatedAgreements", _legalAgreement -> _legalAgreement.getRelatedAgreements())).and(exists(MapperS.of(legalAgreement).<Date>map("getAgreementDate", legalAgreementBase -> legalAgreementBase.getAgreementDate()))).getOrDefault(false)) {
					return exists(MapperS.of(legalAgreement).<LegalAgreement>mapC("getRelatedAgreements", _legalAgreement -> _legalAgreement.getRelatedAgreements()).<Date>map("getAgreementDate", legalAgreementBase -> legalAgreementBase.getAgreementDate()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements LegalAgreementConsistentlyExecutedAgreements {
	
		@Override
		public ValidationResult<LegalAgreement> validate(RosettaPath path, LegalAgreement legalAgreement) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "LegalAgreement", path, DEFINITION);
		}
	}
}
