package cdm.product.template.validation.datarule;

import cdm.product.template.PartialExercise;
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
@RosettaDataRule("PartialExerciseMinimumChoice")
@ImplementedBy(PartialExerciseMinimumChoice.Default.class)
public interface PartialExerciseMinimumChoice extends Validator<PartialExercise> {
	
	String NAME = "PartialExerciseMinimumChoice";
	String DEFINITION = "required choice minimumNotionalAmount, minimumNumberOfOptions";
	
	ValidationResult<PartialExercise> validate(RosettaPath path, PartialExercise partialExercise);
	
	class Default implements PartialExerciseMinimumChoice {
	
		@Override
		public ValidationResult<PartialExercise> validate(RosettaPath path, PartialExercise partialExercise) {
			ComparisonResult result = executeDataRule(partialExercise);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PartialExercise", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PartialExercise", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PartialExercise partialExercise) {
			try {
				return choice(MapperS.of(partialExercise), Arrays.asList("minimumNotionalAmount", "minimumNumberOfOptions"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PartialExerciseMinimumChoice {
	
		@Override
		public ValidationResult<PartialExercise> validate(RosettaPath path, PartialExercise partialExercise) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PartialExercise", path, DEFINITION);
		}
	}
}
