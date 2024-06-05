package cdm.product.template.validation.datarule;

import cdm.product.template.SecurityLeg;
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
@RosettaDataRule("SecurityLegSecurityLegChoice")
@ImplementedBy(SecurityLegSecurityLegChoice.Default.class)
public interface SecurityLegSecurityLegChoice extends Validator<SecurityLeg> {
	
	String NAME = "SecurityLegSecurityLegChoice";
	String DEFINITION = "required choice settlementAmount, settlementCurrency";
	
	ValidationResult<SecurityLeg> validate(RosettaPath path, SecurityLeg securityLeg);
	
	class Default implements SecurityLegSecurityLegChoice {
	
		@Override
		public ValidationResult<SecurityLeg> validate(RosettaPath path, SecurityLeg securityLeg) {
			ComparisonResult result = executeDataRule(securityLeg);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "SecurityLeg", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "SecurityLeg", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(SecurityLeg securityLeg) {
			try {
				return choice(MapperS.of(securityLeg), Arrays.asList("settlementAmount", "settlementCurrency"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements SecurityLegSecurityLegChoice {
	
		@Override
		public ValidationResult<SecurityLeg> validate(RosettaPath path, SecurityLeg securityLeg) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "SecurityLeg", path, DEFINITION);
		}
	}
}
