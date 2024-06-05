package cdm.product.asset.validation.datarule;

import cdm.product.asset.RateSpecification;
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
@RosettaDataRule("RateSpecificationOneOf0")
@ImplementedBy(RateSpecificationOneOf0.Default.class)
public interface RateSpecificationOneOf0 extends Validator<RateSpecification> {
	
	String NAME = "RateSpecificationOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<RateSpecification> validate(RosettaPath path, RateSpecification rateSpecification);
	
	class Default implements RateSpecificationOneOf0 {
	
		@Override
		public ValidationResult<RateSpecification> validate(RosettaPath path, RateSpecification rateSpecification) {
			ComparisonResult result = executeDataRule(rateSpecification);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "RateSpecification", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "RateSpecification", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(RateSpecification rateSpecification) {
			try {
				return choice(MapperS.of(rateSpecification), Arrays.asList("fixedRate", "floatingRate", "inflationRate"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements RateSpecificationOneOf0 {
	
		@Override
		public ValidationResult<RateSpecification> validate(RosettaPath path, RateSpecification rateSpecification) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "RateSpecification", path, DEFINITION);
		}
	}
}
