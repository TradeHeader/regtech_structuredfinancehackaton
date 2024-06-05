package cdm.base.datetime.validation.datarule;

import cdm.base.datetime.AdjustableOrRelativeDate;
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
@RosettaDataRule("AdjustableOrRelativeDateAdjustableOrRelativeDateChoice")
@ImplementedBy(AdjustableOrRelativeDateAdjustableOrRelativeDateChoice.Default.class)
public interface AdjustableOrRelativeDateAdjustableOrRelativeDateChoice extends Validator<AdjustableOrRelativeDate> {
	
	String NAME = "AdjustableOrRelativeDateAdjustableOrRelativeDateChoice";
	String DEFINITION = "required choice adjustableDate, relativeDate";
	
	ValidationResult<AdjustableOrRelativeDate> validate(RosettaPath path, AdjustableOrRelativeDate adjustableOrRelativeDate);
	
	class Default implements AdjustableOrRelativeDateAdjustableOrRelativeDateChoice {
	
		@Override
		public ValidationResult<AdjustableOrRelativeDate> validate(RosettaPath path, AdjustableOrRelativeDate adjustableOrRelativeDate) {
			ComparisonResult result = executeDataRule(adjustableOrRelativeDate);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AdjustableOrRelativeDate", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AdjustableOrRelativeDate", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AdjustableOrRelativeDate adjustableOrRelativeDate) {
			try {
				return choice(MapperS.of(adjustableOrRelativeDate), Arrays.asList("adjustableDate", "relativeDate"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AdjustableOrRelativeDateAdjustableOrRelativeDateChoice {
	
		@Override
		public ValidationResult<AdjustableOrRelativeDate> validate(RosettaPath path, AdjustableOrRelativeDate adjustableOrRelativeDate) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AdjustableOrRelativeDate", path, DEFINITION);
		}
	}
}
