package cdm.observable.asset.validation.datarule;

import cdm.observable.asset.FloatingRateOption;
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
@RosettaDataRule("FloatingRateOptionFloatingRateIndex")
@ImplementedBy(FloatingRateOptionFloatingRateIndex.Default.class)
public interface FloatingRateOptionFloatingRateIndex extends Validator<FloatingRateOption> {
	
	String NAME = "FloatingRateOptionFloatingRateIndex";
	String DEFINITION = "required choice floatingRateIndex, inflationRateIndex";
	
	ValidationResult<FloatingRateOption> validate(RosettaPath path, FloatingRateOption floatingRateOption);
	
	class Default implements FloatingRateOptionFloatingRateIndex {
	
		@Override
		public ValidationResult<FloatingRateOption> validate(RosettaPath path, FloatingRateOption floatingRateOption) {
			ComparisonResult result = executeDataRule(floatingRateOption);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FloatingRateOption", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "FloatingRateOption", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(FloatingRateOption floatingRateOption) {
			try {
				return choice(MapperS.of(floatingRateOption), Arrays.asList("floatingRateIndex", "inflationRateIndex"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements FloatingRateOptionFloatingRateIndex {
	
		@Override
		public ValidationResult<FloatingRateOption> validate(RosettaPath path, FloatingRateOption floatingRateOption) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FloatingRateOption", path, DEFINITION);
		}
	}
}
