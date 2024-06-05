package cdm.product.common.settlement.validation.datarule;

import cdm.product.common.settlement.PrincipalPayment;
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
@RosettaDataRule("PrincipalPaymentPrincipalAmount")
@ImplementedBy(PrincipalPaymentPrincipalAmount.Default.class)
public interface PrincipalPaymentPrincipalAmount extends Validator<PrincipalPayment> {
	
	String NAME = "PrincipalPaymentPrincipalAmount";
	String DEFINITION = "required choice principalAmount, presentValuePrincipalAmount";
	
	ValidationResult<PrincipalPayment> validate(RosettaPath path, PrincipalPayment principalPayment);
	
	class Default implements PrincipalPaymentPrincipalAmount {
	
		@Override
		public ValidationResult<PrincipalPayment> validate(RosettaPath path, PrincipalPayment principalPayment) {
			ComparisonResult result = executeDataRule(principalPayment);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PrincipalPayment", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PrincipalPayment", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PrincipalPayment principalPayment) {
			try {
				return choice(MapperS.of(principalPayment), Arrays.asList("principalAmount", "presentValuePrincipalAmount"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PrincipalPaymentPrincipalAmount {
	
		@Override
		public ValidationResult<PrincipalPayment> validate(RosettaPath path, PrincipalPayment principalPayment) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PrincipalPayment", path, DEFINITION);
		}
	}
}
