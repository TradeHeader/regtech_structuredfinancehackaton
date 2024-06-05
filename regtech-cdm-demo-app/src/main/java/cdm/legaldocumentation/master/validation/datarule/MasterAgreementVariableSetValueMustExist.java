package cdm.legaldocumentation.master.validation.datarule;

import cdm.legaldocumentation.master.MasterAgreementVariableSet;
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
@RosettaDataRule("MasterAgreementVariableSetValueMustExist")
@ImplementedBy(MasterAgreementVariableSetValueMustExist.Default.class)
public interface MasterAgreementVariableSetValueMustExist extends Validator<MasterAgreementVariableSet> {
	
	String NAME = "MasterAgreementVariableSetValueMustExist";
	String DEFINITION = "if value exists then name exists";
	
	ValidationResult<MasterAgreementVariableSet> validate(RosettaPath path, MasterAgreementVariableSet masterAgreementVariableSet);
	
	class Default implements MasterAgreementVariableSetValueMustExist {
	
		@Override
		public ValidationResult<MasterAgreementVariableSet> validate(RosettaPath path, MasterAgreementVariableSet masterAgreementVariableSet) {
			ComparisonResult result = executeDataRule(masterAgreementVariableSet);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MasterAgreementVariableSet", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "MasterAgreementVariableSet", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(MasterAgreementVariableSet masterAgreementVariableSet) {
			try {
				if (exists(MapperS.of(masterAgreementVariableSet).<String>map("getValue", _masterAgreementVariableSet -> _masterAgreementVariableSet.getValue())).getOrDefault(false)) {
					return exists(MapperS.of(masterAgreementVariableSet).<String>map("getName", _masterAgreementVariableSet -> _masterAgreementVariableSet.getName()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements MasterAgreementVariableSetValueMustExist {
	
		@Override
		public ValidationResult<MasterAgreementVariableSet> validate(RosettaPath path, MasterAgreementVariableSet masterAgreementVariableSet) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MasterAgreementVariableSet", path, DEFINITION);
		}
	}
}
