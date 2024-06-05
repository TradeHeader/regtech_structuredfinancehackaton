package cdm.event.common.validation.datarule;

import cdm.event.common.ContractDetails;
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
@RosettaDataRule("ContractDetailsExecutedAgreement")
@ImplementedBy(ContractDetailsExecutedAgreement.Default.class)
public interface ContractDetailsExecutedAgreement extends Validator<ContractDetails> {
	
	String NAME = "ContractDetailsExecutedAgreement";
	String DEFINITION = "if documentation exists then documentation -> agreementDate exists";
	
	ValidationResult<ContractDetails> validate(RosettaPath path, ContractDetails contractDetails);
	
	class Default implements ContractDetailsExecutedAgreement {
	
		@Override
		public ValidationResult<ContractDetails> validate(RosettaPath path, ContractDetails contractDetails) {
			ComparisonResult result = executeDataRule(contractDetails);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ContractDetails", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ContractDetails", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ContractDetails contractDetails) {
			try {
				if (exists(MapperS.of(contractDetails).<LegalAgreement>mapC("getDocumentation", _contractDetails -> _contractDetails.getDocumentation())).getOrDefault(false)) {
					return exists(MapperS.of(contractDetails).<LegalAgreement>mapC("getDocumentation", _contractDetails -> _contractDetails.getDocumentation()).<Date>map("getAgreementDate", legalAgreementBase -> legalAgreementBase.getAgreementDate()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ContractDetailsExecutedAgreement {
	
		@Override
		public ValidationResult<ContractDetails> validate(RosettaPath path, ContractDetails contractDetails) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ContractDetails", path, DEFINITION);
		}
	}
}
