package cdm.product.template.validation.datarule;

import cdm.product.template.MultipleExercise;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("MultipleExerciseMinimumNumberOfOptions")
@ImplementedBy(MultipleExerciseMinimumNumberOfOptions.Default.class)
public interface MultipleExerciseMinimumNumberOfOptions extends Validator<MultipleExercise> {
	
	String NAME = "MultipleExerciseMinimumNumberOfOptions";
	String DEFINITION = "if minimumNumberOfOptions exists then minimumNumberOfOptions >= 0";
	
	ValidationResult<MultipleExercise> validate(RosettaPath path, MultipleExercise multipleExercise);
	
	class Default implements MultipleExerciseMinimumNumberOfOptions {
	
		@Override
		public ValidationResult<MultipleExercise> validate(RosettaPath path, MultipleExercise multipleExercise) {
			ComparisonResult result = executeDataRule(multipleExercise);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MultipleExercise", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "MultipleExercise", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(MultipleExercise multipleExercise) {
			try {
				if (exists(MapperS.of(multipleExercise).<Integer>map("getMinimumNumberOfOptions", partialExercise -> partialExercise.getMinimumNumberOfOptions())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(multipleExercise).<Integer>map("getMinimumNumberOfOptions", partialExercise -> partialExercise.getMinimumNumberOfOptions()), MapperS.of(0), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements MultipleExerciseMinimumNumberOfOptions {
	
		@Override
		public ValidationResult<MultipleExercise> validate(RosettaPath path, MultipleExercise multipleExercise) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MultipleExercise", path, DEFINITION);
		}
	}
}
