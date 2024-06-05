package cdm.event.common.validation.datarule;

import cdm.event.common.TransferExpression;
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
@RosettaDataRule("TransferExpressionOneOf0")
@ImplementedBy(TransferExpressionOneOf0.Default.class)
public interface TransferExpressionOneOf0 extends Validator<TransferExpression> {
	
	String NAME = "TransferExpressionOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<TransferExpression> validate(RosettaPath path, TransferExpression transferExpression);
	
	class Default implements TransferExpressionOneOf0 {
	
		@Override
		public ValidationResult<TransferExpression> validate(RosettaPath path, TransferExpression transferExpression) {
			ComparisonResult result = executeDataRule(transferExpression);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TransferExpression", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "TransferExpression", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(TransferExpression transferExpression) {
			try {
				return choice(MapperS.of(transferExpression), Arrays.asList("priceTransfer", "scheduledTransfer"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TransferExpressionOneOf0 {
	
		@Override
		public ValidationResult<TransferExpression> validate(RosettaPath path, TransferExpression transferExpression) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "TransferExpression", path, DEFINITION);
		}
	}
}
