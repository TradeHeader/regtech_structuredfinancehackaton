package cdm.product.common.schedule.validation.datarule;

import cdm.product.common.schedule.InitialFixingDate;
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
@RosettaDataRule("InitialFixingDateOneOf0")
@ImplementedBy(InitialFixingDateOneOf0.Default.class)
public interface InitialFixingDateOneOf0 extends Validator<InitialFixingDate> {
	
	String NAME = "InitialFixingDateOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<InitialFixingDate> validate(RosettaPath path, InitialFixingDate initialFixingDate);
	
	class Default implements InitialFixingDateOneOf0 {
	
		@Override
		public ValidationResult<InitialFixingDate> validate(RosettaPath path, InitialFixingDate initialFixingDate) {
			ComparisonResult result = executeDataRule(initialFixingDate);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "InitialFixingDate", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "InitialFixingDate", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(InitialFixingDate initialFixingDate) {
			try {
				return choice(MapperS.of(initialFixingDate), Arrays.asList("relativeDateOffset", "initialFixingDate"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements InitialFixingDateOneOf0 {
	
		@Override
		public ValidationResult<InitialFixingDate> validate(RosettaPath path, InitialFixingDate initialFixingDate) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "InitialFixingDate", path, DEFINITION);
		}
	}
}
