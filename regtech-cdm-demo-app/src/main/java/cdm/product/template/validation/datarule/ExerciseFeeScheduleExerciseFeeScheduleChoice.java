package cdm.product.template.validation.datarule;

import cdm.product.template.ExerciseFeeSchedule;
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
@RosettaDataRule("ExerciseFeeScheduleExerciseFeeScheduleChoice")
@ImplementedBy(ExerciseFeeScheduleExerciseFeeScheduleChoice.Default.class)
public interface ExerciseFeeScheduleExerciseFeeScheduleChoice extends Validator<ExerciseFeeSchedule> {
	
	String NAME = "ExerciseFeeScheduleExerciseFeeScheduleChoice";
	String DEFINITION = "required choice feeAmountSchedule, feeRateSchedule";
	
	ValidationResult<ExerciseFeeSchedule> validate(RosettaPath path, ExerciseFeeSchedule exerciseFeeSchedule);
	
	class Default implements ExerciseFeeScheduleExerciseFeeScheduleChoice {
	
		@Override
		public ValidationResult<ExerciseFeeSchedule> validate(RosettaPath path, ExerciseFeeSchedule exerciseFeeSchedule) {
			ComparisonResult result = executeDataRule(exerciseFeeSchedule);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ExerciseFeeSchedule", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ExerciseFeeSchedule", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ExerciseFeeSchedule exerciseFeeSchedule) {
			try {
				return choice(MapperS.of(exerciseFeeSchedule), Arrays.asList("feeAmountSchedule", "feeRateSchedule"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ExerciseFeeScheduleExerciseFeeScheduleChoice {
	
		@Override
		public ValidationResult<ExerciseFeeSchedule> validate(RosettaPath path, ExerciseFeeSchedule exerciseFeeSchedule) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ExerciseFeeSchedule", path, DEFINITION);
		}
	}
}
