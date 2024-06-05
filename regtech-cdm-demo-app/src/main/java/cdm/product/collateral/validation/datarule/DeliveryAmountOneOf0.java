package cdm.product.collateral.validation.datarule;

import cdm.product.collateral.DeliveryAmount;
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
@RosettaDataRule("DeliveryAmountOneOf0")
@ImplementedBy(DeliveryAmountOneOf0.Default.class)
public interface DeliveryAmountOneOf0 extends Validator<DeliveryAmount> {
	
	String NAME = "DeliveryAmountOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<DeliveryAmount> validate(RosettaPath path, DeliveryAmount deliveryAmount);
	
	class Default implements DeliveryAmountOneOf0 {
	
		@Override
		public ValidationResult<DeliveryAmount> validate(RosettaPath path, DeliveryAmount deliveryAmount) {
			ComparisonResult result = executeDataRule(deliveryAmount);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DeliveryAmount", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "DeliveryAmount", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(DeliveryAmount deliveryAmount) {
			try {
				return choice(MapperS.of(deliveryAmount), Arrays.asList("standardElection", "customElection"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements DeliveryAmountOneOf0 {
	
		@Override
		public ValidationResult<DeliveryAmount> validate(RosettaPath path, DeliveryAmount deliveryAmount) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "DeliveryAmount", path, DEFINITION);
		}
	}
}
