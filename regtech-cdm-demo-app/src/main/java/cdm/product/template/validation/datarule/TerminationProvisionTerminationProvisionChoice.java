package cdm.product.template.validation.datarule;

import cdm.product.template.TerminationProvision;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ChoiceRuleValidationMethod;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("TerminationProvisionTerminationProvisionChoice")
@ImplementedBy(TerminationProvisionTerminationProvisionChoice.Default.class)
public interface TerminationProvisionTerminationProvisionChoice extends Validator<TerminationProvision> {
	
	String NAME = "TerminationProvisionTerminationProvisionChoice";
	String DEFINITION = "required choice cancelableProvision, extendibleProvision, evergreenProvision, earlyTerminationProvision";
	
	ValidationResult<TerminationProvision> validate(RosettaPath path, TerminationProvision terminationProvision);
	
	class Default implements TerminationProvisionTerminationProvisionChoice {
	
		@Override
		public ValidationResult<TerminationProvision> validate(RosettaPath path, TerminationProvision terminationProvision) {
			ComparisonResult result = executeDataRule(terminationProvision);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TerminationProvision", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "TerminationProvision", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(TerminationProvision terminationProvision) {
			try {
				return choice(MapperS.of(terminationProvision), Arrays.asList("cancelableProvision", "extendibleProvision", "evergreenProvision", "earlyTerminationProvision"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TerminationProvisionTerminationProvisionChoice {
	
		@Override
		public ValidationResult<TerminationProvision> validate(RosettaPath path, TerminationProvision terminationProvision) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TerminationProvision", path, DEFINITION);
		}
	}
}
