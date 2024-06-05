package cdm.legaldocumentation.contract.validation.datarule;

import cdm.legaldocumentation.contract.Agreement;
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
@RosettaDataRule("AgreementOneOf0")
@ImplementedBy(AgreementOneOf0.Default.class)
public interface AgreementOneOf0 extends Validator<Agreement> {
	
	String NAME = "AgreementOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<Agreement> validate(RosettaPath path, Agreement agreement);
	
	class Default implements AgreementOneOf0 {
	
		@Override
		public ValidationResult<Agreement> validate(RosettaPath path, Agreement agreement) {
			ComparisonResult result = executeDataRule(agreement);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Agreement", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Agreement", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Agreement agreement) {
			try {
				return choice(MapperS.of(agreement), Arrays.asList("creditSupportAgreementElections", "collateralTransferAgreementElections", "securityAgreementElections", "masterAgreementSchedule", "transactionAdditionalTerms"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AgreementOneOf0 {
	
		@Override
		public ValidationResult<Agreement> validate(RosettaPath path, Agreement agreement) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Agreement", path, DEFINITION);
		}
	}
}
