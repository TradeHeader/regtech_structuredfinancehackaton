package cdm.event.common.validation.datarule;

import cdm.event.common.ContractFormationInstruction;
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
@RosettaDataRule("ContractFormationInstructionExecutedAgreements")
@ImplementedBy(ContractFormationInstructionExecutedAgreements.Default.class)
public interface ContractFormationInstructionExecutedAgreements extends Validator<ContractFormationInstruction> {
	
	String NAME = "ContractFormationInstructionExecutedAgreements";
	String DEFINITION = "if legalAgreement exists then legalAgreement -> agreementDate exists";
	
	ValidationResult<ContractFormationInstruction> validate(RosettaPath path, ContractFormationInstruction contractFormationInstruction);
	
	class Default implements ContractFormationInstructionExecutedAgreements {
	
		@Override
		public ValidationResult<ContractFormationInstruction> validate(RosettaPath path, ContractFormationInstruction contractFormationInstruction) {
			ComparisonResult result = executeDataRule(contractFormationInstruction);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ContractFormationInstruction", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ContractFormationInstruction", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ContractFormationInstruction contractFormationInstruction) {
			try {
				if (exists(MapperS.of(contractFormationInstruction).<LegalAgreement>mapC("getLegalAgreement", _contractFormationInstruction -> _contractFormationInstruction.getLegalAgreement())).getOrDefault(false)) {
					return exists(MapperS.of(contractFormationInstruction).<LegalAgreement>mapC("getLegalAgreement", _contractFormationInstruction -> _contractFormationInstruction.getLegalAgreement()).<Date>map("getAgreementDate", legalAgreementBase -> legalAgreementBase.getAgreementDate()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ContractFormationInstructionExecutedAgreements {
	
		@Override
		public ValidationResult<ContractFormationInstruction> validate(RosettaPath path, ContractFormationInstruction contractFormationInstruction) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ContractFormationInstruction", path, DEFINITION);
		}
	}
}
