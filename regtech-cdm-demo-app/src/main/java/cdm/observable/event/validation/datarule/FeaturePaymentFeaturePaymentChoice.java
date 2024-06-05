package cdm.observable.event.validation.datarule;

import cdm.observable.event.FeaturePayment;
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
@RosettaDataRule("FeaturePaymentFeaturePaymentChoice")
@ImplementedBy(FeaturePaymentFeaturePaymentChoice.Default.class)
public interface FeaturePaymentFeaturePaymentChoice extends Validator<FeaturePayment> {
	
	String NAME = "FeaturePaymentFeaturePaymentChoice";
	String DEFINITION = "required choice levelPercentage, amount";
	
	ValidationResult<FeaturePayment> validate(RosettaPath path, FeaturePayment featurePayment);
	
	class Default implements FeaturePaymentFeaturePaymentChoice {
	
		@Override
		public ValidationResult<FeaturePayment> validate(RosettaPath path, FeaturePayment featurePayment) {
			ComparisonResult result = executeDataRule(featurePayment);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FeaturePayment", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "FeaturePayment", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(FeaturePayment featurePayment) {
			try {
				return choice(MapperS.of(featurePayment), Arrays.asList("levelPercentage", "amount"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements FeaturePaymentFeaturePaymentChoice {
	
		@Override
		public ValidationResult<FeaturePayment> validate(RosettaPath path, FeaturePayment featurePayment) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FeaturePayment", path, DEFINITION);
		}
	}
}
