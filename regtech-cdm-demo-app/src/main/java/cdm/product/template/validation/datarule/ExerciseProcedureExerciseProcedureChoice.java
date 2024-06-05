package cdm.product.template.validation.datarule;

import cdm.product.template.ExerciseProcedure;
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
@RosettaDataRule("ExerciseProcedureExerciseProcedureChoice")
@ImplementedBy(ExerciseProcedureExerciseProcedureChoice.Default.class)
public interface ExerciseProcedureExerciseProcedureChoice extends Validator<ExerciseProcedure> {
	
	String NAME = "ExerciseProcedureExerciseProcedureChoice";
	String DEFINITION = "required choice manualExercise, automaticExercise";
	
	ValidationResult<ExerciseProcedure> validate(RosettaPath path, ExerciseProcedure exerciseProcedure);
	
	class Default implements ExerciseProcedureExerciseProcedureChoice {
	
		@Override
		public ValidationResult<ExerciseProcedure> validate(RosettaPath path, ExerciseProcedure exerciseProcedure) {
			ComparisonResult result = executeDataRule(exerciseProcedure);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ExerciseProcedure", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ExerciseProcedure", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ExerciseProcedure exerciseProcedure) {
			try {
				return choice(MapperS.of(exerciseProcedure), Arrays.asList("manualExercise", "automaticExercise"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ExerciseProcedureExerciseProcedureChoice {
	
		@Override
		public ValidationResult<ExerciseProcedure> validate(RosettaPath path, ExerciseProcedure exerciseProcedure) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ExerciseProcedure", path, DEFINITION);
		}
	}
}
