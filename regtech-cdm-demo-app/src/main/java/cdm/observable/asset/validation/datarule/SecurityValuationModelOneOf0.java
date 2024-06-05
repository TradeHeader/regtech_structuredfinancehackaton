package cdm.observable.asset.validation.datarule;

import cdm.observable.asset.SecurityValuationModel;
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
@RosettaDataRule("SecurityValuationModelOneOf0")
@ImplementedBy(SecurityValuationModelOneOf0.Default.class)
public interface SecurityValuationModelOneOf0 extends Validator<SecurityValuationModel> {
	
	String NAME = "SecurityValuationModelOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<SecurityValuationModel> validate(RosettaPath path, SecurityValuationModel securityValuationModel);
	
	class Default implements SecurityValuationModelOneOf0 {
	
		@Override
		public ValidationResult<SecurityValuationModel> validate(RosettaPath path, SecurityValuationModel securityValuationModel) {
			ComparisonResult result = executeDataRule(securityValuationModel);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "SecurityValuationModel", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "SecurityValuationModel", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(SecurityValuationModel securityValuationModel) {
			try {
				return choice(MapperS.of(securityValuationModel), Arrays.asList("bondValuationModel", "unitContractValuationModel"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements SecurityValuationModelOneOf0 {
	
		@Override
		public ValidationResult<SecurityValuationModel> validate(RosettaPath path, SecurityValuationModel securityValuationModel) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "SecurityValuationModel", path, DEFINITION);
		}
	}
}
