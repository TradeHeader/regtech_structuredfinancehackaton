package cdm.product.template.validation.datarule;

import cdm.product.template.OptionStyle;
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
@RosettaDataRule("OptionStyleOneOf0")
@ImplementedBy(OptionStyleOneOf0.Default.class)
public interface OptionStyleOneOf0 extends Validator<OptionStyle> {
	
	String NAME = "OptionStyleOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<OptionStyle> validate(RosettaPath path, OptionStyle optionStyle);
	
	class Default implements OptionStyleOneOf0 {
	
		@Override
		public ValidationResult<OptionStyle> validate(RosettaPath path, OptionStyle optionStyle) {
			ComparisonResult result = executeDataRule(optionStyle);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "OptionStyle", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "OptionStyle", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(OptionStyle optionStyle) {
			try {
				return choice(MapperS.of(optionStyle), Arrays.asList("americanExercise", "bermudaExercise", "europeanExercise"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements OptionStyleOneOf0 {
	
		@Override
		public ValidationResult<OptionStyle> validate(RosettaPath path, OptionStyle optionStyle) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "OptionStyle", path, DEFINITION);
		}
	}
}
