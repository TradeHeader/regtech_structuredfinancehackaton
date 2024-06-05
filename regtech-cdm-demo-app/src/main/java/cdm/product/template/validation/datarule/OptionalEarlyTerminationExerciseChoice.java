package cdm.product.template.validation.datarule;

import cdm.product.template.OptionalEarlyTermination;
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
@RosettaDataRule("OptionalEarlyTerminationExerciseChoice")
@ImplementedBy(OptionalEarlyTerminationExerciseChoice.Default.class)
public interface OptionalEarlyTerminationExerciseChoice extends Validator<OptionalEarlyTermination> {
	
	String NAME = "OptionalEarlyTerminationExerciseChoice";
	String DEFINITION = "optional choice americanExercise, bermudaExercise, europeanExercise";
	
	ValidationResult<OptionalEarlyTermination> validate(RosettaPath path, OptionalEarlyTermination optionalEarlyTermination);
	
	class Default implements OptionalEarlyTerminationExerciseChoice {
	
		@Override
		public ValidationResult<OptionalEarlyTermination> validate(RosettaPath path, OptionalEarlyTermination optionalEarlyTermination) {
			ComparisonResult result = executeDataRule(optionalEarlyTermination);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "OptionalEarlyTermination", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "OptionalEarlyTermination", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(OptionalEarlyTermination optionalEarlyTermination) {
			try {
				return choice(MapperS.of(optionalEarlyTermination), Arrays.asList("americanExercise", "bermudaExercise", "europeanExercise"), ChoiceRuleValidationMethod.OPTIONAL);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements OptionalEarlyTerminationExerciseChoice {
	
		@Override
		public ValidationResult<OptionalEarlyTermination> validate(RosettaPath path, OptionalEarlyTermination optionalEarlyTermination) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "OptionalEarlyTermination", path, DEFINITION);
		}
	}
}
