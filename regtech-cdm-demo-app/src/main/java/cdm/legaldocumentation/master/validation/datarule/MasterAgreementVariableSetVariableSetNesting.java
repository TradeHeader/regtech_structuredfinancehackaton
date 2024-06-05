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
@RosettaDataRule("MasterAgreementVariableSetVariableSetNesting")
@ImplementedBy(MasterAgreementVariableSetVariableSetNesting.Default.class)
public interface MasterAgreementVariableSetVariableSetNesting extends Validator<MasterAgreementVariableSet> {
	
	String NAME = "MasterAgreementVariableSetVariableSetNesting";
	String DEFINITION = "if variableSet -> variableSet exists then variableSet -> variableSet -> variableSet is absent";
	
	ValidationResult<MasterAgreementVariableSet> validate(RosettaPath path, MasterAgreementVariableSet masterAgreementVariableSet);
	
	class Default implements MasterAgreementVariableSetVariableSetNesting {
	
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
				if (exists(MapperS.of(masterAgreementVariableSet).<MasterAgreementVariableSet>mapC("getVariableSet", _masterAgreementVariableSet -> _masterAgreementVariableSet.getVariableSet()).<MasterAgreementVariableSet>mapC("getVariableSet", _masterAgreementVariableSet -> _masterAgreementVariableSet.getVariableSet())).getOrDefault(false)) {
					return notExists(MapperS.of(masterAgreementVariableSet).<MasterAgreementVariableSet>mapC("getVariableSet", _masterAgreementVariableSet -> _masterAgreementVariableSet.getVariableSet()).<MasterAgreementVariableSet>mapC("getVariableSet", _masterAgreementVariableSet -> _masterAgreementVariableSet.getVariableSet()).<MasterAgreementVariableSet>mapC("getVariableSet", _masterAgreementVariableSet -> _masterAgreementVariableSet.getVariableSet()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements MasterAgreementVariableSetVariableSetNesting {
	
		@Override
		public ValidationResult<MasterAgreementVariableSet> validate(RosettaPath path, MasterAgreementVariableSet masterAgreementVariableSet) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MasterAgreementVariableSet", path, DEFINITION);
		}
	}
}
